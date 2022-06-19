package pt.ulisboa.tecnico.classes.classserver.Commands;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.VectorClock;
import pt.ulisboa.tecnico.classes.classserver.Class;
import pt.ulisboa.tecnico.classes.classserver.ServerStruct;

public class CloseEnrollmentsCommand extends Command {

    private ServerStruct server;
    private VectorClock clientVectorClock;

    public CloseEnrollmentsCommand(ServerStruct server1, VectorClock clientVectorClock) {
        server = server1;
        this.clientVectorClock = clientVectorClock;
    }

    @Override
    public String execute() {
        if (server.isDebugOn()) {
            System.err.print(Constants.SERVER_CLOSE_ENROLLMENT);
        }

        synchronized (server) {
            Class _class = server.getServerClass();

            if (!server.isActive()) {
                return "INACTIVE_SERVER";
            }

            if (!server.isPrimary()){
                return "WRITING_NOT_SUPPORTED";
            }

            if (!_class.getEnrollmentStatus()) {
                return "ENROLLMENTS_ALREADY_CLOSED";
            }

            if (server.isGossipOccurring()) {
                return "GOSSIP_OCCURRING";
            }

            if (!clientVectorClock.happenedBefore(server.getVectorClock())) {
                return "INCONSISTENT_STATE";
            }

            server.getVectorClock().increment(server.getId());
            clientVectorClock = server.getVectorClock();
            _class.setEnrollmentStatus(false);
        }

        return "OK";
    }
}
