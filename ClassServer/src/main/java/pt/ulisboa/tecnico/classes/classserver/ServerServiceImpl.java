package pt.ulisboa.tecnico.classes.classserver;

import io.grpc.stub.StreamObserver;
import pt.ulisboa.tecnico.classes.Constants;
import pt.ulisboa.tecnico.classes.Stringify;
import pt.ulisboa.tecnico.classes.VectorClock;
import pt.ulisboa.tecnico.classes.classserver.Commands.PropagateModificationsCommand;

import pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer;
import pt.ulisboa.tecnico.classes.contract.classserver.ClassServerServiceGrpc;
import pt.ulisboa.tecnico.classes.contract.ClassesDefinitions;

public class ServerServiceImpl extends ClassServerServiceGrpc.ClassServerServiceImplBase {
    private ClassServerFrontend serverFrontend;

    public ServerServiceImpl(ClassServerFrontend serverFrontend) {
        this.serverFrontend = serverFrontend;
    }

    // Applies the modifications done in other servers, resulting in a coherent state for all servers involved
    public void propagateModifications(ClassServerClassServer.PropagateModificationsRequest request,
                                       StreamObserver<ClassServerClassServer.PropagateModificationsResponse> responseObserver) {
        if (this.serverFrontend.getClassServer().isDebugOn()) {
            System.err.println(Constants.UPDATING_STATE);
        }

        // Constructing the vector clock received
        VectorClock newVectorClock = new VectorClock(request.getServerId());
        for (ClassesDefinitions.VectorClockMessage message : request.getVectorClockList()) {
            newVectorClock.setClock(message.getId(), message.getValue());
        }

        // Constructing the update log received
        UpdateLog newUpdateLog = new UpdateLog();
        for (String cmd : request.getUpdateLogList()) {
            newUpdateLog.add(cmd);
        }

        synchronized (this.serverFrontend) {
            this.serverFrontend.getVectorClocks().put(request.getServerId(), newVectorClock);
            this.serverFrontend.getUpdateLogs().put(request.getServerId(), newUpdateLog);
        }

        ClassServerClassServer.PropagateModificationsResponse response = ClassServerClassServer.PropagateModificationsResponse.newBuilder()
                .setCode(ClassesDefinitions.ResponseCode.OK)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
