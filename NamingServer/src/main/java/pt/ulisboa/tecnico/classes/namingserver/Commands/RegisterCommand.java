package pt.ulisboa.tecnico.classes.namingserver.Commands;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.namingserver.NamingServices;
import pt.ulisboa.tecnico.classes.namingserver.ServerEntry;
import pt.ulisboa.tecnico.classes.namingserver.ServiceEntry;

import java.util.ArrayList;

public class RegisterCommand extends Command {

    private NamingServices _namingServices;
    private String _serviceName;
    private String _host;
    private int _port;
    private ArrayList<String> _qualifiers;
    private int _id;

    public RegisterCommand(NamingServices namingServices, String serviceName, String host, int port,
                           ArrayList<String> qualifiers, int id) {
        _namingServices = namingServices;
        _serviceName = serviceName;
        _host = host;
        _port = port;
        _qualifiers = qualifiers;
        _id = id;
    }

    @Override
    public String execute() {

        if (_namingServices.isDebugOn()) {
            System.err.println(Constants.SERVER_REGISTER + "server name: "
            + _host + "\n" + "port: " + _port + "\n");
        }

        ServerEntry serverEntry = new ServerEntry(_host, _port, _qualifiers, _id);

        synchronized (_namingServices) {
            if (_namingServices.containsServiceEntry(_serviceName)) {
                _namingServices.addServerEntryToServiceEntry(_serviceName, serverEntry);
            }

            else {
                _namingServices.addNewServiceEntry(_serviceName, serverEntry);
            }
        }

        return "OK";
    }
}
