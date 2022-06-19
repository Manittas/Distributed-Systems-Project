package pt.ulisboa.tecnico.classes.classserver.Commands;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.VectorClock;
import pt.ulisboa.tecnico.classes.classserver.Class;
import pt.ulisboa.tecnico.classes.classserver.ServerStruct;
import pt.ulisboa.tecnico.classes.contract.ClassesDefinitions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OpenEnrollmentsCommand extends Command{
    private int capacity;
    private ServerStruct server;
    private VectorClock clientVectorClock;

    public OpenEnrollmentsCommand(int number, ServerStruct server1, VectorClock clientVectorClock) {
        capacity = number;
        server = server1;
        this.clientVectorClock = clientVectorClock;
    }

    public int getCapacity() { return this.capacity; }

    @Override
    public String execute() {
        if (server.isDebugOn()) {
            System.err.println(Constants.SERVER_OPEN_ENROLLMENT + capacity);
        }

        synchronized (server) {
            Class _class = server.getServerClass();

            if (!server.isActive()) {
                return "INACTIVE_SERVER";
            }

            if (!server.isPrimary()){
                return "WRITING_NOT_SUPPORTED";
            }

            if (_class.getEnrollmentStatus()) {
                return "ENROLLMENTS_ALREADY_OPENED";
            }

            if (capacity < _class.getEnrolledStudents().size()) {
                return "FULL_CLASS";
            }

            if (server.isGossipOccurring()) {
                return "GOSSIP_OCCURRING";
            }

            if (!clientVectorClock.happenedBefore(server.getVectorClock())) {
                return "INCONSISTENT_STATE";
            }

            server.getVectorClock().increment(server.getId());
            clientVectorClock = server.getVectorClock();
            _class.setEnrollmentStatus(true);
            _class.setCapacity(capacity);
        }

        return "OK";
    }
}
