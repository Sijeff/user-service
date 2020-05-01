package com.example.userservice.grpc;

import com.example.userservice.grpc.v1.GetUserRequest;
import com.example.userservice.grpc.v1.User;
import com.example.userservice.grpc.v1.UserServiceGrpc.UserServiceImplBase;
import com.example.userservice.repository.UserRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class UserServiceImpl extends UserServiceImplBase {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void getUser(GetUserRequest request, StreamObserver<User> responseObserver) {
        String userId = request.getUserId();
        if (userId.isEmpty()) {
            responseObserver.onError(
                    Status.INVALID_ARGUMENT
                            .withDescription("UserId must be provided")
                            .asRuntimeException()
            );
        }

        responseObserver.onNext(userRepository.getUser(userId));
        responseObserver.onCompleted();
    }
}
