package pt.ulisboa.tecnico.classes.classserver;

import io.grpc.stub.StreamObserver;
import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.Stringify;
import pt.ulisboa.tecnico.classes.VectorClock;
import pt.ulisboa.tecnico.classes.classserver.Commands.CancelEnrollmentCommand;
import pt.ulisboa.tecnico.classes.classserver.Commands.CloseEnrollmentsCommand;
import pt.ulisboa.tecnico.classes.classserver.Commands.Command;
import pt.ulisboa.tecnico.classes.classserver.Commands.OpenEnrollmentsCommand;
import pt.ulisboa.tecnico.classes.classserver.Commands.ListClassCommand;
import pt.ulisboa.tecnico.classes.contract.ClassesDefinitions;
import pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer;
import pt.ulisboa.tecnico.classes.contract.professor.ProfessorServiceGrpc;

import java.util.List;
import java.util.Map;

public class ProfessorServiceImpl extends ProfessorServiceGrpc.ProfessorServiceImplBase {
    private ClassServerFrontend serverFrontend;

    public ProfessorServiceImpl(ClassServerFrontend serverFrontend) {
        this.serverFrontend = serverFrontend;
    }

    public void openEnrollments(ProfessorClassServer.OpenEnrollmentsRequest request,
                                StreamObserver<ProfessorClassServer.OpenEnrollmentsResponse> responseObserver) {
        ServerStruct serverStruct = this.serverFrontend.getClassServer();

        if (serverStruct.isDebugOn()) {
            System.err.println(Constants.REQUEST_PROFESSOR + Constants.OPEN_ENROLLMENTS_CMD);
        }

        int capacity = request.getCapacity();

        VectorClock clientVectorClock = new VectorClock(serverStruct.getId());

        List<ClassesDefinitions.VectorClockMessage> clientVectorClockMessage = request.getVectorClockList();

        for (ClassesDefinitions.VectorClockMessage vectorClockInstance : clientVectorClockMessage) {
            clientVectorClock.setClock(vectorClockInstance.getId(), vectorClockInstance.getValue());
        }

        Command command = new OpenEnrollmentsCommand(capacity, serverStruct, clientVectorClock);
        String code = command.execute();
        ProfessorClassServer.OpenEnrollmentsResponse.Builder responseBuilder = ProfessorClassServer.
                OpenEnrollmentsResponse.newBuilder();

        switch(code) {
            case("INACTIVE_SERVER"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.INACTIVE_SERVER);
                break;
            case("ENROLLMENTS_ALREADY_OPENED"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.ENROLLMENTS_ALREADY_OPENED);
                break;
            case("FULL_CLASS"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.FULL_CLASS);
                break;
            case("GOSSIP_OCCURRING"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.GOSSIP_OCCURRING);
                break;
            case("INCONSISTENT_STATE"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.INCONSISTENT_STATE);
                break;
            case("OK"):
                synchronized (serverStruct) {
                    UpdateLog updateLog = serverStruct.getUpdateLog();
                    updateLog.add(updateLog.encodeOpenEnrollments(serverStruct.getOperationN(), (OpenEnrollmentsCommand) command));
                    serverStruct.getOperationTable().get(serverStruct.getId()).add(serverStruct.getOperationN());
                    serverStruct.incrementOperationN();
                }

                responseBuilder.setCode(ClassesDefinitions.ResponseCode.OK);
                break;
        }

        for (Map.Entry<Integer, Integer> entry : clientVectorClock.getClock().entrySet()) {
            ClassesDefinitions.VectorClockMessage clock = ClassesDefinitions.VectorClockMessage.newBuilder()
                    .setId(entry.getValue())
                    .setValue(entry.getValue()).build();

            responseBuilder.addVectorClock(clock);
        }

        ProfessorClassServer.OpenEnrollmentsResponse response = responseBuilder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

        if (serverStruct.isDebugOn()) {
            System.err.println(Constants.RESPONSE_PROFESSOR + Constants.OPEN_ENROLLMENTS_CMD + " - " +
                    Stringify.format(response.getCode()) + "\n");
        }
    }

