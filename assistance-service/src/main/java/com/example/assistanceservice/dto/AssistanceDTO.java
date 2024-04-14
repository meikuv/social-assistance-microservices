package com.example.assistanceservice.dto;

import com.example.assistanceservice.model.Assistance;
import com.example.assistanceservice.model.AssistanceLocation;
import com.example.assistanceservice.model.AssistancePhoneNumbers;
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
public class AssistanceDTO {
    private Long id;
    private String type;
    private String name;
    private String description;
    private String subDescription;
    private String email;
    private LocalTime workingStartTime;
    private LocalTime workingEndTime;
    private List<AssistancePhoneNumbers> phoneNumbers;
    private List<AssistanceLocation> locations;

    public Assistance toModel() {
        return new ModelMapper().map(this, Assistance.class);
    }
}
