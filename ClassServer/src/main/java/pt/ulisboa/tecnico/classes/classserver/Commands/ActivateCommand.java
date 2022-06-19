package pt.ulisboa.tecnico.classes.classserver.Commands;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.classserver.ServerStruct;

public class ActivateCommand extends Command {

    private ServerStruct _server;

    public ActivateCommand(ServerStruct server) {
        this._server = server;
    }

    @Override
    public String execute() {
        if (_server.isDebugOn()) {
            System.err.print(Constants.SERVER_ACTIVE);
        }

        synchronized (_server) {
            _server.setActive(true);
        }

        return "OK";
    }
}
