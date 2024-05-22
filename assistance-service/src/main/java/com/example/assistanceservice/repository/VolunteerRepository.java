package com.example.assistanceservice.repository;

import com.example.assistanceservice.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    List<Volunteer> findByNameContainingIgnoreCase(String name);
}

