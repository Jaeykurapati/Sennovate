package com.poc.restapiservices.services;

import com.poc.restapidto.models.UserRequest;
import com.poc.restapidto.models.UserResponse;
import com.poc.restapirepositories.model.User;
import com.poc.restapirepositories.repositories.UserRepository;
import com.poc.restapiutils.Utils.Utils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServices {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Utils utils;

    public UserServiceImpl(UserRepository userRepository, Utils utils) {
        this.userRepository = userRepository;
        this.utils = utils;
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public UserResponse findUserById(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User response = user.get();
            return modelMapper.map(response,UserResponse.class);
        }
        return null;
    }

    @Override
    public List<UserResponse> findUsers() {
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        System.out.println(userRequest);
        User user = modelMapper.map(userRequest,User.class);
        System.out.println(user);
        user.setId(utils.getUserId());
        userRepository.save(user);
        return modelMapper.map(user,UserResponse.class);
    }

    @Override
    public UserResponse updateUser(String userId, UserRequest updatedUser) {
        User user = modelMapper.map(updatedUser,User.class);
        user.setId(userId);
        userRepository.save(user);
        return modelMapper.map(user,UserResponse.class);
    }

    @Override
    public UserResponse deleteUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User response = user.get();
            userRepository.delete(user.get());
            return modelMapper.map(response,UserResponse.class);
        }
        else {
            return null;
        }
    }
}
