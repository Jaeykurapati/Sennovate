package com.poc.restapidto.models;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
@Builder
public class UserRequest {

    @NotNull(message = "firstName must be present")
    String firstName;

    @NotNull(message = "lastName must be present")
    String lastName;

    @NotNull(message = "userName must be present")
    String userName;

    @NotNull(message = "email must be present")
    @Email
    String email;

    @NotNull(message = "phone must be present")
    @Size(min = 10,max = 10)
    String phone;

    @NotNull(message = "street must be present")
    String street;

    @NotNull(message = "state must be present")
    String state;

    @NotNull(message = "country must be present")
    String country;
}
