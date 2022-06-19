package pt.ulisboa.tecnico.classes.professor;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.Stringify;
import pt.ulisboa.tecnico.classes.VectorClock;

import pt.ulisboa.tecnico.classes.contract.ClassesDefinitions;
import pt.ulisboa.tecnico.classes.contract.naming.ClassServerNamingServer;
import pt.ulisboa.tecnico.classes.contract.naming.NamingServerServiceGrpc;
import pt.ulisboa.tecnico.classes.contract.professor.ProfessorServiceGrpc;
import pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.*;
import pt.ulisboa.tecnico.classes.contract.naming.ClassServerNamingServer.*;
import pt.ulisboa.tecnico.classes.contract.naming.ClassServerNamingServer.LookupRequest;
import pt.ulisboa.tecnico.classes.contract.naming.ClassServerNamingServer.LookupResponse;

import java.util.ArrayList;
import java.util.Map;

import static pt.ulisboa.tecnico.classes.Constants.MAX_TRIES;

public class ProfessorFrontend {
    private boolean debug;
    private VectorClock vectorClock = new VectorClock();

    private String hostNamingServer;
    private int portNamingServer;
    private String hostServer;
    private int portServer;
    private ManagedChannel channelServer;
    private ManagedChannel channelNamingServer;
    private ProfessorServiceGrpc.ProfessorServiceBlockingStub stubServer;
    private NamingServerServiceGrpc.NamingServerServiceBlockingStub stubNamingServer;

    public ProfessorFrontend(boolean debug) {
        this.debug = debug;
        hostNamingServer = "localhost";
        portNamingServer = 5001;
        channelNamingServer = ManagedChannelBuilder.forAddress(hostNamingServer, portNamingServer).usePlaintext().build();
        stubNamingServer = NamingServerServiceGrpc.newBlockingStub(channelNamingServer);
    }

    private boolean isDebugOn() {
        return this.debug;
    }

    public void openEnrollments(int capacity){
        ArrayList<ClassesDefinitions.LookupServer> servers = getServers(new ArrayList<String>());

        if(servers.isEmpty()) {
            return;
        }

        OpenEnrollmentsRequest.Builder requestServerBuilder = OpenEnrollmentsRequest.newBuilder();
        requestServerBuilder.setCapacity(capacity);

        for (Map.Entry<Integer, Integer> entry : this.vectorClock.getClock().entrySet()) {
            ClassesDefinitions.VectorClockMessage clock = ClassesDefinitions.VectorClockMessage.newBuilder()
                                                            .setId(entry.getKey())
                                                            .setValue(entry.getValue()).build();

            requestServerBuilder.addVectorClock(clock);
        }

       OpenEnrollmentsRequest requestServer = requestServerBuilder.build();

        int i = 1;
        OpenEnrollmentsResponse responseServer = null;

        while (i <= MAX_TRIES) {
            int r = (int) (Math.random() * servers.size());

            hostServer = servers.get(r).getHost();
            portServer = servers.get(r).getPort();

            if (isDebugOn()) {
                System.err.println(Constants.OPEN_ENROLLMENTS_CMD + " - " + hostServer + ":" + portServer + '\n');
            }

            channelServer = ManagedChannelBuilder.forAddress(hostServer, portServer).usePlaintext().build();
            stubServer = ProfessorServiceGrpc.newBlockingStub(channelServer);

            responseServer = stubServer.openEnrollments(requestServer);
            channelServer.shutdown();

            if (responseServer.getCode() == ClassesDefinitions.ResponseCode.INACTIVE_SERVER) {
                i++;
            } else {
                System.out.println(Stringify.format(responseServer.getCode()) + " (tried " + i + " times)" + '\n');
                for (ClassesDefinitions.VectorClockMessage vectorClockInstance : responseServer.getVectorClockList()) {
                    int key = vectorClockInstance.getId();
                    int value = vectorClockInstance.getValue();
                    this.vectorClock.setClock(key, value);

                }
                return;
            }
        }

        System.out.println(Stringify.format(responseServer.getCode()) + " (tried " + (i - 1) + " times)" + '\n');
    }

    public void closeEnrollments() {
        ArrayList<ClassesDefinitions.LookupServer> servers = getServers(new ArrayList<String>());

        if(servers.isEmpty()) {
            return;
        }

        CloseEnrollmentsRequest.Builder requestServerBuilder = CloseEnrollmentsRequest.newBuilder();

        for (Map.Entry<Integer, Integer> entry : this.vectorClock.getClock().entrySet()) {
            ClassesDefinitions.VectorClockMessage clock = ClassesDefinitions.VectorClockMessage.newBuilder()
                    .setId(entry.getKey())
                    .setValue(entry.getValue()).build();

            requestServerBuilder.addVectorClock(clock);
        }

        CloseEnrollmentsRequest requestServer = requestServerBuilder.build();

        int i = 1;
        CloseEnrollmentsResponse responseServer = null;

        while (i <= MAX_TRIES) {
            int r = (int) (Math.random() * servers.size());

            hostServer = servers.get(r).getHost();
            portServer = servers.get(r).getPort();

            if (isDebugOn()) {
                System.err.println(Constants.CLOSE_ENROLLMENTS_CMD + " - " + hostServer + ":" + portServer + '\n');
            }

            channelServer = ManagedChannelBuilder.forAddress(hostServer, portServer).usePlaintext().build();
            stubServer = ProfessorServiceGrpc.newBlockingStub(channelServer);

            responseServer = stubServer.closeEnrollments(requestServer);
            channelServer.shutdown();

            if (responseServer.getCode() == ClassesDefinitions.ResponseCode.INACTIVE_SERVER) {
                i++;
            } else {
                System.out.println(Stringify.format(responseServer.getCode()) + " (tried " + i + " times)" + '\n');
                for (ClassesDefinitions.VectorClockMessage vectorClockInstance : responseServer.getVectorClockList()) {
                    int key = vectorClockInstance.getId();
                    int value = vectorClockInstance.getValue();
                    this.vectorClock.setClock(key, value);

                }
                return;
            }
        }

        System.out.println(Stringify.format(responseServer.getCode()) + " (tried " + (i - 1) + " times)" + '\n');
    }

