package com.poc.restapidto.models;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    String id;
    String userName;
    String email;
}
