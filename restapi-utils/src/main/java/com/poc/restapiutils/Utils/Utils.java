package com.poc.restapiutils.Utils;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Utils {

    public String getUserId(){
        return UUID.randomUUID().toString();
    }
}
