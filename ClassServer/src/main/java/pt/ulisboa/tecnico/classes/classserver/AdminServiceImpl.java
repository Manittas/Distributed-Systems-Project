package pt.ulisboa.tecnico.classes.classserver;

import io.grpc.stub.StreamObserver;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.Stringify;
import pt.ulisboa.tecnico.classes.classserver.Commands.*;
import pt.ulisboa.tecnico.classes.contract.ClassesDefinitions;
import pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer;
import pt.ulisboa.tecnico.classes.contract.admin.AdminServiceGrpc;

import java.util.Map;

public class AdminServiceImpl extends AdminServiceGrpc.AdminServiceImplBase {

    private ClassServerFrontend serverFrontend;

    public AdminServiceImpl(ClassServerFrontend serverFrontend) { this.serverFrontend = serverFrontend; }

    public void activate(AdminClassServer.ActivateRequest request,
                         StreamObserver<AdminClassServer.ActivateResponse> responseObserver) {
        ServerStruct _server = this.serverFrontend.getClassServer();

        if (_server.isDebugOn()) {
            System.err.println(Constants.REQUEST_ADMIN + Constants.ACTIVATE_CMD);
        }

        ActivateCommand cmd = new ActivateCommand(_server);
        cmd.execute();    // Command always execute OK

        AdminClassServer.ActivateResponse response = AdminClassServer.ActivateResponse.newBuilder().
                setCode(ClassesDefinitions.ResponseCode.OK).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

        if (_server.isDebugOn()) {
            System.err.println(Constants.RESPONSE_ADMIN + Constants.ACTIVATE_CMD + " - " + Stringify.format(response.getCode()) + "\n");
        }
    }

    public void deactivate(AdminClassServer.DeactivateRequest request,
                         StreamObserver<AdminClassServer.DeactivateResponse> responseObserver) {
        ServerStruct _server = this.serverFrontend.getClassServer();

        if (_server.isDebugOn()) {
            System.err.println(Constants.REQUEST_ADMIN + Constants.DEACTIVATE_CMD);
        }

        DeactivateCommand cmd = new DeactivateCommand(_server);
        cmd.execute();    // Command always execute OK

        AdminClassServer.DeactivateResponse response = AdminClassServer.DeactivateResponse.newBuilder().
                setCode(ClassesDefinitions.ResponseCode.OK).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

        if (_server.isDebugOn()) {
            System.err.println(Constants.RESPONSE_ADMIN + Constants.DEACTIVATE_CMD + " - " + Stringify.format(response.getCode()) + "\n");
        }
    }

