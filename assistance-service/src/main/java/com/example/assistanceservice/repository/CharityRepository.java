package com.example.assistanceservice.repository;

import com.example.assistanceservice.model.Charity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharityRepository extends JpaRepository<Charity, Long> {
    List<Charity> findByNameContainingIgnoreCase(String name);
}
