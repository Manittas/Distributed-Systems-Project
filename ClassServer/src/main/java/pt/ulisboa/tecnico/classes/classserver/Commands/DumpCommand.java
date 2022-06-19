package pt.ulisboa.tecnico.classes.classserver.Commands;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.classserver.ServerStruct;

public class DumpCommand extends Command {

    private ServerStruct _server;

    public DumpCommand(ServerStruct server) {
        this._server = server;
    }

    @Override
    public String execute() {
        if (_server.isDebugOn()) {
            System.err.print(Constants.SERVER_DUMP);
        }

        if (!_server.isActive()) {
            return "INACTIVE_SERVER";
        }

        synchronized (_server) {
        }

        return "OK";
    }
}