    public void dump(AdminClassServer.DumpRequest request,
                           StreamObserver<AdminClassServer.DumpResponse> responseObserver) {
        ServerStruct _server = this.serverFrontend.getClassServer();

        if (_server.isDebugOn()) {
            System.err.println(Constants.REQUEST_ADMIN + Constants.DUMP_CMD);
        }

        AdminClassServer.DumpResponse response;
        synchronized (_server) {
            DumpCommand cmd = new DumpCommand(_server);
            String code = cmd.execute();  // Command always execute OK

            Class _class;
            _class = _server.getServerClass();

            if (code.equals("INACTIVE_SERVER")) {
                response = AdminClassServer.DumpResponse.newBuilder().
                        setCode(ClassesDefinitions.ResponseCode.INACTIVE_SERVER).build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
                return;
            }

            ClassesDefinitions.ClassState.Builder classStateBuilder = ClassesDefinitions.ClassState.newBuilder().
                    setCapacity(_class.getCapacity()).setOpenEnrollments(_class.getEnrollmentStatus());

            // Getting all enrolled students
            for (Map.Entry<String, String> entry : _class.getEnrolledStudents().entrySet()) {
                ClassesDefinitions.Student student = ClassesDefinitions.Student.newBuilder().setStudentId("aluno" + entry.getKey()).
                        setStudentName(entry.getValue()).build();
                classStateBuilder.addEnrolled(student);
            }

            // Getting all discarded students
            for (Map.Entry<String, String> entry : _class.getNotEnrolledStudents().entrySet()) {
                ClassesDefinitions.Student student = ClassesDefinitions.Student.newBuilder().setStudentId("aluno" + entry.getKey()).
                        setStudentName(entry.getValue()).build();
                classStateBuilder.addDiscarded(student);
            }

            response = AdminClassServer.DumpResponse.newBuilder().
                    setCode(ClassesDefinitions.ResponseCode.OK).setClassState(classStateBuilder.build()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        if (_server.isDebugOn()) {
            System.err.println(Constants.RESPONSE_ADMIN + Constants.DUMP_CMD + " - " + Stringify.format(response.getCode()) + "\n");
        }
    }

    public void deactivateGossip(AdminClassServer.DeactivateGossipRequest request,
                         StreamObserver<AdminClassServer.DeactivateGossipResponse> responseObserver) {
        ServerStruct _server = this.serverFrontend.getClassServer();

        if (_server.isDebugOn()) {
            System.err.println(Constants.REQUEST_ADMIN + Constants.DEACTIVATE_GOSSIP_CMD);
        }

        DeactivateGossipCommand cmd = new DeactivateGossipCommand(_server);
        String code = cmd.execute();

        AdminClassServer.DeactivateGossipResponse.Builder responseBuilder = AdminClassServer.DeactivateGossipResponse.newBuilder();

        switch (code) {
            case "OK":
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.OK);
                break;
            case "INACTIVE_SERVER":
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.INACTIVE_SERVER);
                break;
        }

        AdminClassServer.DeactivateGossipResponse response = responseBuilder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

        if (_server.isDebugOn()) {
            System.err.println(Constants.RESPONSE_ADMIN + Constants.DEACTIVATE_GOSSIP_CMD + " - " + Stringify.format(response.getCode()) + "\n");
        }
    }

    public void activateGossip(AdminClassServer.ActivateGossipRequest request,
                         StreamObserver<AdminClassServer.ActivateGossipResponse> responseObserver) {
        ServerStruct _server = this.serverFrontend.getClassServer();

        if (_server.isDebugOn()) {
            System.err.println(Constants.REQUEST_ADMIN + Constants.ACTIVATE_GOSSIP_CMD);
        }

        ActivateGossipCommand cmd = new ActivateGossipCommand(_server);
        String code = cmd.execute();    // Command always execute OK

        AdminClassServer.ActivateGossipResponse.Builder responseBuilder = AdminClassServer.ActivateGossipResponse.newBuilder();

        switch (code) {
            case "OK":
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.OK);
                break;
            case "INACTIVE_SERVER":
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.INACTIVE_SERVER);
                break;
        }

        AdminClassServer.ActivateGossipResponse response = responseBuilder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

        if (_server.isDebugOn()) {
            System.err.println(Constants.RESPONSE_ADMIN + Constants.ACTIVATE_GOSSIP_CMD + " - " + Stringify.format(response.getCode()) + "\n");
        }
    }

    public void gossip(AdminClassServer.GossipRequest request,
                       StreamObserver<AdminClassServer.GossipResponse> responseObserver) {
        ServerStruct _server = this.serverFrontend.getClassServer();

        if (_server.isDebugOn()) {
            System.err.println(Constants.REQUEST_ADMIN + Constants.GOSSIP_CMD);
        }

        GossipCommand cmd = new GossipCommand(serverFrontend);
        String code = cmd.execute();

        AdminClassServer.GossipResponse.Builder responseBuilder = AdminClassServer.GossipResponse.newBuilder();

        switch (code) {
            case "OK":
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.OK);
                break;
            case "INACTIVE_SERVER":
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.INACTIVE_SERVER);
                break;
            case "GOSSIP_OFF":
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.GOSSIP_OFF);
                break;
        }

        AdminClassServer.GossipResponse response = responseBuilder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

        if (_server.isDebugOn()) {
            System.err.println(Constants.RESPONSE_ADMIN + Constants.GOSSIP_CMD + " - " + Stringify.format(response.getCode()) + "\n");
        }
    }
}
