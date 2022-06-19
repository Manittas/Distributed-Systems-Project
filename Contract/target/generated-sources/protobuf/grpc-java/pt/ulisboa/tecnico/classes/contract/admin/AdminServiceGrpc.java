package pt.ulisboa.tecnico.classes.contract.admin;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.1)",
    comments = "Source: Admin_ClassServer.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AdminServiceGrpc {

  private AdminServiceGrpc() {}

  public static final String SERVICE_NAME = "pt.ulisboa.tecnico.classes.contract.admin.AdminService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateRequest,
      pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateResponse> getActivateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "activate",
      requestType = pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateRequest.class,
      responseType = pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateRequest,
      pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateResponse> getActivateMethod() {
    io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateRequest, pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateResponse> getActivateMethod;
    if ((getActivateMethod = AdminServiceGrpc.getActivateMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getActivateMethod = AdminServiceGrpc.getActivateMethod) == null) {
          AdminServiceGrpc.getActivateMethod = getActivateMethod =
              io.grpc.MethodDescriptor.<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateRequest, pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "activate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("activate"))
              .build();
        }
      }
    }
    return getActivateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateRequest,
      pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateResponse> getDeactivateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deactivate",
      requestType = pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateRequest.class,
      responseType = pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateRequest,
      pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateResponse> getDeactivateMethod() {
    io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateRequest, pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateResponse> getDeactivateMethod;
    if ((getDeactivateMethod = AdminServiceGrpc.getDeactivateMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getDeactivateMethod = AdminServiceGrpc.getDeactivateMethod) == null) {
          AdminServiceGrpc.getDeactivateMethod = getDeactivateMethod =
              io.grpc.MethodDescriptor.<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateRequest, pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deactivate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("deactivate"))
              .build();
        }
      }
    }
    return getDeactivateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipRequest,
      pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipResponse> getActivateGossipMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "activateGossip",
      requestType = pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipRequest.class,
      responseType = pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipRequest,
      pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipResponse> getActivateGossipMethod() {
    io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipRequest, pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipResponse> getActivateGossipMethod;
    if ((getActivateGossipMethod = AdminServiceGrpc.getActivateGossipMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getActivateGossipMethod = AdminServiceGrpc.getActivateGossipMethod) == null) {
          AdminServiceGrpc.getActivateGossipMethod = getActivateGossipMethod =
              io.grpc.MethodDescriptor.<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipRequest, pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "activateGossip"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("activateGossip"))
              .build();
        }
      }
    }
    return getActivateGossipMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipRequest,
      pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipResponse> getDeactivateGossipMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deactivateGossip",
      requestType = pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipRequest.class,
      responseType = pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipRequest,
      pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipResponse> getDeactivateGossipMethod() {
    io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipRequest, pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipResponse> getDeactivateGossipMethod;
    if ((getDeactivateGossipMethod = AdminServiceGrpc.getDeactivateGossipMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getDeactivateGossipMethod = AdminServiceGrpc.getDeactivateGossipMethod) == null) {
          AdminServiceGrpc.getDeactivateGossipMethod = getDeactivateGossipMethod =
              io.grpc.MethodDescriptor.<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipRequest, pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deactivateGossip"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("deactivateGossip"))
              .build();
        }
      }
    }
    return getDeactivateGossipMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipRequest,
      pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipResponse> getGossipMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "gossip",
      requestType = pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipRequest.class,
      responseType = pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipRequest,
      pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipResponse> getGossipMethod() {
    io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipRequest, pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipResponse> getGossipMethod;
    if ((getGossipMethod = AdminServiceGrpc.getGossipMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getGossipMethod = AdminServiceGrpc.getGossipMethod) == null) {
          AdminServiceGrpc.getGossipMethod = getGossipMethod =
              io.grpc.MethodDescriptor.<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipRequest, pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "gossip"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("gossip"))
              .build();
        }
      }
    }
    return getGossipMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpRequest,
      pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpResponse> getDumpMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "dump",
      requestType = pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpRequest.class,
      responseType = pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpRequest,
      pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpResponse> getDumpMethod() {
    io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpRequest, pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpResponse> getDumpMethod;
    if ((getDumpMethod = AdminServiceGrpc.getDumpMethod) == null) {
      synchronized (AdminServiceGrpc.class) {
        if ((getDumpMethod = AdminServiceGrpc.getDumpMethod) == null) {
          AdminServiceGrpc.getDumpMethod = getDumpMethod =
              io.grpc.MethodDescriptor.<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpRequest, pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "dump"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AdminServiceMethodDescriptorSupplier("dump"))
              .build();
        }
      }
    }
    return getDumpMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AdminServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AdminServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AdminServiceStub>() {
        @java.lang.Override
        public AdminServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AdminServiceStub(channel, callOptions);
        }
      };
    return AdminServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AdminServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AdminServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AdminServiceBlockingStub>() {
        @java.lang.Override
        public AdminServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AdminServiceBlockingStub(channel, callOptions);
        }
      };
    return AdminServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AdminServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AdminServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AdminServiceFutureStub>() {
        @java.lang.Override
        public AdminServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AdminServiceFutureStub(channel, callOptions);
        }
      };
    return AdminServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class AdminServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void activate(pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getActivateMethod(), responseObserver);
    }

    /**
     */
    public void deactivate(pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeactivateMethod(), responseObserver);
    }

    /**
     */
    public void activateGossip(pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getActivateGossipMethod(), responseObserver);
    }

    /**
     */
    public void deactivateGossip(pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeactivateGossipMethod(), responseObserver);
    }

    /**
     */
    public void gossip(pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGossipMethod(), responseObserver);
    }

    /**
     */
    public void dump(pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDumpMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getActivateMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateRequest,
                pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateResponse>(
                  this, METHODID_ACTIVATE)))
          .addMethod(
            getDeactivateMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateRequest,
                pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateResponse>(
                  this, METHODID_DEACTIVATE)))
          .addMethod(
            getActivateGossipMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipRequest,
                pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipResponse>(
                  this, METHODID_ACTIVATE_GOSSIP)))
          .addMethod(
            getDeactivateGossipMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipRequest,
                pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipResponse>(
                  this, METHODID_DEACTIVATE_GOSSIP)))
          .addMethod(
            getGossipMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipRequest,
                pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipResponse>(
                  this, METHODID_GOSSIP)))
          .addMethod(
            getDumpMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpRequest,
                pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpResponse>(
                  this, METHODID_DUMP)))
          .build();
    }
  }

  /**
   */
  public static final class AdminServiceStub extends io.grpc.stub.AbstractAsyncStub<AdminServiceStub> {
    private AdminServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdminServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AdminServiceStub(channel, callOptions);
    }

    /**
     */
    public void activate(pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getActivateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deactivate(pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeactivateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void activateGossip(pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getActivateGossipMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deactivateGossip(pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeactivateGossipMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void gossip(pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGossipMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void dump(pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDumpMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AdminServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<AdminServiceBlockingStub> {
    private AdminServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdminServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AdminServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateResponse activate(pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getActivateMethod(), getCallOptions(), request);
    }

    /**
     */
    public pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateResponse deactivate(pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeactivateMethod(), getCallOptions(), request);
    }

    /**
     */
    public pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipResponse activateGossip(pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getActivateGossipMethod(), getCallOptions(), request);
    }

    /**
     */
    public pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipResponse deactivateGossip(pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeactivateGossipMethod(), getCallOptions(), request);
    }

    /**
     */
    public pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipResponse gossip(pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGossipMethod(), getCallOptions(), request);
    }

    /**
     */
    public pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpResponse dump(pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDumpMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AdminServiceFutureStub extends io.grpc.stub.AbstractFutureStub<AdminServiceFutureStub> {
    private AdminServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdminServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AdminServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateResponse> activate(
        pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getActivateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateResponse> deactivate(
        pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeactivateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipResponse> activateGossip(
        pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getActivateGossipMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipResponse> deactivateGossip(
        pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeactivateGossipMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipResponse> gossip(
        pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGossipMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpResponse> dump(
        pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDumpMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ACTIVATE = 0;
  private static final int METHODID_DEACTIVATE = 1;
  private static final int METHODID_ACTIVATE_GOSSIP = 2;
  private static final int METHODID_DEACTIVATE_GOSSIP = 3;
  private static final int METHODID_GOSSIP = 4;
  private static final int METHODID_DUMP = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AdminServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AdminServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ACTIVATE:
          serviceImpl.activate((pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateRequest) request,
              (io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateResponse>) responseObserver);
          break;
        case METHODID_DEACTIVATE:
          serviceImpl.deactivate((pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateRequest) request,
              (io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateResponse>) responseObserver);
          break;
        case METHODID_ACTIVATE_GOSSIP:
          serviceImpl.activateGossip((pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipRequest) request,
              (io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.ActivateGossipResponse>) responseObserver);
          break;
        case METHODID_DEACTIVATE_GOSSIP:
          serviceImpl.deactivateGossip((pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipRequest) request,
              (io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DeactivateGossipResponse>) responseObserver);
          break;
        case METHODID_GOSSIP:
          serviceImpl.gossip((pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipRequest) request,
              (io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.GossipResponse>) responseObserver);
          break;
        case METHODID_DUMP:
          serviceImpl.dump((pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpRequest) request,
              (io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.DumpResponse>) responseObserver);
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

  private static abstract class AdminServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AdminServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return pt.ulisboa.tecnico.classes.contract.admin.AdminClassServer.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AdminService");
    }
  }

  private static final class AdminServiceFileDescriptorSupplier
      extends AdminServiceBaseDescriptorSupplier {
    AdminServiceFileDescriptorSupplier() {}
  }

  private static final class AdminServiceMethodDescriptorSupplier
      extends AdminServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AdminServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (AdminServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AdminServiceFileDescriptorSupplier())
              .addMethod(getActivateMethod())
              .addMethod(getDeactivateMethod())
              .addMethod(getActivateGossipMethod())
              .addMethod(getDeactivateGossipMethod())
              .addMethod(getGossipMethod())
              .addMethod(getDumpMethod())
              .build();
        }
      }
    }
    return result;
  }
}
