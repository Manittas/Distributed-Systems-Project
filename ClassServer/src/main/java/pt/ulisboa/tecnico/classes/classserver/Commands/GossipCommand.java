package pt.ulisboa.tecnico.classes.classserver.Commands;

import pt.ulisboa.tecnico.classes.classserver.Class;
import pt.ulisboa.tecnico.classes.classserver.ClassServerFrontend;
import pt.ulisboa.tecnico.classes.classserver.GossipTask;
import pt.ulisboa.tecnico.classes.classserver.ServerStruct;

public class GossipCommand extends Command {
    ClassServerFrontend serverFrontend;

    public GossipCommand(ClassServerFrontend serverFrontend) { this.serverFrontend = serverFrontend; }

    public String execute() {
        ServerStruct server = serverFrontend.getClassServer();

        synchronized (server) {
            if (!server.isActive()) {
                return "INACTIVE_SERVER";
            }

            if (!server.isGossipOn()) {
                return "GOSSIP_OFF";
            }

            GossipTask task = new GossipTask(serverFrontend);
            task.run();

            return "OK";
        }
    }
}
