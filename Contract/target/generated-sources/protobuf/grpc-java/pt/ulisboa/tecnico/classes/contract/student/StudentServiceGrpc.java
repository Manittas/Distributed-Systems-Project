package pt.ulisboa.tecnico.classes.contract.student;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.44.1)",
    comments = "Source: Student_ClassServer.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class StudentServiceGrpc {

  private StudentServiceGrpc() {}

  public static final String SERVICE_NAME = "pt.ulisboa.tecnico.classes.contract.student.StudentService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassRequest,
      pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassResponse> getListClassMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listClass",
      requestType = pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassRequest.class,
      responseType = pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassRequest,
      pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassResponse> getListClassMethod() {
    io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassRequest, pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassResponse> getListClassMethod;
    if ((getListClassMethod = StudentServiceGrpc.getListClassMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getListClassMethod = StudentServiceGrpc.getListClassMethod) == null) {
          StudentServiceGrpc.getListClassMethod = getListClassMethod =
              io.grpc.MethodDescriptor.<pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassRequest, pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listClass"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("listClass"))
              .build();
        }
      }
    }
    return getListClassMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollRequest,
      pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollResponse> getEnrollMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "enroll",
      requestType = pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollRequest.class,
      responseType = pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollRequest,
      pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollResponse> getEnrollMethod() {
    io.grpc.MethodDescriptor<pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollRequest, pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollResponse> getEnrollMethod;
    if ((getEnrollMethod = StudentServiceGrpc.getEnrollMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getEnrollMethod = StudentServiceGrpc.getEnrollMethod) == null) {
          StudentServiceGrpc.getEnrollMethod = getEnrollMethod =
              io.grpc.MethodDescriptor.<pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollRequest, pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "enroll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("enroll"))
              .build();
        }
      }
    }
    return getEnrollMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StudentServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StudentServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StudentServiceStub>() {
        @java.lang.Override
        public StudentServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StudentServiceStub(channel, callOptions);
        }
      };
    return StudentServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StudentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StudentServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StudentServiceBlockingStub>() {
        @java.lang.Override
        public StudentServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StudentServiceBlockingStub(channel, callOptions);
        }
      };
    return StudentServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StudentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StudentServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StudentServiceFutureStub>() {
        @java.lang.Override
        public StudentServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StudentServiceFutureStub(channel, callOptions);
        }
      };
    return StudentServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class StudentServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void listClass(pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListClassMethod(), responseObserver);
    }

    /**
     */
    public void enroll(pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEnrollMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getListClassMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassRequest,
                pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassResponse>(
                  this, METHODID_LIST_CLASS)))
          .addMethod(
            getEnrollMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollRequest,
                pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollResponse>(
                  this, METHODID_ENROLL)))
          .build();
    }
  }

  /**
   */
  public static final class StudentServiceStub extends io.grpc.stub.AbstractAsyncStub<StudentServiceStub> {
    private StudentServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StudentServiceStub(channel, callOptions);
    }

    /**
     */
    public void listClass(pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListClassMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void enroll(pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollRequest request,
        io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEnrollMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class StudentServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<StudentServiceBlockingStub> {
    private StudentServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StudentServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassResponse listClass(pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListClassMethod(), getCallOptions(), request);
    }

    /**
     */
    public pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollResponse enroll(pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEnrollMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class StudentServiceFutureStub extends io.grpc.stub.AbstractFutureStub<StudentServiceFutureStub> {
    private StudentServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StudentServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassResponse> listClass(
        pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListClassMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollResponse> enroll(
        pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEnrollMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIST_CLASS = 0;
  private static final int METHODID_ENROLL = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StudentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StudentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LIST_CLASS:
          serviceImpl.listClass((pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassRequest) request,
              (io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.ListClassResponse>) responseObserver);
          break;
        case METHODID_ENROLL:
          serviceImpl.enroll((pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollRequest) request,
              (io.grpc.stub.StreamObserver<pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.EnrollResponse>) responseObserver);
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

  private static abstract class StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StudentServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return pt.ulisboa.tecnico.classes.contract.student.StudentClassServer.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StudentService");
    }
  }

  private static final class StudentServiceFileDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier {
    StudentServiceFileDescriptorSupplier() {}
  }

  private static final class StudentServiceMethodDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StudentServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (StudentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StudentServiceFileDescriptorSupplier())
              .addMethod(getListClassMethod())
              .addMethod(getEnrollMethod())
              .build();
        }
      }
    }
    return result;
  }
}
