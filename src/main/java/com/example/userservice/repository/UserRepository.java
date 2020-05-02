package com.example.userservice.repository;


import com.example.userservice.grpc.v1.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public UserRepository() {
    }

    public User createUser(User user) {
        // todo: actually save in repository

        System.out.println(user);

        return user;
    }

    public User updateUser(User user) {
        // todo: actually update in repository
        System.out.println(user + " updated");

        return user;
    }

    public User getUser(String userId) {
        // todo implement
        return User.newBuilder()
                .setUserId(userId)
                .setUserAge(99)
                .setUserName("PLACEHOLDER")
                .build();
    }

    public User deleteUser(String userId) {

        // todo implement
        System.out.println("User with id + " + userId + " deleted");

        return User.newBuilder()
                .setUserId(userId)
                .setUserAge(99)
                .setUserName("PLACEHOLDER")
                .build();
    }
}
