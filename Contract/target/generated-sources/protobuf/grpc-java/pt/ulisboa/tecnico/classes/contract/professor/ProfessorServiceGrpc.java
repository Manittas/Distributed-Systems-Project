package pt.ulisboa.tecnico.classes.contract.professor;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.1)",
    comments = "Source: Professor_ClassServer.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ProfessorServiceGrpc {

  private ProfessorServiceGrpc() {}

  public static final String SERVICE_NAME = "pt.ulisboa.tecnico.classes.contract.professor.ProfessorService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsRequest,
      pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsResponse> getOpenEnrollmentsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "openEnrollments",
      requestType = pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsRequest.class,
      responseType = pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsRequest,
      pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsResponse> getOpenEnrollmentsMethod() {
    io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsRequest, pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsResponse> getOpenEnrollmentsMethod;
    if ((getOpenEnrollmentsMethod = ProfessorServiceGrpc.getOpenEnrollmentsMethod) == null) {
      synchronized (ProfessorServiceGrpc.class) {
        if ((getOpenEnrollmentsMethod = ProfessorServiceGrpc.getOpenEnrollmentsMethod) == null) {
          ProfessorServiceGrpc.getOpenEnrollmentsMethod = getOpenEnrollmentsMethod =
              io.grpc.MethodDescriptor.<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsRequest, pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "openEnrollments"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ProfessorServiceMethodDescriptorSupplier("openEnrollments"))
              .build();
        }
      }
    }
    return getOpenEnrollmentsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsRequest,
      pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsResponse> getCloseEnrollmentsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "closeEnrollments",
      requestType = pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsRequest.class,
      responseType = pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsRequest,
      pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsResponse> getCloseEnrollmentsMethod() {
    io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsRequest, pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsResponse> getCloseEnrollmentsMethod;
    if ((getCloseEnrollmentsMethod = ProfessorServiceGrpc.getCloseEnrollmentsMethod) == null) {
      synchronized (ProfessorServiceGrpc.class) {
        if ((getCloseEnrollmentsMethod = ProfessorServiceGrpc.getCloseEnrollmentsMethod) == null) {
          ProfessorServiceGrpc.getCloseEnrollmentsMethod = getCloseEnrollmentsMethod =
              io.grpc.MethodDescriptor.<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsRequest, pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "closeEnrollments"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ProfessorServiceMethodDescriptorSupplier("closeEnrollments"))
              .build();
        }
      }
    }
    return getCloseEnrollmentsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassRequest,
      pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassResponse> getListClassMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listClass",
      requestType = pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassRequest.class,
      responseType = pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassRequest,
      pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassResponse> getListClassMethod() {
    io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassRequest, pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassResponse> getListClassMethod;
    if ((getListClassMethod = ProfessorServiceGrpc.getListClassMethod) == null) {
      synchronized (ProfessorServiceGrpc.class) {
        if ((getListClassMethod = ProfessorServiceGrpc.getListClassMethod) == null) {
          ProfessorServiceGrpc.getListClassMethod = getListClassMethod =
              io.grpc.MethodDescriptor.<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassRequest, pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listClass"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ProfessorServiceMethodDescriptorSupplier("listClass"))
              .build();
        }
      }
    }
    return getListClassMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentRequest,
      pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentResponse> getCancelEnrollmentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "cancelEnrollment",
      requestType = pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentRequest.class,
      responseType = pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentRequest,
      pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentResponse> getCancelEnrollmentMethod() {
    io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentRequest, pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentResponse> getCancelEnrollmentMethod;
    if ((getCancelEnrollmentMethod = ProfessorServiceGrpc.getCancelEnrollmentMethod) == null) {
      synchronized (ProfessorServiceGrpc.class) {
        if ((getCancelEnrollmentMethod = ProfessorServiceGrpc.getCancelEnrollmentMethod) == null) {
          ProfessorServiceGrpc.getCancelEnrollmentMethod = getCancelEnrollmentMethod =
              io.grpc.MethodDescriptor.<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentRequest, pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "cancelEnrollment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ProfessorServiceMethodDescriptorSupplier("cancelEnrollment"))
              .build();
        }
      }
    }
    return getCancelEnrollmentMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProfessorServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProfessorServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProfessorServiceStub>() {
        @java.lang.Override
        public ProfessorServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProfessorServiceStub(channel, callOptions);
        }
      };
    return ProfessorServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProfessorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProfessorServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProfessorServiceBlockingStub>() {
        @java.lang.Override
        public ProfessorServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProfessorServiceBlockingStub(channel, callOptions);
        }
      };
    return ProfessorServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProfessorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProfessorServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProfessorServiceFutureStub>() {
        @java.lang.Override
        public ProfessorServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProfessorServiceFutureStub(channel, callOptions);
        }
      };
    return ProfessorServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ProfessorServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void openEnrollments(pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOpenEnrollmentsMethod(), responseObserver);
    }

    /**
     */
    public void closeEnrollments(pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCloseEnrollmentsMethod(), responseObserver);
    }

    /**
     */
    public void listClass(pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListClassMethod(), responseObserver);
    }

    /**
     */
    public void cancelEnrollment(pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCancelEnrollmentMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getOpenEnrollmentsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsRequest,
                pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsResponse>(
                  this, METHODID_OPEN_ENROLLMENTS)))
          .addMethod(
            getCloseEnrollmentsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsRequest,
                pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsResponse>(
                  this, METHODID_CLOSE_ENROLLMENTS)))
          .addMethod(
            getListClassMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassRequest,
                pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassResponse>(
                  this, METHODID_LIST_CLASS)))
          .addMethod(
            getCancelEnrollmentMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentRequest,
                pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentResponse>(
                  this, METHODID_CANCEL_ENROLLMENT)))
          .build();
    }
  }

  /**
   */
  public static final class ProfessorServiceStub extends io.grpc.stub.AbstractAsyncStub<ProfessorServiceStub> {
    private ProfessorServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProfessorServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProfessorServiceStub(channel, callOptions);
    }

    /**
     */
    public void openEnrollments(pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOpenEnrollmentsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void closeEnrollments(pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCloseEnrollmentsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listClass(pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListClassMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void cancelEnrollment(pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCancelEnrollmentMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ProfessorServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ProfessorServiceBlockingStub> {
    private ProfessorServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProfessorServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProfessorServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsResponse openEnrollments(pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOpenEnrollmentsMethod(), getCallOptions(), request);
    }

    /**
     */
    public pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsResponse closeEnrollments(pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCloseEnrollmentsMethod(), getCallOptions(), request);
    }

    /**
     */
    public pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassResponse listClass(pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListClassMethod(), getCallOptions(), request);
    }

    /**
     */
    public pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentResponse cancelEnrollment(pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCancelEnrollmentMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ProfessorServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ProfessorServiceFutureStub> {
    private ProfessorServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProfessorServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProfessorServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsResponse> openEnrollments(
        pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOpenEnrollmentsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsResponse> closeEnrollments(
        pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCloseEnrollmentsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassResponse> listClass(
        pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListClassMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentResponse> cancelEnrollment(
        pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCancelEnrollmentMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_OPEN_ENROLLMENTS = 0;
  private static final int METHODID_CLOSE_ENROLLMENTS = 1;
  private static final int METHODID_LIST_CLASS = 2;
  private static final int METHODID_CANCEL_ENROLLMENT = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ProfessorServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ProfessorServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_OPEN_ENROLLMENTS:
          serviceImpl.openEnrollments((pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsRequest) request,
              (io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.OpenEnrollmentsResponse>) responseObserver);
          break;
        case METHODID_CLOSE_ENROLLMENTS:
          serviceImpl.closeEnrollments((pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsRequest) request,
              (io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CloseEnrollmentsResponse>) responseObserver);
          break;
        case METHODID_LIST_CLASS:
          serviceImpl.listClass((pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassRequest) request,
              (io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.ListClassResponse>) responseObserver);
          break;
        case METHODID_CANCEL_ENROLLMENT:
          serviceImpl.cancelEnrollment((pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentRequest) request,
              (io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.CancelEnrollmentResponse>) responseObserver);
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

  private static abstract class ProfessorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProfessorServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return pt.ulisboa.tecnico.classes.contract.professor.ProfessorClassServer.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ProfessorService");
    }
  }

  private static final class ProfessorServiceFileDescriptorSupplier
      extends ProfessorServiceBaseDescriptorSupplier {
    ProfessorServiceFileDescriptorSupplier() {}
  }

  private static final class ProfessorServiceMethodDescriptorSupplier
      extends ProfessorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ProfessorServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ProfessorServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProfessorServiceFileDescriptorSupplier())
              .addMethod(getOpenEnrollmentsMethod())
              .addMethod(getCloseEnrollmentsMethod())
              .addMethod(getListClassMethod())
              .addMethod(getCancelEnrollmentMethod())
              .build();
        }
      }
    }
    return result;
  }
}
