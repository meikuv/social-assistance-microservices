package com.example.assistanceservice.service;

import com.example.assistanceservice.dto.MapLocationDto;
import com.example.assistanceservice.model.MapLocation;

import java.util.List;

public interface IMapLocationService {
    List<MapLocation> getMapLocations();

    void createMapLocation(MapLocationDto mapLocationDto);
}
