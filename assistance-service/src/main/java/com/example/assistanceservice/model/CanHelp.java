package com.example.assistanceservice.model;

import com.example.assistanceservice.dto.CanHelpDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cx_can_help")
@Table
public class CanHelp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String organization;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String motivation;
    private String phoneNumber;
    private LocalDateTime createdAt;

    public void update(CanHelpDto canHelpDto) {
        this.organization = canHelpDto.getOrganization();
        this.type = "canHelp";
        this.username = canHelpDto.getUsername();
        this.firstName = canHelpDto.getFirstName();
        this.lastName = canHelpDto.getLastName();
        this.email = canHelpDto.getEmail();
        this.age = canHelpDto.getAge();
        this.motivation = canHelpDto.getMotivation();
        this.phoneNumber = canHelpDto.getPhoneNumber();
        this.createdAt = LocalDateTime.now();
    }
}