    public void closeEnrollments(ProfessorClassServer.CloseEnrollmentsRequest request,
                                StreamObserver<ProfessorClassServer.CloseEnrollmentsResponse> responseObserver) {
        ServerStruct serverStruct = this.serverFrontend.getClassServer();

        if (serverStruct.isDebugOn()) {
            System.err.println(Constants.REQUEST_PROFESSOR + Constants.CLOSE_ENROLLMENTS_CMD);
        }

        VectorClock clientVectorClock = new VectorClock(serverStruct.getId());

        List<ClassesDefinitions.VectorClockMessage> clientVectorClockMessage = request.getVectorClockList();

        for (ClassesDefinitions.VectorClockMessage vectorClockInstance : clientVectorClockMessage) {
            clientVectorClock.setClock(vectorClockInstance.getId(), vectorClockInstance.getValue());
        }

        Command command = new CloseEnrollmentsCommand(serverStruct, clientVectorClock);
        String code = command.execute();
        ProfessorClassServer.CloseEnrollmentsResponse.Builder responseBuilder = ProfessorClassServer.
                CloseEnrollmentsResponse.newBuilder();

        switch(code) {
            case("INACTIVE_SERVER"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.INACTIVE_SERVER);
                break;
            case("ENROLLMENTS_ALREADY_CLOSED"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.ENROLLMENTS_ALREADY_CLOSED);
                break;
            case("GOSSIP_OCCURRING"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.GOSSIP_OCCURRING);
                break;
            case("INCONSISTENT_STATE"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.INCONSISTENT_STATE);
                break;
            case("OK"):
                synchronized (serverStruct) {
                    UpdateLog updateLog = serverStruct.getUpdateLog();
                    updateLog.add(updateLog.encodeCloseEnrollments(serverStruct.getOperationN()));
                    serverStruct.getOperationTable().get(serverStruct.getId()).add(serverStruct.getOperationN());
                    serverStruct.incrementOperationN();
                }

                responseBuilder.setCode(ClassesDefinitions.ResponseCode.OK);
                break;
        }

        for (Map.Entry<Integer, Integer> entry : clientVectorClock.getClock().entrySet()) {
            ClassesDefinitions.VectorClockMessage clock = ClassesDefinitions.VectorClockMessage.newBuilder()
                    .setId(entry.getValue())
                    .setValue(entry.getValue()).build();

            responseBuilder.addVectorClock(clock);
        }

        ProfessorClassServer.CloseEnrollmentsResponse response = responseBuilder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

        if (serverStruct.isDebugOn()) {
            System.err.println(Constants.RESPONSE_PROFESSOR + Constants.CLOSE_ENROLLMENTS_CMD + " - " +
                    Stringify.format(response.getCode()) + "\n");
        }
    }

    public void cancelEnrollment(ProfessorClassServer.CancelEnrollmentRequest request,
                                 StreamObserver<ProfessorClassServer.CancelEnrollmentResponse> responseObserver) {
        ServerStruct serverStruct = this.serverFrontend.getClassServer();

        if (serverStruct.isDebugOn()) {
            System.err.println(Constants.REQUEST_PROFESSOR + Constants.CANCEL_ENROLLMENT_CMD);
        }

        VectorClock clientVectorClock = new VectorClock(serverStruct.getId());

        List<ClassesDefinitions.VectorClockMessage> clientVectorClockMessage = request.getVectorClockList();

        for (ClassesDefinitions.VectorClockMessage vectorClockInstance : clientVectorClockMessage) {
            clientVectorClock.setClock(vectorClockInstance.getId(), vectorClockInstance.getValue());
        }

        String student_number = request.getStudentId();
        Command command = new CancelEnrollmentCommand(student_number, serverStruct, clientVectorClock);
        String code = command.execute();
        ProfessorClassServer.CancelEnrollmentResponse.Builder responseBuilder = ProfessorClassServer.
                CancelEnrollmentResponse.newBuilder();

        switch(code) {
            case ("EXCEPTION_INVALID_ARGUMENT"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.ARGUMENT_NOT_VALID);
                break;
            case("INACTIVE_SERVER"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.INACTIVE_SERVER);
                break;
            case("NON_EXISTING_STUDENT"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.NON_EXISTING_STUDENT);
                break;
            case("GOSSIP_OCCURRING"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.GOSSIP_OCCURRING);
                break;
            case("INCONSISTENT_STATE"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.INCONSISTENT_STATE);
                break;
            case("OK"):
                synchronized (serverStruct) {
                    UpdateLog updateLog = serverStruct.getUpdateLog();
                    updateLog.add(updateLog.encodeCancelEnrollment(serverStruct.getOperationN(), (CancelEnrollmentCommand) command));
                    serverStruct.getOperationTable().get(serverStruct.getId()).add(serverStruct.getOperationN());
                    serverStruct.incrementOperationN();
                }

                responseBuilder.setCode(ClassesDefinitions.ResponseCode.OK);
                break;
        }

        for (Map.Entry<Integer, Integer> entry : clientVectorClock.getClock().entrySet()) {
            ClassesDefinitions.VectorClockMessage clock = ClassesDefinitions.VectorClockMessage.newBuilder()
                    .setId(entry.getValue())
                    .setValue(entry.getValue()).build();

            responseBuilder.addVectorClock(clock);
        }

        ProfessorClassServer.CancelEnrollmentResponse response = responseBuilder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

        if (serverStruct.isDebugOn()) {
            System.err.println(Constants.RESPONSE_PROFESSOR + Constants.CANCEL_ENROLLMENT_CMD + " - " +
                    Stringify.format(response.getCode()) + "\n");
        }
    }

