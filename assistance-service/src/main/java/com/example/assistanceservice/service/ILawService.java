package com.example.assistanceservice.service;

import com.example.assistanceservice.dto.LawDto;
import com.example.assistanceservice.model.Law;

import java.util.List;

public interface ILawService {
    List<Law> getAllLaw();

    void createLaw(LawDto lawDto);

    void updateLaw(LawDto lawDto);

    void deleteLaw(Long id);
}
