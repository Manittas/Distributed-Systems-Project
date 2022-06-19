package pt.ulisboa.tecnico.classes.classserver.Commands;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.VectorClock;
import pt.ulisboa.tecnico.classes.classserver.Class;
import pt.ulisboa.tecnico.classes.classserver.ServerStruct;

public class EnrollCommand extends Command {

    private ServerStruct _server;
    private String _id;
    private String _name;
    private VectorClock clientVectorClock;

    public EnrollCommand(ServerStruct server, String id, String name, VectorClock clientVectorClock) {
        this._server = server;
        this._id = id;
        this._name = name;
        this.clientVectorClock = clientVectorClock;
    }

    public String getId() { return this._id; }

    public String getName() { return this._name; }

    @Override
    public String execute() {
        if (_server.isDebugOn()) {
            System.err.println(Constants.SERVER_ENROLL + "aluno" + _id + " " + _name);
        }

        synchronized (_server) {
            Class _class = _server.getServerClass();

            if (!_server.isActive()) {
                return "INACTIVE_SERVER";
            }

            if (!_server.isPrimary()){
                return "WRITING_NOT_SUPPORTED";
            }

            if (!_class.getEnrollmentStatus()) {
                return "ENROLLMENTS_ALREADY_CLOSED";
            }

            if (_class.getEnrolledStudents().containsKey(_id)) {
                return "STUDENT_ALREADY_ENROLLED";
            }

            if (_class.getCapacity() == _class.getEnrolledStudents().size()) {
                return "FULL_CLASS";
            }

            if (_server.isGossipOccurring()) {
                return "GOSSIP_OCCURRING";
            }

            if (!clientVectorClock.happenedBefore(_server.getVectorClock())) {
                return "INCONSISTENT_STATE";
            }

            _server.getVectorClock().increment(_server.getId());
            clientVectorClock = _server.getVectorClock();
            _class.addEnrolledStudent(_id, _name);
        }

        return "OK";
    }
}