package pt.ulisboa.tecnico.classes.namingserver.Commands;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.namingserver.NamingServices;
import pt.ulisboa.tecnico.classes.namingserver.ServerEntry;

import java.util.ArrayList;

public class DeleteCommand extends Command{

    private NamingServices _namingServices;
    private String _serviceName;
    private String _host;
    private int _port;

    public DeleteCommand(NamingServices namingServices, String serviceName, String host, int port) {
        _namingServices = namingServices;
        _serviceName = serviceName;
        _host = host;
        _port = port;
    }

    @Override
    public String execute() {
        if (_namingServices.isDebugOn()) {
            System.err.println(Constants.SERVER_DELETE + "server name: "
                    + _host + "\n" + "port: " + _port + "\n");
        }

        synchronized (_namingServices) {
            if (!_namingServices.containsServiceEntry(_serviceName)) {
                return "NON_EXISTING_SERVER";
            }

            ArrayList<ServerEntry> serverEntries = _namingServices.getServiceEntry(_serviceName).getServerEntries();

            for (ServerEntry serverEntry : serverEntries) {
                if (serverEntry.getHost().equals(_host) && serverEntry.getPort() == _port) {
                    int index = this._namingServices.getServerList().indexOf(serverEntry.getId());
                    this._namingServices.getServerList().remove(index);
                    serverEntries.remove(serverEntry);
                    return "OK";
                }
            }
        }

        return "NON_EXISTING_SERVER";
    }
}
