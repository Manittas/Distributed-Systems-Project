package pt.ulisboa.tecnico.classes.namingserver;

import java.util.ArrayList;
import java.util.List;

public class ServerEntry {
    private String host;
    private int port;
    private ArrayList<String> qualifiers;
    private int id;

    public ServerEntry(String host, int port, ArrayList<String> qualifiers, int id) {
        setHost(host);
        setPort(port);
        setQualifiers(qualifiers);
        setId(id);
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ArrayList<String> getQualifiers() {
        return this.qualifiers;
    }

    public void setQualifiers(ArrayList<String> qualifiers) {
        this.qualifiers = qualifiers;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
