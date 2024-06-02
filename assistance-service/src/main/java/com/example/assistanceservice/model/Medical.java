package com.example.assistanceservice.model;

import com.example.assistanceservice.dto.MedicalDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "s_medical")
@Table
public class Medical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String photoUrl;
    private String address;
    private String phone;
    private String descriptionKZ;
    private String descriptionRU;
    private String websiteUrl;

    public void update(MedicalDto medicalDto) {
        this.name = medicalDto.getName();
        this.address = medicalDto.getAddress();
        this.phone = medicalDto.getPhone();
        this.descriptionKZ = medicalDto.getDescriptionKZ();
        this.descriptionRU = medicalDto.getDescriptionRU();
        this.websiteUrl = medicalDto.getWebsiteUrl();
    }
}
