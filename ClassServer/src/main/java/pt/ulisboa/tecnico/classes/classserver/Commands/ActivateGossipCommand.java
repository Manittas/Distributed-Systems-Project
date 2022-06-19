package pt.ulisboa.tecnico.classes.classserver.Commands;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.classserver.ServerStruct;

public class ActivateGossipCommand extends Command {

    private ServerStruct _server;

    public ActivateGossipCommand(ServerStruct server) {
        this._server = server;
    }

    @Override
    public String execute() {
        if (_server.isDebugOn()) {
            System.err.print(Constants.SERVER_ACTIVE);
        }

        synchronized (_server) {
            if (!_server.isActive()) {
                return "INACTIVE_SERVER";
            }

            _server.setGossipMode(true);
        }

        return "OK";
    }
}