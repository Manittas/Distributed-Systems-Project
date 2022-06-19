package pt.ulisboa.tecnico.classes.admin;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.Stringify;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import pt.ulisboa.tecnico.classes.contract.ClassesDefinitions;
import pt.ulisboa.tecnico.classes.contract.admin.AdminServiceGrpc;
import pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.*;
import pt.ulisboa.tecnico.classes.contract.classserver.ClassServerServiceGrpc;
import pt.ulisboa.tecnico.classes.contract.naming.ClassServerNamingServer;
import pt.ulisboa.tecnico.classes.contract.naming.NamingServerServiceGrpc;
import pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer;
import pt.ulisboa.tecnico.classes.contract.professor.ProfessorServiceGrpc;

import java.util.ArrayList;

import static pt.ulisboa.tecnico.classes.Constants.MAX_TRIES;

public class AdminFrontend {

    private boolean debug;

    private String hostNamingServer;
    private int portNamingServer;
    private String hostServer;
    private int portServer;
    private ManagedChannel channelServer;
    private ManagedChannel channelNamingServer;
    private AdminServiceGrpc.AdminServiceBlockingStub stubServer;
    private NamingServerServiceGrpc.NamingServerServiceBlockingStub stubNamingServer;

    public AdminFrontend(boolean debug) {
        this.debug = debug;
        hostNamingServer = "localhost";
        portNamingServer = 5001;
        channelNamingServer = ManagedChannelBuilder.forAddress(hostNamingServer, portNamingServer).usePlaintext().build();
        stubNamingServer = NamingServerServiceGrpc.newBlockingStub(channelNamingServer);
    }

    private boolean debugIsOn() {
        return debug;
    }

    // Activates a server (primary or secondary, according to the server_type)
    public void activate(String serverType) {
        // Getting all servers
        ArrayList<ClassesDefinitions.LookupServer> servers;
        ArrayList<String> qualifiers = new ArrayList<String>();
        qualifiers.add(serverType);
        servers = getServers(qualifiers);

        ActivateResponse response;
        // Sending the gossip request to every server
        for (ClassesDefinitions.LookupServer server : servers) {
            String hostServer = server.getHost();
            int portServer = server.getPort();

            if (debugIsOn()) {
                System.err.println(Constants.GOSSIP_CMD + " -> " + hostServer + ":" + portServer + '\n');
            }

            channelServer = ManagedChannelBuilder.forAddress(hostServer, portServer).usePlaintext().build();
            stubServer = AdminServiceGrpc.newBlockingStub(channelServer);

            response = stubServer.activate(ActivateRequest.getDefaultInstance());
            channelServer.shutdown();

            if (debugIsOn()) {
                System.err.println(Constants.GOSSIP_CMD + " -> " + response.getCode() + '\n');
            }
        }
    }

    // Dectivates a server (primary or secondary, according to the server_type)
    public void deactivate(String serverType) {
        // Getting all servers
        ArrayList<ClassesDefinitions.LookupServer> servers;
        ArrayList<String> qualifiers = new ArrayList<String>();
        qualifiers.add(serverType);
        servers = getServers(qualifiers);

        DeactivateResponse response;
        // Sending the gossip request to every server
        for (ClassesDefinitions.LookupServer server : servers) {
            String hostServer = server.getHost();
            int portServer = server.getPort();

            if (debugIsOn()) {
                System.err.println(Constants.GOSSIP_CMD + " -> " + hostServer + ":" + portServer + '\n');
            }

            channelServer = ManagedChannelBuilder.forAddress(hostServer, portServer).usePlaintext().build();
            stubServer = AdminServiceGrpc.newBlockingStub(channelServer);

            response = stubServer.deactivate(DeactivateRequest.getDefaultInstance());
            channelServer.shutdown();

            if (debugIsOn()) {
                System.err.println(Constants.GOSSIP_CMD + " -> " + response.getCode() + '\n');
            }
        }
    }

    // Gets information about the server
    public void dump(String serverType) {
        ArrayList<ClassesDefinitions.LookupServer> servers = getServers(new ArrayList<String>());

        if(servers.isEmpty()) {
            return;
        }

        DumpRequest dumpRequest = DumpRequest.getDefaultInstance();
        int i = 1;
        DumpResponse responseServer = null;

        while (i <= MAX_TRIES) {
            int r = (int) (Math.random() * servers.size());

            hostServer = servers.get(r).getHost();
            portServer = servers.get(r).getPort();

            if (debugIsOn()) {
                System.err.println(Constants.DUMP_CMD + " - " + hostServer + ":" + portServer + '\n');
            }

            channelServer = ManagedChannelBuilder.forAddress(hostServer, portServer).usePlaintext().build();
            stubServer = AdminServiceGrpc.newBlockingStub(channelServer);

            responseServer = stubServer.dump(dumpRequest);
            channelServer.shutdown();

            if (responseServer.getCode() == ClassesDefinitions.ResponseCode.INACTIVE_SERVER) {
                i++;
                continue;
            } else {
                System.out.println(Stringify.format(responseServer.getClassState()) + '\n' + "(tried " + i + " times)" + '\n');
                return;
            }
        }

        System.out.println(Stringify.format(responseServer.getCode()) + " (tried " + (i-1) + " times)" + '\n');
    }

