package com.example.assistanceservice.model;

import com.example.assistanceservice.dto.MapLocationDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cx_map_location")
@Table
public class MapLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String locationName;
    private String locationAddress;
    private Double latitude;
    private Double longitude;

    public void update(MapLocationDto mapLocationDto) {
        this.type = mapLocationDto.getType();
        this.locationName = mapLocationDto.getLocationName();
        this.locationAddress = mapLocationDto.getLocationAddress();
        this.latitude = mapLocationDto.getLatitude();
        this.longitude = mapLocationDto.getLongitude();
    }
}
