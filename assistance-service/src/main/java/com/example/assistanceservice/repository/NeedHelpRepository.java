package com.example.assistanceservice.repository;

import com.example.assistanceservice.model.NeedHelp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NeedHelpRepository extends JpaRepository<NeedHelp, Long> {
    List<NeedHelp> findAllByUsername(String username);
}