    public void deactivateGossip(String serverType) {
        // Getting all servers
        ArrayList<ClassesDefinitions.LookupServer> servers;
        ArrayList<String> qualifiers = new ArrayList<String>();
        qualifiers.add(serverType);
        servers = getServers(qualifiers);

        DeactivateGossipResponse response;
        // Sending the gossip request to every server
        for (ClassesDefinitions.LookupServer server : servers) {
            String hostServer = server.getHost();
            int portServer = server.getPort();

            if (debugIsOn()) {
                System.err.println(Constants.GOSSIP_CMD + " -> " + hostServer + ":" + portServer + '\n');
            }

            channelServer = ManagedChannelBuilder.forAddress(hostServer, portServer).usePlaintext().build();
            stubServer = AdminServiceGrpc.newBlockingStub(channelServer);

            response = stubServer.deactivateGossip(DeactivateGossipRequest.getDefaultInstance());
            channelServer.shutdown();

            if (debugIsOn()) {
                System.err.println(Constants.GOSSIP_CMD + " -> " + response.getCode() + '\n');
            }
        }
    }

    public void activateGossip(String serverType) {
        // Getting all servers
        ArrayList<ClassesDefinitions.LookupServer> servers;
        ArrayList<String> qualifiers = new ArrayList<String>();
        qualifiers.add(serverType);
        servers = getServers(qualifiers);

        ActivateGossipResponse response;
        // Sending the gossip request to every server
        for (ClassesDefinitions.LookupServer server : servers) {
            String hostServer = server.getHost();
            int portServer = server.getPort();

            if (debugIsOn()) {
                System.err.println(Constants.GOSSIP_CMD + " -> " + hostServer + ":" + portServer);
            }

            channelServer = ManagedChannelBuilder.forAddress(hostServer, portServer).usePlaintext().build();
            stubServer = AdminServiceGrpc.newBlockingStub(channelServer);

            response = stubServer.activateGossip(ActivateGossipRequest.getDefaultInstance());
            channelServer.shutdown();

            if (debugIsOn()) {
                System.err.println(response.getCode() + "\n");
            }
        }
    }

    public void gossip() {
        GossipRequest request = GossipRequest.getDefaultInstance();

        // Getting all servers
        ArrayList<ClassesDefinitions.LookupServer> servers;
        ArrayList<String> qualifiers = new ArrayList<String>();
        servers = getServers(qualifiers);

        GossipResponse response;
        // Sending the gossip request to every server
        for (ClassesDefinitions.LookupServer server : servers) {
            String hostServer = server.getHost();
            int portServer = server.getPort();

            if (debugIsOn()) {
                System.err.println(Constants.GOSSIP_CMD + " -> " + hostServer + ":" + portServer + '\n');
            }

            channelServer = ManagedChannelBuilder.forAddress(hostServer, portServer).usePlaintext().build();
            stubServer = AdminServiceGrpc.newBlockingStub(channelServer);

            response = stubServer.gossip(request);
            channelServer.shutdown();

            if (debugIsOn()) {
                System.err.println(Constants.GOSSIP_CMD + " -> " + response.getCode() + '\n');
            }
        }
    }

    public ArrayList<ClassesDefinitions.LookupServer> getServers(ArrayList<String> qualifiers) {
        ClassServerNamingServer.LookupRequest requestNamingServer = ClassServerNamingServer.LookupRequest.newBuilder().
                setServiceName(Constants.TURMAS_SERVICE).addAllQualifiers(qualifiers).build();
        ClassServerNamingServer.LookupResponse responseNamingServer = stubNamingServer.lookup(requestNamingServer);

        ArrayList<ClassesDefinitions.LookupServer> servers = new ArrayList<ClassesDefinitions.LookupServer>(responseNamingServer.getServerList());

        if (debugIsOn()) {
            System.err.println('\n' + Constants.SERVERS_REQUESTED + servers + "\n - " + Stringify.format(responseNamingServer.getCode()));
        }

        return servers;
    }

    // closes channels
    public void closeChannels() {
        System.out.println("\nShutting Admin down ...\n");
        if (channelServer != null) {
            channelServer.shutdown();
        }
        if (channelNamingServer != null) {
            channelNamingServer.shutdown();
        }
    }
}
