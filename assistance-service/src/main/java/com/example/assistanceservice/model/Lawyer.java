package com.example.assistanceservice.model;

import com.example.assistanceservice.dto.LawyerDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "s_lawyer")
@Table
public class Lawyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String photoUrl;
    private int workExperience;
    private String directKZ;
    private String directRU;

    @OneToOne(targetEntity = Contact.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "lawyer_id", referencedColumnName = "id")
    private Contact contact;

    public void update(LawyerDto lawyerDto) {
        this.name = lawyerDto.getName();
        this.photoUrl = lawyerDto.getPhotoUrl();
        this.workExperience = lawyerDto.getWorkExperience();
        this.directKZ = lawyerDto.getDirectKZ();
        this.directRU = lawyerDto.getDirectRU();
        this.contact = lawyerDto.getContact();
    }
}
