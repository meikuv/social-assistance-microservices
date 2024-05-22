package com.example.assistanceservice.repository;

import com.example.assistanceservice.model.Lawyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, Long> {
    List<Lawyer> findByNameContainingIgnoreCase(String name);
}
