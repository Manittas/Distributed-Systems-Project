package pt.ulisboa.tecnico.classes.classserver;

import io.grpc.stub.StreamObserver;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.Stringify;
import pt.ulisboa.tecnico.classes.VectorClock;
import pt.ulisboa.tecnico.classes.classserver.Commands.ListClassCommand;
import pt.ulisboa.tecnico.classes.classserver.Commands.EnrollCommand;
import pt.ulisboa.tecnico.classes.classserver.Commands.Command;
import pt.ulisboa.tecnico.classes.classserver.Commands.OpenEnrollmentsCommand;
import pt.ulisboa.tecnico.classes.contract.ClassesDefinitions;

import pt.ulisboa.tecnico.classes.contract.student.StudentClassServer;
import pt.ulisboa.tecnico.classes.contract.student.StudentServiceGrpc;

import java.util.List;
import java.util.Map;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {
    private ClassServerFrontend serverFrontend;

    public StudentServiceImpl(ClassServerFrontend serverFrontend) {
        this.serverFrontend = serverFrontend;
    }

    public void listClass(StudentClassServer.ListClassRequest request,
                    StreamObserver<StudentClassServer.ListClassResponse> responseObserver){
        ServerStruct serverStruct = this.serverFrontend.getClassServer();

        if (serverStruct.isDebugOn()) {
            System.err.println(Constants.REQUEST_STUDENT + Constants.LIST_CMD);
        }

        VectorClock clientVectorClock = new VectorClock(serverStruct.getId());

        List<ClassesDefinitions.VectorClockMessage> clientVectorClockMessage = request.getVectorClockList();

        for (ClassesDefinitions.VectorClockMessage vectorClockInstance : clientVectorClockMessage) {
            clientVectorClock.setClock(vectorClockInstance.getId(), vectorClockInstance.getValue());
        }

        StudentClassServer.ListClassResponse.Builder responseBuilder;
        synchronized (serverStruct) {
            responseBuilder = StudentClassServer.ListClassResponse.newBuilder();
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
                case("GOSSIP_OCCURRING"):
                    responseBuilder.setCode(ClassesDefinitions.ResponseCode.GOSSIP_OCCURRING);
                    break;
                case ("OK"):
                    ClassesDefinitions.ClassState.Builder classStateBuilder = ClassesDefinitions.ClassState.newBuilder();
                    classStateBuilder.setCapacity(classCopy.getCapacity());
                    classStateBuilder.setOpenEnrollments(classCopy.getEnrollmentStatus());

                    for (Map.Entry<String, String> studentObject : classCopy.getEnrolledStudents().entrySet()) {
                        ClassesDefinitions.Student student = ClassesDefinitions.Student.newBuilder().
                                setStudentId("aluno" + studentObject.getKey()).setStudentName(studentObject.getValue()).build();
                        classStateBuilder.addEnrolled(student);
                    }

                    for (Map.Entry<String, String> studentObject : classCopy.getNotEnrolledStudents().entrySet()) {
                        ClassesDefinitions.Student student = ClassesDefinitions.Student.newBuilder().
                                setStudentId("aluno" + studentObject.getKey()).setStudentName(studentObject.getValue()).build();
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

        StudentClassServer.ListClassResponse response = responseBuilder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

        if (serverStruct.isDebugOn()) {
            System.err.println(Constants.RESPONSE_STUDENT + Constants.LIST_CMD + " - " +
                    Stringify.format(response.getCode()) + "\n");
        }
    }

    public void enroll(StudentClassServer.EnrollRequest request,
                    StreamObserver<StudentClassServer.EnrollResponse> responseObserver){
        ServerStruct serverStruct = this.serverFrontend.getClassServer();

        if (serverStruct.isDebugOn()) {
            System.err.println(Constants.REQUEST_STUDENT + Constants.ENROLL_CMD);
        }

        VectorClock clientVectorClock = new VectorClock(serverStruct.getId());

        List<ClassesDefinitions.VectorClockMessage> clientVectorClockMessage = request.getVectorClockList();

        for (ClassesDefinitions.VectorClockMessage vectorClockInstance : clientVectorClockMessage) {
            clientVectorClock.setClock(vectorClockInstance.getId(), vectorClockInstance.getValue());
        }

        StudentClassServer.EnrollResponse.Builder responseBuilder = StudentClassServer.EnrollResponse.newBuilder();
        ClassesDefinitions.Student student = request.getStudent();
        Command command = new EnrollCommand(serverStruct, student.getStudentId(), student.getStudentName(), clientVectorClock);
        String code = command.execute();

        switch(code) {
            case("INACTIVE_SERVER"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.INACTIVE_SERVER);
                break;
            case("ENROLLMENTS_ALREADY_CLOSED"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.ENROLLMENTS_ALREADY_CLOSED);
                break;
            case("STUDENT_ALREADY_ENROLLED"):
                responseBuilder.setCode(ClassesDefinitions.ResponseCode.STUDENT_ALREADY_ENROLLED);
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
                    updateLog.add(updateLog.encodeEnrollCommand(serverStruct.getOperationN(), (EnrollCommand) command));
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

        StudentClassServer.EnrollResponse response = responseBuilder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

        if (serverStruct.isDebugOn()) {
            System.err.println(Constants.RESPONSE_STUDENT + Constants.ENROLL_CMD + " - " +
                    Stringify.format(response.getCode()) + "\n");
        }
    }
}