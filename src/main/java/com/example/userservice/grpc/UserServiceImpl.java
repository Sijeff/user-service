package com.example.userservice.grpc;

import com.example.userservice.grpc.v1.*;
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

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<User> responseObserver) {
        String userName = request.getUserName();
        int userAge = request.getUserAge();

        // todo: better verification
        if (userName.isEmpty()) {
            responseObserver.onError(
                    Status.INVALID_ARGUMENT
                            .withDescription("User name must be provided")
                            .asRuntimeException()
            );
        }

        if (userAge <= 0) {
            responseObserver.onError(
                    Status.INVALID_ARGUMENT
                            .withDescription("User age must be greater than zero")
                            .asRuntimeException()
            );
        }

        User user = User.newBuilder()
                .setUserName(userName)
                .setUserAge(userAge)
                .build();

        responseObserver.onNext(userRepository.createUser(user));
        responseObserver.onCompleted();
    }

    @Override
    public void deleteUser(DeleteUserRequest request, StreamObserver<User> responseObserver) {
        String userId = request.getUserId();
        responseObserver.onNext(userRepository.deleteUser(userId));
        responseObserver.onCompleted();
    }

    @Override
    public void patchUser(PatchUserRequest request, StreamObserver<User> responseObserver) {
        String userId = request.getUserId();
        String userName = request.getUserName();
        int userAge = request.getUserAge();

        User user = User.newBuilder()
                .setUserId(userId)
                .setUserName(userName)
                .setUserAge(userAge)
                .build();

        responseObserver.onNext(userRepository.updateUser(user));
        responseObserver.onCompleted();
    }
}
