package pt.ulisboa.tecnico.classes.classserver.Commands;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.VectorClock;
import pt.ulisboa.tecnico.classes.classserver.Class;
import pt.ulisboa.tecnico.classes.classserver.ServerStruct;

public class CancelEnrollmentCommand extends Command{

    private String studentNumber;
    private ServerStruct server;
    private VectorClock clientVectorClock;

    public CancelEnrollmentCommand(String number, ServerStruct server1, VectorClock clientVectorClock) {
        studentNumber = number;
        server = server1;
        this.clientVectorClock = clientVectorClock;
    }

    public String getStudentNumber() { return this.studentNumber; }

    @Override
    public String execute() {
        if (server.isDebugOn()) {
            System.err.println(Constants.SERVER_CANCEL_ENROLLMENT + "aluno" + studentNumber);
        }

        try {

            if (studentNumber.length() != 4) {
                return "EXCEPTION_INVALID_ARGUMENT";
            }

            Integer.parseInt(studentNumber);

            synchronized (server) {
                Class _class = server.getServerClass();

                if (!server.isActive()) {
                    return "INACTIVE_SERVER";
                }

                if (!server.isPrimary()){
                    return "WRITING_NOT_SUPPORTED";
                }

                if (!_class.getEnrolledStudents().containsKey(studentNumber)) {
                    return "NON_EXISTING_STUDENT";
                }

                if (server.isGossipOccurring()) {
                    return "GOSSIP_OCCURRING";
                }

                if (!clientVectorClock.happenedBefore(server.getVectorClock())) {
                    return "INCONSISTENT_STATE";
                }

                server.getVectorClock().increment(server.getId());
                clientVectorClock = server.getVectorClock();
                _class.removeEnrolledStudent(studentNumber);
            }

            return "OK";

        } catch (NumberFormatException e) {
            return "EXCEPTION_INVALID_ARGUMENT";
        }
    }
}
