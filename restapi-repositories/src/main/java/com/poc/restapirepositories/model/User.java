package com.poc.restapirepositories.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter @Getter
@NoArgsConstructor @ToString
@AllArgsConstructor
@Document
@Builder
public class User {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String userName;

    @Indexed(unique=true)
    private String email;

    private String phone;
    private String street;
    private String state;
    private String country;
}
