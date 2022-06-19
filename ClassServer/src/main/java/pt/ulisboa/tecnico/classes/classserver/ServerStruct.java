package pt.ulisboa.tecnico.classes.classserver;

import pt.ulisboa.tecnico.classes.VectorClock;

import java.util.ArrayList;
import java.util.HashMap;

public class ServerStruct {

    private String _host;
    private int _port;
    private int _id;                            // Server identifier
    private int _operationN = 0;                // Number of operations done
    private boolean _primary;                   // Indicates whether the server is primary or secondary
    private boolean _active;                    // Server is able to receive all types of requests or not
    private boolean _debugOn;                   // Debug mode active or not
    private boolean _gossipOn;                  // Gossip active or not
    private boolean _gossipOccurring = false;   // Gossip occurring or not
    private VectorClock _vectorClock;
    private UpdateLog _updateLog = new UpdateLog();  // Log of the operations being issued
    private HashMap<Integer, ArrayList<Integer>> _operationTable;
    private Class _class = new Class();

    public ServerStruct(String host, int port, int id, boolean primary, boolean active, boolean debugOn, boolean gossipOn) {
        this._host = host;
        this._port = port;
        this._id = id;
        this._primary = primary;
        this._active = active;
        this._debugOn = debugOn;
        this._gossipOn = gossipOn;
        this._vectorClock = new VectorClock(id);
        this._operationTable = new HashMap<Integer, ArrayList<Integer>>();
        this._operationTable.put(id, new ArrayList<Integer>());
    }

    public String getHost() {
        return this._host;
    }

    public void setHost(String host) {
        this._host = host;
    }

    public int getPort() {
        return this._port;
    }

    public void setPort(int port) {
        this._port = port;
    }

    public int getId() {
        return this._id;
    }

    public int getOperationN() { return this._operationN; }

    public void incrementOperationN() { this._operationN++; }

    public boolean isPrimary() { return this._primary; }

    public void setPrimary(boolean primary) { this._primary = primary; }

    public boolean isActive() {
        return this._active;
    }

    public void setActive(boolean active) {
        this._active = active;
    }

    public boolean isDebugOn() {
        return this._debugOn;
    }

    public void setDebugMode(boolean debugOn) {
        this._debugOn = debugOn;
    }

    public boolean isGossipOn() {
        return this._gossipOn;
    }

    public void setGossipMode(boolean gossipOn) {
        this._gossipOn= gossipOn;
    }
    public boolean isGossipOccurring() { return this._gossipOccurring; }

    public void setGossipOccurring(boolean gossipOccurring) { this._gossipOccurring = gossipOccurring; }

    public VectorClock getVectorClock() {
        return this._vectorClock;
    }

    public UpdateLog getUpdateLog() {
        return this._updateLog;
    }

    public HashMap<Integer, ArrayList<Integer>> getOperationTable() { return this._operationTable; }

    public Class getServerClass() {
        return this._class;
    }

    public void setClass(Class class_) {
        this._class = class_;
    }

    @Override
    public String toString() {
        return "Server {" +
                "id: " + _id +
                ", number of operations done: " + _operationN +
                ", active: " + _active +
                ", debug: " + _debugOn +
                ", gossip: " + _gossipOn +
                ", vector clock: " + _vectorClock +
                ", update log: " + _updateLog +
                ", class: " + _class + " }\n";
    }
}
