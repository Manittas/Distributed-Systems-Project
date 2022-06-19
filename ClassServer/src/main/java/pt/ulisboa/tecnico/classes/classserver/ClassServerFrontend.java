package pt.ulisboa.tecnico.classes.classserver;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.Stringify;
import pt.ulisboa.tecnico.classes.VectorClock;
import pt.ulisboa.tecnico.classes.classserver.Commands.PropagateModificationsCommand;
import pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer;
import pt.ulisboa.tecnico.classes.contract.classserver.ClassServerServiceGrpc;
import pt.ulisboa.tecnico.classes.contract.naming.ClassServerNamingServer;
import pt.ulisboa.tecnico.classes.contract.naming.NamingServerServiceGrpc;
import pt.ulisboa.tecnico.classes.contract.ClassesDefinitions;

import java.util.*;

import java.time.LocalTime;

public class ClassServerFrontend {
    private final static long GOSSIP_DELAY = 2000;  // Delay to execute the gossip

    private ServerStruct classServer;
    private HashMap<Integer, VectorClock> vectorClocks = new HashMap<Integer, VectorClock>();   // Vector clocks maintained during the gossip
    private HashMap<Integer, UpdateLog> updateLogs = new HashMap<Integer, UpdateLog>();       // Update logs maintained during the gossip
    private Timer timer;

    private final String hostNamingServer = "localhost";
    private final int portNamingServer = 5001;

    private ManagedChannel channelServer;
    private ManagedChannel channelNamingServer;

    private ClassServerServiceGrpc.ClassServerServiceBlockingStub stubServer;
    private NamingServerServiceGrpc.NamingServerServiceBlockingStub stubNamingServer;

    public ClassServerFrontend(ServerStruct classServer) {
        this.classServer = classServer;
        channelNamingServer = ManagedChannelBuilder.forAddress(hostNamingServer, portNamingServer).usePlaintext().build();
        stubNamingServer = NamingServerServiceGrpc.newBlockingStub(channelNamingServer);

        setupGossipTimer();
    }

    public ServerStruct getClassServer() {
        return this.classServer;
    }

    public void getClassServer(ServerStruct classServer) {
        this.classServer = classServer;
    }

    public HashMap<Integer, VectorClock> getVectorClocks() { return this.vectorClocks; }

    public HashMap<Integer, UpdateLog> getUpdateLogs() { return this.updateLogs; }

    public ManagedChannel getChannelServer() { return this.channelServer; }

    public void setChannelServer(ManagedChannel channel) { this.channelServer = channel; }

    public ClassServerServiceGrpc.ClassServerServiceBlockingStub getStubServer() { return this.stubServer; }

    public void setStubServer(ClassServerServiceGrpc.ClassServerServiceBlockingStub stub) { this.stubServer = stub; }

    private void setupGossipTimer() {
        this.timer = new Timer();
        this.timer.schedule(new GossipTask(this), GOSSIP_DELAY, GOSSIP_DELAY);
    }

    public ArrayList<ClassesDefinitions.LookupServer> getServers(ArrayList<String> qualifiers) {
        ClassServerNamingServer.LookupRequest requestNamingServer = ClassServerNamingServer.LookupRequest.newBuilder().
                setServiceName(Constants.TURMAS_SERVICE).addAllQualifiers(qualifiers).build();
        ClassServerNamingServer.LookupResponse responseNamingServer = stubNamingServer.lookup(requestNamingServer);

        ArrayList<ClassesDefinitions.LookupServer> servers = new ArrayList<ClassesDefinitions.LookupServer>(responseNamingServer.getServerList());

        if (this.getClassServer().isDebugOn()) {
            System.err.println('\n' + Constants.SERVERS_REQUESTED + servers + " - " + Stringify.format(responseNamingServer.getCode()));
        }

        return servers;
    }

    public int getServerNumber() {
        ClassServerNamingServer.InfoRequest requestNamingServer = ClassServerNamingServer.InfoRequest.newBuilder().build();
        ClassServerNamingServer.InfoResponse responseNamingServer = stubNamingServer.getInfo(requestNamingServer);

        if (this.getClassServer().isDebugOn()) {
            System.err.println(Constants.SERVER_GET_INFO + " - " + Stringify.format(responseNamingServer.getCode()));
        }

        return responseNamingServer.getServerN();
    }

    // closes channels
    public void closeChannels() {
        System.out.println("\nShutting Server down ...\n");
        if (channelServer != null) {
            channelServer.shutdown();
        }
        if (channelNamingServer != null) {
            channelNamingServer.shutdown();
        }
    }
}
