package pt.ulisboa.tecnico.classes.classserver.Commands;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.classserver.ServerStruct;

public class DeactivateCommand extends Command {

    private ServerStruct _server;

    public DeactivateCommand(ServerStruct server) {
        this._server = server;
    }

    @Override
    public String execute() {
        if (_server.isDebugOn()) {
            System.err.print(Constants.SERVER_INACTIVE);
        }

        synchronized (_server) {
            _server.setActive(false);
        }

        return "OK";
    }
}
