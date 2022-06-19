package pt.ulisboa.tecnico.classes.namingserver.Commands;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.contract.ClassesDefinitions;
import pt.ulisboa.tecnico.classes.contract.naming.ClassServerNamingServer;
import pt.ulisboa.tecnico.classes.namingserver.NamingServices;
import pt.ulisboa.tecnico.classes.namingserver.ServerEntry;
import pt.ulisboa.tecnico.classes.namingserver.ServiceEntry;

import java.util.ArrayList;

public class LookupCommand extends Command{

    private NamingServices _namingServices;
    private String _serviceName;
    private ArrayList<String> _qualifiers;
    private ArrayList<ServerEntry> _serverEntries;

    public LookupCommand(NamingServices namingServices, String serviceName, ArrayList<String> qualifiers, ArrayList<ServerEntry> serverEntries ) {
        _namingServices = namingServices;
        _serviceName = serviceName;
        _qualifiers = qualifiers;
        _serverEntries = serverEntries;
    }

    @Override
    public String execute() {
        if (_namingServices.isDebugOn()) {
            System.err.println(Constants.SERVER_LOOKUP + _qualifiers + "\n");
        }

        synchronized (_namingServices) {
            if (!_namingServices.containsServiceEntry(_serviceName)) {
                return "NON_EXISTING_SERVER";
            }

            if (_qualifiers.isEmpty()) {
                _serverEntries.addAll(_namingServices.getServiceEntry(_serviceName).getServerEntries());
                return "OK";
            }


            for (ServerEntry entry : _namingServices.getServiceEntry(_serviceName).getServerEntries()) {
                for (String qualifier : _qualifiers) {
                    if (entry.getQualifiers().contains(qualifier)) {
                        _serverEntries.add(entry);
                        break;
                    }
                }
            }
        }

        if (_serverEntries.isEmpty()) {
            return "NON_EXISTING_SERVER";
        }

        else {
            return "OK";
        }
    }
}
