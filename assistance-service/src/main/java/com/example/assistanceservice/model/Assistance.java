package com.example.assistanceservice.model;

import com.example.assistanceservice.dto.AssistanceDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "s_assistances")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Assistance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String name;
    private String description;
    private String subDescription;
    private String email;
    private LocalTime workingStartTime;
    private LocalTime workingEndTime;

    @OneToMany(targetEntity = AssistancePhoneNumbers.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "assistance_id", referencedColumnName = "id")
    private List<AssistancePhoneNumbers> phoneNumbers;

    @OneToMany(targetEntity = AssistanceLocation.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "assistance_id", referencedColumnName = "id")
    private List<AssistanceLocation> locations;

    public void update(AssistanceDTO assistanceDTO) {
        this.type = assistanceDTO.getType();
        this.name = assistanceDTO.getName();
        this.description = assistanceDTO.getDescription();
        this.subDescription = assistanceDTO.getSubDescription();
        this.email = assistanceDTO.getEmail();
        this.workingStartTime = assistanceDTO.getWorkingStartTime();
        this.workingEndTime = assistanceDTO.getWorkingEndTime();
        this.phoneNumbers = assistanceDTO.getPhoneNumbers();
        this.locations = assistanceDTO.getLocations();
    }
}
