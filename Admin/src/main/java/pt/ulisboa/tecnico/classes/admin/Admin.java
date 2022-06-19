package pt.ulisboa.tecnico.classes.admin;

import java.util.Scanner;

import pt.ulisboa.tecnico.classes.Constants;

import io.grpc.StatusRuntimeException;

public class Admin {
    public static void main(String[] args) {
        System.out.println(Admin.class.getSimpleName());
        System.out.printf("Received %d Argument(s)%n", args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.printf("args[%d] = %s%n", i, args[i]);
        }

        boolean debugMode = false;
        if (args.length > 0 && args[args.length - 1].equals(Constants.DEBUG_FLAG)) {
            debugMode = true;
        }
        else if (args.length > 0) {   // Wrong flag
            System.out.println(Constants.INVALID_ARG);
            System.exit(1);
        }

        try {
            AdminFrontend adminFrontend = new AdminFrontend(debugMode);
            start(adminFrontend);

        } catch (StatusRuntimeException e) {
            System.out.print(Constants.SERVER_UNAVAILABLE);
            System.exit(1);
        }
    }

    public static void start(AdminFrontend adminFrontend) {
        Scanner scanner = new Scanner(System.in);
        
        // Reading the Admin inputs
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            String[] cmd = line.split("\\s+");

            if (cmd.length == 0) {
                System.out.print("\n");
                continue;
            }

            switch (cmd[0]) {
                case Constants.ACTIVATE_CMD:
                    if (validArgs(cmd)) {
                        String serverType = "P";    // Default value

                        if ((cmd.length == 2) && cmd[1].equals(Constants.SECONDARY_SERVER)) {
                            serverType = "S";
                        }

                        adminFrontend.activate(serverType);
                    }
                    break;

                case Constants.DEACTIVATE_CMD:
                    if (validArgs(cmd)) {
                        String serverType = "P";    // Default value

                        if ((cmd.length == 2) && cmd[1].equals(Constants.SECONDARY_SERVER)) {
                            serverType = "S";
                        }

                        adminFrontend.deactivate(serverType);
                    }
                    break;

                case Constants.DUMP_CMD:
                    if (validArgs(cmd)) {
                        String serverType = "P";    // Default value

                        if ((cmd.length == 2) && cmd[1].equals(Constants.SECONDARY_SERVER)) {
                            serverType = "S";
                        }

                        adminFrontend.dump(serverType);
                    }
                    break;

                case Constants.DEACTIVATE_GOSSIP_CMD:
                    if (validArgs(cmd)) {
                        String serverType = "P";    // Default value

                        if ((cmd.length == 2) && cmd[1].equals(Constants.SECONDARY_SERVER)) {
                            serverType = "S";
                        }

                        adminFrontend.deactivateGossip(serverType);
                    }
                    break;
          
                case Constants.ACTIVATE_GOSSIP_CMD:
                    if (validArgs(cmd)) {
                        String serverType = "P";    // Default value

                        if ((cmd.length == 2) && cmd[1].equals(Constants.SECONDARY_SERVER)) {
                            serverType = "S";
                        }

                        adminFrontend.activateGossip(serverType);
                    }
                    break;

                case Constants.GOSSIP_CMD:
                    if (validArgs(cmd)) {
                        String serverType = "P";    // Default value

                        if ((cmd.length == 2) && cmd[1].equals(Constants.SECONDARY_SERVER)) {
                            serverType = "S";
                        }

                        adminFrontend.gossip();
                    }
                    break;

                case (Constants.EXIT_CMD):
                    if (cmd.length > 1) {
                        System.out.println(Constants.INVALID_EXIT_CMD);
                        continue;
                    }

                    adminFrontend.closeChannels();
                    System.exit(0);

                default:
                    System.out.println(Constants.INVALID_CMD);
            }
        }
    }

    private static boolean validArgs(String[] cmd) {
        if (cmd.length == 1) {
            return true;
        }
        else if (cmd.length == 2) {
            if (!Constants.SERVER_TYPES.contains(cmd[1])) {
                System.out.println(Constants.INVALID_SERVER_TYPE);
                return false;
            }
        }
        else {
            System.out.println(Constants.INVALID_ARG_NUMBER);
            return false;
        }

        return true;
    }
}