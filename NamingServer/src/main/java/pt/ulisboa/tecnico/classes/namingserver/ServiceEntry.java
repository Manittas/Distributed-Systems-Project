package pt.ulisboa.tecnico.classes.namingserver;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ServiceEntry {
    private String serviceName;
    private ArrayList<ServerEntry> serverEntries;

    public ServiceEntry(String serviceName) {
        setServiceName(serviceName);
        setServerEntries(new ArrayList<>());
    }

    public ServiceEntry(String serviceName, ArrayList<ServerEntry> serverEntries) {
        setServiceName(serviceName);
        setServerEntries(serverEntries);
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ArrayList<ServerEntry> getServerEntries() {
        return this.serverEntries;
    }

    public void setServerEntries(ArrayList<ServerEntry> serverEntries) {
        this.serverEntries = serverEntries;
    }

    public void addServer(ServerEntry server) {
        this.serverEntries.add(server);
    }

    public void removeServer(ServerEntry server) {
        this.serverEntries.remove(server);
    }

    public ArrayList<ServerEntry> filterServerEntries(ArrayList<String> qualifiers) {
        ArrayList<ServerEntry> entries = new ArrayList<ServerEntry>();

        for (ServerEntry entry : serverEntries) {
            for (String qualifier : entry.getQualifiers()) {
                if (qualifiers.contains(qualifier)) {
                    entries.add(entry);
                    break;
                }
            }
        }

        return entries;
    }

    public ServerEntry getServerEntry(ArrayList<String> qualifiers) {
        for (ServerEntry serverEntry : serverEntries) {
            if (serverEntry.getQualifiers().equals(qualifiers)) {
                return serverEntry;
            }
        }

        return null;
    }
}
