package com.example.assistanceservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CanHelpDto {
    private Long id;
    private String organization;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String motivation;
    private String phoneNumber;
}
