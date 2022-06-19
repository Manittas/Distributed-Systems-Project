package pt.ulisboa.tecnico.classes.classserver;

import io.grpc.ManagedChannelBuilder;
import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.Stringify;
import pt.ulisboa.tecnico.classes.VectorClock;
import pt.ulisboa.tecnico.classes.classserver.Commands.PropagateModificationsCommand;
import pt.ulisboa.tecnico.classes.contract.ClassesDefinitions;
import pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer;
import pt.ulisboa.tecnico.classes.contract.classserver.ClassServerServiceGrpc;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.TimerTask;

public class GossipTask extends TimerTask {
    private ClassServerFrontend serverFrontend;

    public GossipTask(ClassServerFrontend serverFrontend) {
        this.serverFrontend = serverFrontend;
    }

    // Gossip method
    public void run() {
        ServerStruct classServer = serverFrontend.getClassServer();

        if (!classServer.isGossipOn()) {
            System.out.println(Constants.GOSSIP_IS_OFF);
            return;
        }

        ClassServerClassServer.PropagateModificationsRequest.Builder requestBuilder;
        synchronized (classServer) {
            // Inactive servers don't gossip
            if (!classServer.isActive()) {
                return;
            }

            classServer.setGossipOccurring(true);

            requestBuilder = ClassServerClassServer.PropagateModificationsRequest.newBuilder();
            requestBuilder.setServerId(classServer.getId());
            requestBuilder.addAllUpdateLog(classServer.getUpdateLog().getLog());
            requestBuilder.addAllVectorClock(classServer.getVectorClock().proto());
        }

        ClassServerClassServer.PropagateModificationsRequest request = requestBuilder.build();

        // Getting all servers
        ArrayList<ClassesDefinitions.LookupServer> servers;
        ArrayList<String> qualifiers = new ArrayList<String>();
        servers = serverFrontend.getServers(qualifiers);

        if (servers.isEmpty()) {
            classServer.setGossipOccurring(false);

            if (serverFrontend.getClassServer().isDebugOn()) {
                System.err.println(Constants.PROPAGATION_FINISHED + " - " + Constants.NO_OTHER_SERVERS_AVAILABLE + "\n");
            }

            return;
        }

        if (serverFrontend.getClassServer().isDebugOn()) {
            System.err.println(Constants.PROPAGATING_STATE);
        }

        ClassServerClassServer.PropagateModificationsResponse response = null;
        // Sending the vector clock and update log to every server
        for (ClassesDefinitions.LookupServer server : servers) {
            String hostServer = server.getHost();
            int portServer = server.getPort();

            if (serverFrontend.getClassServer().isDebugOn()) {
                System.err.println(Constants.PROPAGATING_TO + hostServer + ":" + portServer + '\n');
            }

            serverFrontend.setChannelServer(ManagedChannelBuilder.forAddress(hostServer, portServer).usePlaintext().build());
            serverFrontend.setStubServer(ClassServerServiceGrpc.newBlockingStub(serverFrontend.getChannelServer()));

            response = serverFrontend.getStubServer().propagateModifications(request);
            serverFrontend.getChannelServer().shutdown();
        }

        LocalTime gossipTimeout = LocalTime.now().plusNanos(Constants.GOSSIP_TIMEOUT);
        // Wait for the update logs of other replicas
        while (LocalTime.now().isBefore(gossipTimeout)) {
            continue;
        }

        applyModifications();

        if (serverFrontend.getClassServer().isDebugOn()) {
            System.err.println(Constants.PROPAGATION_FINISHED + " - " + Stringify.format(response.getCode()) + "\n");
        }
    }

    // Applies the modifications received from other replicas, ensuring a coherent state.
    private void applyModifications() {
        synchronized (this) {
            ServerStruct server = serverFrontend.getClassServer();

            synchronized (server) {
                // Adding the vector clock and update log of the own server
                serverFrontend.getVectorClocks().put(server.getId(), server.getVectorClock());
                serverFrontend.getUpdateLogs().put(server.getId(), server.getUpdateLog());

                PropagateModificationsCommand cmd = new PropagateModificationsCommand(server, serverFrontend.getVectorClocks(),
                        serverFrontend.getUpdateLogs());
                String code = cmd.execute();

                // Updates the server vector clock
                VectorClock classServerVectorClock = server.getVectorClock();
                for (Map.Entry<Integer, VectorClock> entry : serverFrontend.getVectorClocks().entrySet()) {
                    if (server.getId() != entry.getKey()) {
                        classServerVectorClock.update(entry.getValue());
                    }
                }

                int serverN = serverFrontend.getServerNumber();
                // Clear the update log if all servers participated in the gossip
                if (serverFrontend.getUpdateLogs().size() == serverN) {
                    server.getUpdateLog().clear();
                }

                serverFrontend.getUpdateLogs().clear();
                serverFrontend.getVectorClocks().clear();

                server.setGossipOccurring(false);

                if (server.isDebugOn()) {
                    System.err.println(Constants.UPDATING_STATE_FINISHED + " - " + code);
                }
            }
        }
    }
}
