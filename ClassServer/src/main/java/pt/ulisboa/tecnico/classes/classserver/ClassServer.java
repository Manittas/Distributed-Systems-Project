package pt.ulisboa.tecnico.classes.classserver;

import java.io.IOException;

import io.grpc.BindableService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.contract.naming.ClassServerNamingServer;
import pt.ulisboa.tecnico.classes.contract.naming.NamingServerServiceGrpc;

import static pt.ulisboa.tecnico.classes.contract.ClassesDefinitions.RegistServer;

public class ClassServer {
  private static final short ARGS_N = 3;        // Number of arguments expected to run the admin program
  private static final short ARGS_N_DEBUG = 4;  // Number of arguments expected to run the admin program, in debug mode

  public static void main(String[] args) {
    System.out.println(ClassServer.class.getSimpleName());
    System.out.printf("Received %d Argument(s)%n", args.length);
    for (int i = 0; i < args.length; i++) {
      System.out.printf("args[%d] = %s%n", i, args[i]);
    }

    // Invalid number of arguments
    if (args.length != ARGS_N && args.length != ARGS_N_DEBUG) {
      System.out.print(Constants.INVALID_ARG_NUMBER);
      System.exit(1);
    }

    // Checking the server type is valid
    else if (!Constants.SERVER_TYPES.contains(args[2])) {
      System.out.print(Constants.INVALID_SERVER_TYPE);
      System.exit(1);
    }

    try {
      boolean isPrimary = args[2].equals(Constants.SERVER_TYPES.get(0));
      boolean debugOn = false;

      if (args.length == ARGS_N_DEBUG) {
        if (args[ARGS_N_DEBUG - 1].equals(Constants.DEBUG_FLAG)) {
          debugOn = true;
        }

        // Debug argument but not recognized
        else {
          System.out.println(Constants.INVALID_ARG);
        }
      }

      String hostNamingServer = "localhost";
      int portNamingServer = 5001;
      ManagedChannel channelNamingServer = ManagedChannelBuilder.forAddress(hostNamingServer, portNamingServer).usePlaintext().build();
      NamingServerServiceGrpc.NamingServerServiceBlockingStub stubNamingServer = NamingServerServiceGrpc.newBlockingStub(channelNamingServer);

      ClassServerNamingServer.RegisterRequest requestNamingServer = ClassServerNamingServer.RegisterRequest.newBuilder()
              .setServiceName(Constants.TURMAS_SERVICE)
              .setServer(RegistServer.newBuilder().setHost(args[0]).setPort(Integer.parseInt(args[1])).build())
              .addQualifiers(args[2])
              .build();
      ClassServerNamingServer.RegisterResponse responseNamingServer = stubNamingServer.register(requestNamingServer);

      ServerStruct serverStruct = new ServerStruct(args[0], Integer.parseInt(args[1]), responseNamingServer.getServerId(),
              isPrimary, true, debugOn, true);
      ClassServerFrontend serverFrontend = new ClassServerFrontend(serverStruct);

      final BindableService adminImpl = new AdminServiceImpl(serverFrontend);
      final BindableService professorImpl = new ProfessorServiceImpl(serverFrontend);
      final BindableService studentImpl = new StudentServiceImpl(serverFrontend);
      final BindableService serverImpl = new ServerServiceImpl(serverFrontend);

      Server server = ServerBuilder.forPort(Integer.parseInt(args[1]))
                .addService(adminImpl)
                .addService(professorImpl)
                .addService(studentImpl)
                .addService(serverImpl)
                .build();

      if (serverStruct.isDebugOn()) {
        System.err.println("\n" + Constants.SERVER_STARTED);
      }

      server.start();

      Runtime.getRuntime().addShutdownHook(new Thread() {
        public void run() {
          try {
            Thread.sleep(200);

            ClassServerNamingServer.DeleteRequest requestNamingServer = ClassServerNamingServer.DeleteRequest.newBuilder()
                    .setServiceName(Constants.TURMAS_SERVICE)
                    .setServer(RegistServer.newBuilder().setHost(args[0]).setPort(Integer.parseInt(args[1])).build())
                    .build();
            ClassServerNamingServer.DeleteResponse responseNamingServer = stubNamingServer.delete(requestNamingServer);
            channelNamingServer.shutdown();

            serverFrontend.closeChannels();
            server.shutdown().awaitTermination();

          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
          }
        }
      });

      server.awaitTermination();

    } catch (NumberFormatException e) { // Invalid port number
      System.out.print(Constants.INVALID_PORT);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
