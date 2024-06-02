package com.example.assistanceservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalDto {
    private Long id;
    private String name;
    private String photoUrl;
    private String address;
    private String phone;
    private String descriptionKZ;
    private String descriptionRU;
    private String websiteUrl;
}
