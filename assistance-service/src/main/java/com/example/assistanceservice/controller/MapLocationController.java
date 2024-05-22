package com.example.assistanceservice.controller;

import com.example.assistanceservice.dto.MapLocationDto;
import com.example.assistanceservice.model.MapLocation;
import com.example.assistanceservice.payload.response.MessageResponse;
import com.example.assistanceservice.service.impl.MapLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assistance/map-location")
@RequiredArgsConstructor
public class MapLocationController {
    private final MapLocationService mapLocationService;

    @GetMapping("/getAll")
    public List<MapLocation> getAll() {
        return mapLocationService.getMapLocations();
    }

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> create(@RequestBody MapLocationDto mapLocationDto) {
        mapLocationService.createMapLocation(mapLocationDto);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Location " + mapLocationDto.getLocationName() + " created successfully"));
    }

}
