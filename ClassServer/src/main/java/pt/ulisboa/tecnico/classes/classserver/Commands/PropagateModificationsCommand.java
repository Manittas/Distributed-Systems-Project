package pt.ulisboa.tecnico.classes.classserver.Commands;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.classserver.ServerStruct;
import pt.ulisboa.tecnico.classes.VectorClock;
import pt.ulisboa.tecnico.classes.classserver.UpdateLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PropagateModificationsCommand extends Command {
    private ServerStruct server;
    private HashMap<Integer, VectorClock> vectorClocks;
    private HashMap<Integer, UpdateLog> updateLogs;

    public PropagateModificationsCommand(ServerStruct server, HashMap<Integer, VectorClock> vectorClocks, HashMap<Integer, UpdateLog> updateLogs) {
        this.server = server;
        this.vectorClocks = vectorClocks;
        this.updateLogs = updateLogs;
    }

    // Computes a coherent state for the server
    public String execute() {
        boolean statusInferred = false;
        boolean updatedEnrollmentStatus = server.getServerClass().getEnrollmentStatus();
        int updatedCapacity = server.getServerClass().getCapacity();
        HashMap<String, String> updatedEnrolled = this.server.getServerClass().getEnrolledStudents();
        HashMap<String, String> updatedDiscarded = this.server.getServerClass().getNotEnrolledStudents();

        ArrayList<Map.Entry<Integer, UpdateLog>> sortedUpdateLogs = sortUpdateLogs(vectorClocks, updateLogs);

        if (server.isDebugOn()) {
            System.err.println("Vector clocks:\n" + this.vectorClocks + "\n");
            System.err.println("Update Logs:\n" + this.updateLogs + "\n");
        }

        for (Map.Entry<Integer, UpdateLog> entry : sortedUpdateLogs) {
            if (!server.getOperationTable().containsKey(entry.getKey())) {
                server.getOperationTable().put(entry.getKey(), new ArrayList<Integer>());
            }
        }

        // Computing the coherent enrollment status and capacity of the class
        // The status and capacity are established by the most updated replica open/close operations
        // Note: Local update logs keep an absolute order of what happened in their replica (0 -> first operation, ...)
        for (int i = (sortedUpdateLogs.size() - 1); i >= 0; i--) {
            int serverId = sortedUpdateLogs.get(i).getKey();
            UpdateLog updateLog = sortedUpdateLogs.get(i).getValue();

            for (String cmd : updateLog.getLog()) {
                String[] cmdSplit = cmd.split(" ");

                if (cmdSplit[1].equals(Constants.OPEN_ENROLLMENTS_CMD) &&
                        !server.getOperationTable().get(serverId).contains(Integer.parseInt(cmdSplit[0]))) {
                    updatedEnrollmentStatus = true;
                    updatedCapacity = Integer.parseInt(cmdSplit[2]);
                    statusInferred = true;

                    server.getOperationTable().get(serverId).add(Integer.parseInt(cmdSplit[0]));
                }

                else if (cmdSplit[1].equals(Constants.CLOSE_ENROLLMENTS_CMD) &&
                        !server.getOperationTable().get(serverId).contains(Integer.parseInt(cmdSplit[0]))) {
                    updatedEnrollmentStatus = false;
                    statusInferred = true;

                    server.getOperationTable().get(serverId).add(Integer.parseInt(cmdSplit[0]));
                }
            }

            if (statusInferred) {
                break;
            }
        }

        if (updatedCapacity != 0) {
            // Computing the students that get enrolled or discarded in the class
            for (int i = (sortedUpdateLogs.size() - 1); i >= 0; i--) {
                int serverId = sortedUpdateLogs.get(i).getKey();
                UpdateLog updateLog = sortedUpdateLogs.get(i).getValue();

                for (String cmd : updateLog.getLog()) {
                    String[] cmdSplit = cmd.split(" ");

                    if (cmdSplit[1].equals(Constants.ENROLL_CMD) &&
                            !server.getOperationTable().get(serverId).contains(Integer.parseInt(cmdSplit[0]))) {
                        if (!updatedEnrolled.containsKey(cmdSplit[2])) {
                            updatedEnrolled.put(cmdSplit[2], cmdSplit[3]);
                            updatedDiscarded.remove(cmdSplit[2]);
                        }

                        server.getOperationTable().get(serverId).add(Integer.parseInt(cmdSplit[0]));
                    }

                    else if (cmdSplit[1].equals(Constants.CANCEL_ENROLLMENT_CMD) &&
                            !server.getOperationTable().get(serverId).contains(Integer.parseInt(cmdSplit[0]))) {
                        if (updatedEnrolled.containsKey(cmdSplit[2])) {
                            String name = updatedEnrolled.remove(cmdSplit[2]);
                            updatedDiscarded.put(cmdSplit[2], name);
                        }

                        server.getOperationTable().get(serverId).add(Integer.parseInt(cmdSplit[0]));
                    }
                }
            }

            ArrayList<Map.Entry<String, String>> sortedUpdatedEnrolled = sortEnrolled(updatedEnrolled);

            // Discarding excess students
            while (sortedUpdatedEnrolled.size() > updatedCapacity) {
                Map.Entry<String, String> studentToRemove = sortedUpdatedEnrolled.remove(0);   // Student with the lowest id

                updatedEnrolled.remove(studentToRemove.getKey());
                updatedDiscarded.put(studentToRemove.getKey(), studentToRemove.getValue());
            }
        }

        // Discarding all students if the capacity is 0
        else {
            for (Map.Entry<String, String> entry : updatedEnrolled.entrySet()) {
                if (!updatedDiscarded.containsKey(entry.getKey())) {
                    updatedDiscarded.put(entry.getKey(), entry.getValue());
                }
            }

            updatedEnrolled.clear();
        }

        // Updating the class
        this.server.getServerClass().setEnrollmentStatus(updatedEnrollmentStatus);
        this.server.getServerClass().setCapacity(updatedCapacity);
        this.server.getServerClass().setEnrolledStudents(updatedEnrolled);
        this.server.getServerClass().setNotEnrolledStudents(updatedDiscarded);

        return "OK";
    }

    // Sorts the update logs by their vector clock value.
    private ArrayList<Map.Entry<Integer, UpdateLog>> sortUpdateLogs(HashMap<Integer, VectorClock> vectorClocks,
                                                                    HashMap<Integer, UpdateLog> updateLogs) {
        ArrayList<Map.Entry<Integer, UpdateLog>> sortedUpdateLogs = new ArrayList<Map.Entry<Integer, UpdateLog>>(updateLogs.entrySet());

        for (int i = 0; i < (sortedUpdateLogs.size() - 1); i++) {
            for (int j = 0; j < (sortedUpdateLogs.size() - i - 1); j++) {
                int prevId = sortedUpdateLogs.get(j).getKey();
                int nextId = sortedUpdateLogs.get(j + 1).getKey();

                // In case they are concurrent, the one that stays ahead is the one with the biggest id
                // (prevents non-coherent states due to different orders of the update logs).
                if (vectorClocks.get(prevId).get(prevId) > vectorClocks.get(nextId).get(nextId) ||
                        (vectorClocks.get(prevId).get(prevId).equals(vectorClocks.get(nextId).get(nextId)) && prevId > nextId)) {
                    Map.Entry<Integer, UpdateLog> temp = sortedUpdateLogs.set(j, sortedUpdateLogs.get(j + 1));
                    sortedUpdateLogs.set(j + 1, temp);
                }
            }
        }

        return sortedUpdateLogs;
    }

    // Sorts the enrolled students according to their id.
    private ArrayList<Map.Entry<String, String>> sortEnrolled(HashMap<String, String> enrolled) {
        ArrayList<Map.Entry<String, String>> sortedEnrolled = new ArrayList<Map.Entry<String, String>>(enrolled.entrySet());

        for (int i = 0; i < (sortedEnrolled.size() - 1); i++) {
            for (int j = 0; j < (sortedEnrolled.size() - i - 1); j++) {
                int prevId = Integer.parseInt(sortedEnrolled.get(j).getKey());
                int nextId = Integer.parseInt(sortedEnrolled.get(j + 1).getKey());

                if (prevId > nextId) {
                    Map.Entry<String, String> temp = sortedEnrolled.set(j, sortedEnrolled.get(j + 1));
                    sortedEnrolled.set(j + 1, temp);
                }
            }
        }

        return sortedEnrolled;
    }

}