    public void listClass(ProfessorClassServer.ListClassRequest request,
                                 StreamObserver<ProfessorClassServer.ListClassResponse> responseObserver) {
        ServerStruct serverStruct = this.serverFrontend.getClassServer();

        if (serverStruct.isDebugOn()) {
            System.err.println(Constants.REQUEST_PROFESSOR + Constants.LIST_CMD);
        }

        VectorClock clientVectorClock = new VectorClock(serverStruct.getId());

        List<ClassesDefinitions.VectorClockMessage> clientVectorClockMessage = request.getVectorClockList();

        for (ClassesDefinitions.VectorClockMessage vectorClockInstance : clientVectorClockMessage) {
            clientVectorClock.setClock(vectorClockInstance.getId(), vectorClockInstance.getValue());
        }

        ProfessorClassServer.ListClassResponse.Builder responseBuilder;
        synchronized (serverStruct) {
            responseBuilder = ProfessorClassServer.ListClassResponse.newBuilder();
            Command command = new ListClassCommand(serverStruct, clientVectorClock);
            String code = command.execute();

            Class classCopy;
            classCopy = serverStruct.getServerClass();

            switch (code) {
                case ("INACTIVE_SERVER"):
                    responseBuilder.setCode(ClassesDefinitions.ResponseCode.INACTIVE_SERVER);
                    break;
                case("INCONSISTENT_STATE"):
                    responseBuilder.setCode(ClassesDefinitions.ResponseCode.INCONSISTENT_STATE);
                    break;
                case ("OK"):
                    ClassesDefinitions.ClassState.Builder classStateBuilder = ClassesDefinitions.ClassState.newBuilder();
                    classStateBuilder.setCapacity(classCopy.getCapacity());
                    classStateBuilder.setOpenEnrollments(classCopy.getEnrollmentStatus());

                    for (Map.Entry<String, String> studentObject : classCopy.getEnrolledStudents().entrySet()) {
                        ClassesDefinitions.Student student = ClassesDefinitions.Student.newBuilder().setStudentId("aluno" + studentObject.getKey()).setStudentName(studentObject.getValue()).build();
                        classStateBuilder.addEnrolled(student);
                    }

                    for (Map.Entry<String, String> studentObject : classCopy.getNotEnrolledStudents().entrySet()) {
                        ClassesDefinitions.Student student = ClassesDefinitions.Student.newBuilder().setStudentId("aluno" + studentObject.getKey()).setStudentName(studentObject.getValue()).build();
                        classStateBuilder.addDiscarded(student);
                    }

                    ClassesDefinitions.ClassState classState = classStateBuilder.build();
                    responseBuilder.setCode(ClassesDefinitions.ResponseCode.OK).setClassState(classState);
                    break;
            }
        }

        for (Map.Entry<Integer, Integer> entry : clientVectorClock.getClock().entrySet()) {
            ClassesDefinitions.VectorClockMessage clock = ClassesDefinitions.VectorClockMessage.newBuilder()
                    .setId(entry.getValue())
                    .setValue(entry.getValue()).build();

            responseBuilder.addVectorClock(clock);
        }

        ProfessorClassServer.ListClassResponse response = responseBuilder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

        if (serverStruct.isDebugOn()) {
            System.err.println(Constants.RESPONSE_PROFESSOR + Constants.LIST_CMD + " - " +
                    Stringify.format(response.getCode()) + "\n");
        }
    }

}
