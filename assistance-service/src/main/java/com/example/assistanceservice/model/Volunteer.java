package com.example.assistanceservice.model;

import com.example.assistanceservice.dto.VolunteerDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "s_volunteer")
@Table
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String postUrl;
    private String photoUrl;
    private String descriptionKZ;
    private String descriptionRU;

    @OneToOne(targetEntity = Contact.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "volunteer_id", referencedColumnName = "id")
    private Contact contact;

    @OneToMany(targetEntity = Direction.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "volunteer_id", referencedColumnName = "id")
    private List<Direction> directions;

    @OneToMany(targetEntity = Requisite.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "volunteer_id", referencedColumnName = "id")
    private List<Requisite> requisites; ;

    @OneToMany(targetEntity = PhoneNumbers.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "volunteer_id", referencedColumnName = "id")
    private List<PhoneNumbers> phoneNumbers;

    @OneToMany(targetEntity = Locations.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "volunteer_id", referencedColumnName = "id")
    private List<Locations> locations;

    public void update(VolunteerDto volunteerDto) {
        this.name = volunteerDto.getName();
        this.postUrl = volunteerDto.getPostUrl();
        this.photoUrl = volunteerDto.getPhotoUrl();
        this.descriptionKZ = volunteerDto.getDescriptionKZ();
        this.descriptionRU = volunteerDto.getDescriptionRU();
        this.contact = volunteerDto.getContact();
        this.directions = volunteerDto.getDirections();
        this.requisites = volunteerDto.getRequisites();
        this.phoneNumbers = volunteerDto.getPhoneNumbers();
        this.locations = volunteerDto.getLocations();
    }
}
