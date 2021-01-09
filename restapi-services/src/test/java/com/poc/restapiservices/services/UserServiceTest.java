package com.poc.restapiservices.services;

import com.poc.restapidto.models.UserRequest;
import com.poc.restapidto.models.UserResponse;
import com.poc.restapirepositories.model.User;
import com.poc.restapirepositories.repositories.UserRepository;
import com.poc.restapiutils.Utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    Utils utils;

    @InjectMocks
    UserServiceImpl userService;

    User user;
    UserResponse response;

    @BeforeEach
    void setup(){
        user = User.builder().userName("jaideep").lastName("kurapati").email("jaideep@email.com").id("12672t367t27g36t")
                .phone("1234567789").street("345 ar r").state("PA").country("India").build();
        response = UserResponse.builder().userName("jaideepkurapati").id("12672t367t27g36t")
                .email("jaideep@email.com").build();
    }

    @Test
    void findUserById(){
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
        UserResponse result = userService.findUserById("6737612t");
        assertEquals(result.getId(),user.getId());

    }

    @Test
    void findAll(){
        List<User> storedUsers = new ArrayList<>();
        storedUsers.add(user);
        storedUsers.add(User.builder().userName("jaideep").email("jai@gmail.com").build());

        when(userRepository.findAll()).thenReturn(storedUsers);

        List<UserResponse> result = userService.findUsers();
        assertEquals(2,result.size());
        assertNotNull(result);
        verify(userRepository).findAll();
    }

    @Test
    void createUser(){
        UserRequest request = UserRequest.builder().firstName("jaideep").lastName("kurapati").email("jaideep@email.com")
                .userName("jaideepkurapati").phone("1234567890").street("345").state("PA").country("India").build();
        lenient().when(userService.createUser(request)).thenReturn(response);
        assertEquals(response.getUserName(),userService.createUser(request).getUserName());
        verify(userRepository).save(any());
    }

    @Test
    void update(){
        UserRequest request = UserRequest.builder().firstName("jaideep").lastName("kurapati").email("jaideep@email.com")
                .userName("jaideepkurapati").phone("1234567890").street("345").state("PA").country("India").build();
        lenient().when(userService.updateUser(anyString(),request)).thenReturn(response);
        assertEquals(response.getUserName(),userService.updateUser("64rtuwtr64wuyr",request).getUserName());
        verify(userRepository).save(any());
    }

    @Test
    void delete(){
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
        userService.deleteUser("ry487yr4r");
        verify(userRepository,times(1)).delete(any());
    }
}
