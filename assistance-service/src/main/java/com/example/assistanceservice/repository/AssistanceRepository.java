package com.example.assistanceservice.repository;

import com.example.assistanceservice.model.Assistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssistanceRepository extends JpaRepository<Assistance, Long> {
    List<Assistance> findAll();
}
