package com.example.assistanceservice.repository;

import com.example.assistanceservice.model.Law;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LawRepository extends JpaRepository<Law, Long> {
}
