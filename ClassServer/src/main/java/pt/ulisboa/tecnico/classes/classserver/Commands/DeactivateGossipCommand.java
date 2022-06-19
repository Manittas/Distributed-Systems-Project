package pt.ulisboa.tecnico.classes.classserver.Commands;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.classserver.ServerStruct;

public class DeactivateGossipCommand extends Command {

    private ServerStruct _server;

    public DeactivateGossipCommand(ServerStruct server) {
        this._server = server;
    }

    @Override
    public String execute() {
        if (_server.isDebugOn()) {
            System.err.print(Constants.SERVER_INACTIVE);
        }

        synchronized (_server) {
            if (!_server.isActive()) {
                return "INACTIVE_SERVER";
            }
            _server.setGossipMode(false);
        }

        return "OK";
    }
}