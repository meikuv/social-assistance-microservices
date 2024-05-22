package com.example.assistanceservice.repository;

import com.example.assistanceservice.model.CanHelp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CanHelpRepository extends JpaRepository<CanHelp, Long> {
    List<CanHelp> findAllByUsername(String username);
}
