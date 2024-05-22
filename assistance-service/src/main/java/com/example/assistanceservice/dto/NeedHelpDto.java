package com.example.assistanceservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NeedHelpDto {
    private Long id;
    private String organization;
    private String username;
    private String fullName;
    private String supportType;
    private String expand;
    private String phoneNumber;
    private LocalDateTime createdAt;
}
