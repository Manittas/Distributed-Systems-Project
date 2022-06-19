package pt.ulisboa.tecnico.classes.classserver.Commands;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.VectorClock;
import pt.ulisboa.tecnico.classes.classserver.ServerStruct;

public class ListClassCommand extends Command {

    private ServerStruct _server;
    private VectorClock clientVectorClock;

    public ListClassCommand(ServerStruct server, VectorClock clientVectorClock) {
        this._server = server;
        this.clientVectorClock = clientVectorClock;
    }

    @Override
    public String execute() {

        if (_server.isDebugOn()) {
            System.err.print(Constants.SERVER_LIST);
        }

        synchronized (_server) {

            if (!_server.isActive()) {
                return "INACTIVE_SERVER";
            }

            if (!clientVectorClock.happenedBefore(_server.getVectorClock())) {
                return "INCONSISTENT_STATE";
            }

            _server.getVectorClock().increment(_server.getId());
            clientVectorClock = _server.getVectorClock();
        }

        return "OK";
    }
}