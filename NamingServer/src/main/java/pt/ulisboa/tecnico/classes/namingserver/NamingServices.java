package pt.ulisboa.tecnico.classes.namingserver;

import java.sql.Array;
import java.util.HashMap;
import java.util.ArrayList;

public class NamingServices {
    private HashMap<String, ServiceEntry> serviceEntries;
    private boolean debugOn;
    private int serverN = 0;
    private ArrayList<Integer> serverList;

    public NamingServices(boolean debugOn) {
        this.debugOn = debugOn;
        this.serviceEntries = new HashMap<String, ServiceEntry>();
        this.serverList = new ArrayList<Integer>();
    }

    public int getServerN() {
        return this.serverN;
    }
    public ArrayList<Integer> getServerList() {
        return serverList;
    }

    public void incrementServerN() {
        this.serverN++;
    }

    public void addServiceEntry(String serviceName, ServiceEntry serviceEntry) {
        serviceEntries.put(serviceName, serviceEntry);
    }

    public ServiceEntry getServiceEntry(String serviceName) {
        return serviceEntries.get(serviceName);
    }

    public boolean containsServiceEntry(String serviceName) {
        return serviceEntries.containsKey(serviceName);
    }

    public void removeServiceEntry(String serviceName) {
        serviceEntries.remove(serviceName);
    }

    public void addServerEntryToServiceEntry(String serviceName, ServerEntry serverEntry) {
        getServiceEntry(serviceName).addServer(serverEntry);
        this.serverList.add(serverEntry.getId());
    }

    public void addNewServiceEntry(String serviceName, ServerEntry serverEntry) {
        ServiceEntry serviceEntry = new ServiceEntry(serviceName);
        serviceEntry.addServer(serverEntry);
        serviceEntries.put(serviceName, serviceEntry);
        this.serverList.add(serverEntry.getId());
    }

    public boolean isDebugOn() {
        return debugOn;
    }
}
