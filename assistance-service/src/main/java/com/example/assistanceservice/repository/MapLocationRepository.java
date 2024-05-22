package com.example.assistanceservice.repository;

import com.example.assistanceservice.model.MapLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapLocationRepository extends JpaRepository<MapLocation, Long> {
}
