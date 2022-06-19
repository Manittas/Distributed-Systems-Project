package pt.ulisboa.tecnico.classes.contract.classserver;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.1)",
    comments = "Source: ClassServer_ClassServer.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ClassServerServiceGrpc {

  private ClassServerServiceGrpc() {}

  public static final String SERVICE_NAME = "pt.ulisboa.tecnico.classes.contract.classserver.ClassServerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsRequest,
      pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsResponse> getPropagateModificationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "propagateModifications",
      requestType = pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsRequest.class,
      responseType = pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsRequest,
      pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsResponse> getPropagateModificationsMethod() {
    io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsRequest, pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsResponse> getPropagateModificationsMethod;
    if ((getPropagateModificationsMethod = ClassServerServiceGrpc.getPropagateModificationsMethod) == null) {
      synchronized (ClassServerServiceGrpc.class) {
        if ((getPropagateModificationsMethod = ClassServerServiceGrpc.getPropagateModificationsMethod) == null) {
          ClassServerServiceGrpc.getPropagateModificationsMethod = getPropagateModificationsMethod =
              io.grpc.MethodDescriptor.<pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsRequest, pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "propagateModifications"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ClassServerServiceMethodDescriptorSupplier("propagateModifications"))
              .build();
        }
      }
    }
    return getPropagateModificationsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ClassServerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClassServerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClassServerServiceStub>() {
        @java.lang.Override
        public ClassServerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClassServerServiceStub(channel, callOptions);
        }
      };
    return ClassServerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ClassServerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClassServerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClassServerServiceBlockingStub>() {
        @java.lang.Override
        public ClassServerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClassServerServiceBlockingStub(channel, callOptions);
        }
      };
    return ClassServerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ClassServerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClassServerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClassServerServiceFutureStub>() {
        @java.lang.Override
        public ClassServerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClassServerServiceFutureStub(channel, callOptions);
        }
      };
    return ClassServerServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ClassServerServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void propagateModifications(pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPropagateModificationsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPropagateModificationsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsRequest,
                pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsResponse>(
                  this, METHODID_PROPAGATE_MODIFICATIONS)))
          .build();
    }
  }

  /**
   */
  public static final class ClassServerServiceStub extends io.grpc.stub.AbstractAsyncStub<ClassServerServiceStub> {
    private ClassServerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClassServerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClassServerServiceStub(channel, callOptions);
    }

    /**
     */
    public void propagateModifications(pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPropagateModificationsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ClassServerServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ClassServerServiceBlockingStub> {
    private ClassServerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClassServerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClassServerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsResponse propagateModifications(pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPropagateModificationsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ClassServerServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ClassServerServiceFutureStub> {
    private ClassServerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClassServerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClassServerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsResponse> propagateModifications(
        pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPropagateModificationsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PROPAGATE_MODIFICATIONS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ClassServerServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ClassServerServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PROPAGATE_MODIFICATIONS:
          serviceImpl.propagateModifications((pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsRequest) request,
              (io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.PropagateModificationsResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ClassServerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ClassServerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return pt.ulisboa.tecnico.classes.contract.classserver.ClassServerClassServer.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ClassServerService");
    }
  }

  private static final class ClassServerServiceFileDescriptorSupplier
      extends ClassServerServiceBaseDescriptorSupplier {
    ClassServerServiceFileDescriptorSupplier() {}
  }

  private static final class ClassServerServiceMethodDescriptorSupplier
      extends ClassServerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ClassServerServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ClassServerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ClassServerServiceFileDescriptorSupplier())
              .addMethod(getPropagateModificationsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
