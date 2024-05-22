package com.example.assistanceservice.dto;

import com.example.assistanceservice.model.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VolunteerDto {
    private Long id;
    private String name;
    private String postUrl;
    private String photoUrl;
    private String descriptionKZ;
    private String descriptionRU;
    private Contact contact;
    private List<Direction> directions;
    private List<Requisite> requisites;
    private List<PhoneNumbers> phoneNumbers;
    private List<Locations> locations;
}
