package com.example.assistanceservice.dto;

import com.example.assistanceservice.model.Contact;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LawyerDto {
    private Long id;
    private String name;
    private String photoUrl;
    private int workExperience;
    private String directKZ;
    private String directRU;
    private Contact contact;
}
