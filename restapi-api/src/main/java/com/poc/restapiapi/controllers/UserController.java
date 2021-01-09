package com.poc.restapiapi.controllers;

import com.poc.restapidto.models.UserRequest;
import com.poc.restapidto.models.UserResponse;
import com.poc.restapiservices.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping(path = {"/{id}"},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponse> findUserById(@PathVariable("id") String userId){
        UserResponse response = userServices.findUserById(userId);
        if(response==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = {"all","","/list"},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<UserResponse>> findUsers(){
        List<UserResponse> response = userServices.findUsers();
        if(response==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserRequest userRequest){

        return new ResponseEntity<>(userServices.createUser(userRequest), HttpStatus.CREATED);
    }

    @PutMapping(path = {"/{id}"},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") String userId,
                                                   @Valid @RequestBody UserRequest userRequest){

        return new ResponseEntity<>(userServices.updateUser(userId,userRequest), HttpStatus.OK);
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<UserResponse> deleteUser(@PathVariable("id") String userId) {
        UserResponse response = userServices.deleteUser(userId);
        if(response==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity handleBadInput(Exception ex) {
       return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
    }
}
