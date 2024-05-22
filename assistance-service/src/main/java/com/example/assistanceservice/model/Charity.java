package com.example.assistanceservice.model;

import com.example.assistanceservice.dto.CharityDto;
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
@Entity(name = "s_charity")
@Table
public class Charity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String typeKZ;
    private String typeRU;
    private String postUrl;
    private String photoUrl;
    private String descriptionKZ;
    private String descriptionRU;

    @OneToOne(targetEntity = Contact.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "charity_id", referencedColumnName = "id")
    private Contact contact;

    @OneToMany(targetEntity = Direction.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "charity_id", referencedColumnName = "id")
    private List<Direction> directions;

    @OneToMany(targetEntity = Requisite.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "charity_id", referencedColumnName = "id")
    private List<Requisite> requisites; ;

    @OneToMany(targetEntity = PhoneNumbers.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "charity_id", referencedColumnName = "id")
    private List<PhoneNumbers> phoneNumbers;

    @OneToMany(targetEntity = Locations.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "charity_id", referencedColumnName = "id")
    private List<Locations> locations;

    public void update(CharityDto charityDto) {
        this.name = charityDto.getName();
        this.typeKZ = charityDto.getTypeKZ();
        this.typeRU = charityDto.getTypeRU();
        this.postUrl = charityDto.getPostUrl();
        this.photoUrl = charityDto.getPhotoUrl();
        this.descriptionKZ = charityDto.getDescriptionKZ();
        this.descriptionRU = charityDto.getDescriptionRU();
        this.contact = charityDto.getContact();
        this.directions = charityDto.getDirections();
        this.requisites = charityDto.getRequisites();
        this.phoneNumbers = charityDto.getPhoneNumbers();
        this.locations = charityDto.getLocations();
    }
}
