package pt.ulisboa.tecnico.classes.classserver;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.classserver.Commands.*;

import java.util.ArrayList;

public class UpdateLog {
    private ArrayList<String> log = new ArrayList<String>();

    public UpdateLog() {}

    // Returns the internal representation of the update log (for grpc purposes)
    public ArrayList<String> getLog() { return this.log; }

    public void add(String encodedCmd) {
        log.add(encodedCmd);
    }

    public void clear() {
        log.clear();
    }

    // Encoders

    public String encodeOpenEnrollments(int cmdN, OpenEnrollmentsCommand cmd) {
        return cmdN + " " + Constants.OPEN_ENROLLMENTS_CMD + " " + cmd.getCapacity();
    }

    public String encodeCloseEnrollments(int cmdN) {
        return cmdN + " " + Constants.CLOSE_ENROLLMENTS_CMD;
    }

    public String encodeCancelEnrollment(int cmdN, CancelEnrollmentCommand cmd) {
        return cmdN + " " + Constants.CANCEL_ENROLLMENT_CMD + " " + cmd.getStudentNumber();
    }

    public String encodeEnrollCommand(int cmdN, EnrollCommand cmd) {
        return cmdN + " " + Constants.ENROLL_CMD + " " + cmd.getId() + " " + cmd.getName();
    }

    public String toString() {
        return this.log.toString();
    }
}
