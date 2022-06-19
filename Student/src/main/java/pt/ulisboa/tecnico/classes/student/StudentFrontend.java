package pt.ulisboa.tecnico.classes.student;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.Stringify;
import pt.ulisboa.tecnico.classes.VectorClock;

import pt.ulisboa.tecnico.classes.contract.ClassesDefinitions;
import pt.ulisboa.tecnico.classes.contract.naming.ClassServerNamingServer;
import pt.ulisboa.tecnico.classes.contract.naming.NamingServerServiceGrpc;
import pt.ulisboa.tecnico.classes.contract.student.StudentClassServer;
import pt.ulisboa.tecnico.classes.contract.student.StudentServiceGrpc;
import pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.*;

import java.util.ArrayList;
import java.util.Map;

import static pt.ulisboa.tecnico.classes.Constants.MAX_TRIES;
import static pt.ulisboa.tecnico.classes.contract.ClassesDefinitions.Student;

public class StudentFrontend{
    private boolean debug;
    private VectorClock vectorClock = new VectorClock();

    private String hostNamingServer;
    private int portNamingServer;
    private String hostServer;
    private int portServer;
    private ManagedChannel channelServer;
    private ManagedChannel channelNamingServer;
    private StudentServiceGrpc.StudentServiceBlockingStub stubServer;
    private NamingServerServiceGrpc.NamingServerServiceBlockingStub stubNamingServer;

    public StudentFrontend(boolean debug) {
        this.debug = debug;
        hostNamingServer = "localhost";
        portNamingServer = 5001;
        channelNamingServer = ManagedChannelBuilder.forAddress(hostNamingServer, portNamingServer).usePlaintext().build();
        stubNamingServer = NamingServerServiceGrpc.newBlockingStub(channelNamingServer);
    }

    private boolean isDebugOn() {
        return this.debug;
    }

    // stub for list command
    public void list() {
        ArrayList<ClassesDefinitions.LookupServer> servers = getServers(new ArrayList<String>());

        if (servers.isEmpty()) {
            return;
        }

        StudentClassServer.ListClassRequest.Builder requestServerBuilder = StudentClassServer.ListClassRequest.newBuilder();

        for (Map.Entry<Integer, Integer> entry : this.vectorClock.getClock().entrySet()) {
            ClassesDefinitions.VectorClockMessage clock = ClassesDefinitions.VectorClockMessage.newBuilder()
                    .setId(entry.getKey())
                    .setValue(entry.getValue()).build();

            requestServerBuilder.addVectorClock(clock);
        }

        StudentClassServer.ListClassRequest requestServer = requestServerBuilder.build();
        int i = 1;
        StudentClassServer.ListClassResponse responseServer = null;

        while (i <= MAX_TRIES) {
            int r = (int) (Math.random() * servers.size());

            hostServer = servers.get(r).getHost();
            portServer = servers.get(r).getPort();

            if (isDebugOn()) {
                System.err.println(Constants.CANCEL_ENROLLMENT_CMD + " - " + hostServer + ":" + portServer + '\n');
            }

            channelServer = ManagedChannelBuilder.forAddress(hostServer, portServer).usePlaintext().build();
            stubServer = StudentServiceGrpc.newBlockingStub(channelServer);

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

        System.out.println(Stringify.format(responseServer.getCode()) + " (tried " + (i - 1) + " times)" + '\n');
    }

    // stub for enroll command
    public void enroll(String id, String name) {
        ArrayList<ClassesDefinitions.LookupServer> servers = getServers(new ArrayList<String>());

        if (servers.isEmpty()) {
            return;
        }

        StudentClassServer.EnrollRequest.Builder requestServerBuilder = StudentClassServer.EnrollRequest.newBuilder();
        requestServerBuilder.setStudent(Student.newBuilder().setStudentName(name).setStudentId(id).build());

        for (Map.Entry<Integer, Integer> entry : this.vectorClock.getClock().entrySet()) {
            ClassesDefinitions.VectorClockMessage clock = ClassesDefinitions.VectorClockMessage.newBuilder()
                    .setId(entry.getKey())
                    .setValue(entry.getValue()).build();

            requestServerBuilder.addVectorClock(clock);
        }

        StudentClassServer.EnrollRequest requestServer = requestServerBuilder.build();

        int i = 1;
        EnrollResponse responseServer = null;

        while (i <= MAX_TRIES) {
            int r = (int) (Math.random() * servers.size());

            hostServer = servers.get(r).getHost();
            portServer = servers.get(r).getPort();

            if (isDebugOn()) {
                System.err.println(Constants.CANCEL_ENROLLMENT_CMD + " - " + hostServer + ":" + portServer + '\n');
            }

            channelServer = ManagedChannelBuilder.forAddress(hostServer, portServer).usePlaintext().build();
            stubServer = StudentServiceGrpc.newBlockingStub(channelServer);

            responseServer = stubServer.enroll(requestServer);
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
        System.out.println("\nShutting Student down ...\n");
        if (channelServer != null) {
            channelServer.shutdown();
        }
        if (channelNamingServer != null) {
            channelNamingServer.shutdown();
        }
    }
}