    public void listClass() {
        ArrayList<ClassesDefinitions.LookupServer> servers = getServers(new ArrayList<String>());

        if(servers.isEmpty()) {
            return;
        }

        ListClassRequest.Builder requestServerBuilder = ListClassRequest.newBuilder();

        for (Map.Entry<Integer, Integer> entry : this.vectorClock.getClock().entrySet()) {
            ClassesDefinitions.VectorClockMessage clock = ClassesDefinitions.VectorClockMessage.newBuilder()
                    .setId(entry.getKey())
                    .setValue(entry.getValue()).build();

            requestServerBuilder.addVectorClock(clock);
        }

        ListClassRequest requestServer = requestServerBuilder.build();
        int i = 1;
        ListClassResponse responseServer = null;

        while (i <= MAX_TRIES) {
            int r = (int) (Math.random() * servers.size());

            hostServer = servers.get(r).getHost();
            portServer = servers.get(r).getPort();

            if (isDebugOn()) {
                System.err.println(Constants.CANCEL_ENROLLMENT_CMD + " - " + hostServer + ":" + portServer + '\n');
            }

            channelServer = ManagedChannelBuilder.forAddress(hostServer, portServer).usePlaintext().build();
            stubServer = ProfessorServiceGrpc.newBlockingStub(channelServer);

            responseServer = stubServer.listClass(requestServer);
            channelServer.shutdown();

            if (responseServer.getCode() == ClassesDefinitions.ResponseCode.INACTIVE_SERVER) {
                i++;
            } else {
                System.out.println(Stringify.format(responseServer.getClassState()) + "\n(tried " + i + " times)" + '\n');
                for (ClassesDefinitions.VectorClockMessage vectorClockInstance : responseServer.getVectorClockList()) {
                    int key = vectorClockInstance.getId();
                    int value = vectorClockInstance.getValue();
                    this.vectorClock.setClock(key, value);

                }
                return;
            }
        }

        System.out.println(Stringify.format(responseServer.getCode()) + "\n(tried " + (i - 1) + " times)" + '\n');
    }

    public void cancelEnrollment(String number) {
        ArrayList<ClassesDefinitions.LookupServer> servers = getServers(new ArrayList<String>());

        if(servers.isEmpty()) {
            return;
        }

        CancelEnrollmentRequest.Builder requestServerBuilder = CancelEnrollmentRequest.newBuilder();

        requestServerBuilder.setStudentId(number);

        for (Map.Entry<Integer, Integer> entry : this.vectorClock.getClock().entrySet()) {
            ClassesDefinitions.VectorClockMessage clock = ClassesDefinitions.VectorClockMessage.newBuilder()
                    .setId(entry.getValue())
                    .setValue(entry.getValue()).build();

            requestServerBuilder.addVectorClock(clock);
        }

        CancelEnrollmentRequest requestServer = requestServerBuilder.build();
        int i = 1;
        CancelEnrollmentResponse responseServer = null;

        while (i <= MAX_TRIES) {
            int r = (int) (Math.random() * servers.size());

            hostServer = servers.get(r).getHost();
            portServer = servers.get(r).getPort();

            if (isDebugOn()) {
                System.err.println(Constants.LIST_CMD + " - " + hostServer + ":" + portServer + '\n');
            }

            channelServer = ManagedChannelBuilder.forAddress(hostServer, portServer).usePlaintext().build();
            stubServer = ProfessorServiceGrpc.newBlockingStub(channelServer);

            responseServer = stubServer.cancelEnrollment(requestServer);
            channelServer.shutdown();

            if (responseServer.getCode() == ClassesDefinitions.ResponseCode.INACTIVE_SERVER) {
                i++;
            } else {
                System.out.println(Stringify.format(responseServer.getCode()) + " (tried " + i + " times)" + '\n');
                for (ClassesDefinitions.VectorClockMessage vectorClockInstance : responseServer.getVectorClockList()) {
                    int key = vectorClockInstance.getId();
                    int value = vectorClockInstance.getValue();
                    this.vectorClock.setClock(key, value);

                }
                return;
            }
        }

        System.out.println(Stringify.format(responseServer.getCode()) + " (tried " + (i - 1) + " times)" + '\n');
    }

    public ArrayList<ClassesDefinitions.LookupServer> getServers(ArrayList<String> qualifiers) {
        ClassServerNamingServer.LookupRequest requestNamingServer = ClassServerNamingServer.LookupRequest.newBuilder().
                setServiceName(Constants.TURMAS_SERVICE).addAllQualifiers(qualifiers).build();
        ClassServerNamingServer.LookupResponse responseNamingServer = stubNamingServer.lookup(requestNamingServer);

        ArrayList<ClassesDefinitions.LookupServer> servers = new ArrayList<ClassesDefinitions.LookupServer>(responseNamingServer.getServerList());

        if (isDebugOn()) {
            System.err.println('\n' + Constants.SERVERS_REQUESTED + servers + "\n - " + Stringify.format(responseNamingServer.getCode()));
        }

        return servers;
    }
    
    // closes channels
    public void closeChannels() {
        System.out.println("\nShutting Professor down ...\n");
        if (channelServer != null) {
            channelServer.shutdown();
        }
        if (channelNamingServer != null) {
            channelNamingServer.shutdown();
        }
    }
}
