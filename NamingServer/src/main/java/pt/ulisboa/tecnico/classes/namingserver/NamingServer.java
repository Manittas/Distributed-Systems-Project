package pt.ulisboa.tecnico.classes.namingserver;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class NamingServer {

  private static final short ARGS_N = 2;
  private static final short ARGS_N_DEBUG = 3;

  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println(NamingServer.class.getSimpleName());
    System.out.printf("Received %d Argument(s)%n", args.length);
    for (int i = 0; i < args.length; i++) {
      System.out.printf("args[%d] = %s%n", i, args[i]);
    }

    // Invalid number of arguments
    if (args.length != ARGS_N && args.length != ARGS_N_DEBUG) {
      System.out.print("Invalid number of arguments.\n");
      System.exit(1);
    }

    try {
      boolean debugOn = false;

      if (args.length == ARGS_N_DEBUG) {
        if (args[ARGS_N_DEBUG - 1].equals("-debug")) {
          debugOn = true;
        }

        else {
          System.out.println("Invalid argument.\n");
        }
      }

      NamingServices namingServices = new NamingServices(debugOn);

      final BindableService namingServerServiceImpl = new NamingServerServiceImpl(namingServices);
      Server server = ServerBuilder.forPort(Integer.parseInt(args[1])).addService(namingServerServiceImpl).build();

      if (debugOn) {
        System.err.println("\n" + "Naming server started.\n");
      }

      server.start();
      server.awaitTermination();

    } catch (NumberFormatException e) {
      System.out.print("Invalid port specified.\n");
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
