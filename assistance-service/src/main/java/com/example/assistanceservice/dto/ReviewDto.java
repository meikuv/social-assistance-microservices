package com.example.assistanceservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Integer rating;
    private String comment;
}
