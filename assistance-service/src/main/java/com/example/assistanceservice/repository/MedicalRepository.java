package com.example.assistanceservice.repository;

import com.example.assistanceservice.model.Medical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRepository extends JpaRepository<Medical, Long> {
}
