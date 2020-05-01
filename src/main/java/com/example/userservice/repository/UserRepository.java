package com.example.userservice.repository;


import com.example.userservice.grpc.v1.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public UserRepository() {
    }

    public void saveUser(User user) {
        // todo: actually save in repository
        System.out.println(user);
    }

    public User getUser(String id) {
        // todo implement
        return User.newBuilder()
                .setUserId(id)
                .setUserAge(99)
                .build();
    }

    public void deleteUser(String id) {
        // todo implement
        System.out.println("User with id + " + id + " deleted");
    }
}
