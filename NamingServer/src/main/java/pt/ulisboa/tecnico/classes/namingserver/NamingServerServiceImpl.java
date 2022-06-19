package pt.ulisboa.tecnico.classes.namingserver;

import io.grpc.stub.StreamObserver;
import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.Stringify;
import pt.ulisboa.tecnico.classes.contract.ClassesDefinitions;
import pt.ulisboa.tecnico.classes.contract.naming.ClassServerNamingServer;
import pt.ulisboa.tecnico.classes.contract.naming.NamingServerServiceGrpc;
import pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer;
import pt.ulisboa.tecnico.classes.namingserver.Commands.Command;
import pt.ulisboa.tecnico.classes.namingserver.Commands.DeleteCommand;
import pt.ulisboa.tecnico.classes.namingserver.Commands.LookupCommand;
import pt.ulisboa.tecnico.classes.namingserver.Commands.RegisterCommand;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NamingServerServiceImpl extends NamingServerServiceGrpc.NamingServerServiceImplBase{
    private NamingServices namingServices;

    public NamingServerServiceImpl(NamingServices _namingServices) {
        namingServices = _namingServices;
    }

    public void register(ClassServerNamingServer.RegisterRequest request,
                         StreamObserver<ClassServerNamingServer.RegisterResponse> responseObserver) {

        if (namingServices.isDebugOn()) {
            System.err.println(Constants.REQUEST_SERVER + Constants.REGISTER_CMD);
        }

        String serviceName = request.getServiceName();
        String host = request.getServer().getHost();
        int port = request.getServer().getPort();
        int id;
        synchronized (namingServices) {
            id = namingServices.getServerN();
        }

        ArrayList<String> qualifiers = new ArrayList<String>();

        for (String qualifier : request.getQualifiersList()) {
            qualifiers.add(qualifier);
        }

        Command command = new RegisterCommand(namingServices, serviceName, host, port, qualifiers, id);
        String code = command.execute();
        ClassServerNamingServer.RegisterResponse.Builder responseBuilder = ClassServerNamingServer.RegisterResponse.newBuilder();

        switch(code) {
            case("OK"):
                ClassServerNamingServer.RegisterResponse response = responseBuilder.setServerId(id).build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();

                synchronized (namingServices) {
                    namingServices.incrementServerN();
                }

                if (namingServices.isDebugOn()) {
                    System.err.println(Constants.RESPONSE_SERVER + Constants.REGISTER_CMD + " - " +
                            "OK" + "\n");
                }
                break;
        }
    }

    public void lookup(ClassServerNamingServer.LookupRequest request,
                       StreamObserver<ClassServerNamingServer.LookupResponse> responseObserver) {

        if (namingServices.isDebugOn()) {
            System.err.println(Constants.REQUEST_SERVER + Constants.LOOKUP_CMD);
        }

        String serviceName = request.getServiceName();
        ArrayList<String> qualifiers = new ArrayList<String>();

        for (String qualifier : request.getQualifiersList()) {
            qualifiers.add(qualifier);
        }

        ArrayList<ServerEntry> serverEntries = new ArrayList<ServerEntry>();

        Command command = new LookupCommand(namingServices, serviceName, qualifiers, serverEntries);
        String code = command.execute();
        ClassServerNamingServer.LookupResponse.Builder responseBuilder = ClassServerNamingServer.LookupResponse.newBuilder();

        switch(code) {
            case ("OK"):
                for (ServerEntry serverEntry : serverEntries) {
                    ClassesDefinitions.LookupServer server = ClassesDefinitions.LookupServer
                                .newBuilder()
                                .setHost(serverEntry.getHost())
                                .setPort(serverEntry.getPort())
                                .setId(serverEntry.getId())
                                .build();
                    responseBuilder.addServer(server);
                }

                synchronized (namingServices) {
                    for (int serverId : namingServices.getServerList()) {
                        responseBuilder.addServerList(serverId);
                    }
                }

                responseBuilder.setCode(ClassesDefinitions.ResponseCode.OK);
                break;
            case ("NON_EXISTING_SERVER"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.NON_EXISTING_SERVER);
                break;
        }

        ClassServerNamingServer.LookupResponse response = responseBuilder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

        if (namingServices.isDebugOn()) {
            System.err.println(Constants.RESPONSE_SERVER + Constants.LOOKUP_CMD + " - " +
                    Stringify.format(response.getCode()) + "\n");
        }
    }

    public void delete(ClassServerNamingServer.DeleteRequest request,
                       StreamObserver<ClassServerNamingServer.DeleteResponse> responseObserver) {

        if (namingServices.isDebugOn()) {
            System.err.println(Constants.REQUEST_SERVER + Constants.DELETE_CMD);
        }

        String serviceName = request.getServiceName();
        String serverHost = request.getServer().getHost();
        int serverPort = request.getServer().getPort();

        Command command = new DeleteCommand(namingServices, serviceName, serverHost, serverPort);
        String code = command.execute();
        ClassServerNamingServer.DeleteResponse.Builder responseBuilder = ClassServerNamingServer.DeleteResponse.newBuilder();

        switch(code) {
            case ("OK"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.OK);
                break;
            case ("NON_EXISTING_SERVER"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.NON_EXISTING_SERVER);
                break;
        }

        ClassServerNamingServer.DeleteResponse response = responseBuilder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

        if (namingServices.isDebugOn()) {
            System.err.println(Constants.RESPONSE_SERVER + Constants.DELETE_CMD + " - " +
                    Stringify.format(response.getCode()) + "\n");
        }
    }

    public void getInfo(ClassServerNamingServer.InfoRequest request,
                       StreamObserver<ClassServerNamingServer.InfoResponse> responseObserver) {
        if (namingServices.isDebugOn()) {
            System.err.println(Constants.REQUEST_SERVER + Constants.GET_INFO_CMD);
        }

        int serverN;
        synchronized (namingServices) {
            serverN = namingServices.getServerN();
        }

        ClassServerNamingServer.InfoResponse response = ClassServerNamingServer.InfoResponse.newBuilder()
                .setServerN(serverN)
                .setCode(ClassesDefinitions.ResponseCode.OK)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

        if (namingServices.isDebugOn()) {
            System.err.println(Constants.RESPONSE_SERVER + Constants.GET_INFO_CMD + " - " +
                    Stringify.format(response.getCode()) + "\n");
        }
    }
}
