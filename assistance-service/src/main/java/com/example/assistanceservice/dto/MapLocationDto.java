package com.example.assistanceservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapLocationDto {
    private Long id;
    private String type;
    private String locationName;
    private String locationAddress;
    private Double latitude;
    private Double longitude;
}
