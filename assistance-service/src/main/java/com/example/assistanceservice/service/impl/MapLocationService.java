package com.example.assistanceservice.service.impl;

import com.example.assistanceservice.dto.MapLocationDto;
import com.example.assistanceservice.model.MapLocation;
import com.example.assistanceservice.repository.MapLocationRepository;
import com.example.assistanceservice.service.IMapLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MapLocationService implements IMapLocationService {
    private final MapLocationRepository mapLocationRepository;

    @Override
    public List<MapLocation> getMapLocations() {
        return mapLocationRepository.findAll();
    }

    @Override
    public void createMapLocation(MapLocationDto mapLocationDto) {
        MapLocation newMapLocation = new MapLocation();
        newMapLocation.update(mapLocationDto);
        mapLocationRepository.save(newMapLocation);
    }
}
