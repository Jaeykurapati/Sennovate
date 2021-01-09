package com.poc.restapiservices.services;

import com.poc.restapidto.models.UserRequest;
import com.poc.restapidto.models.UserResponse;

import java.util.List;

public interface UserServices {

    UserResponse findUserById(String userId);
    List<UserResponse> findUsers();
    UserResponse createUser(UserRequest user);
    UserResponse updateUser(String userId, UserRequest updatedUser);
    UserResponse deleteUser(String userId);
}
