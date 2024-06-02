package com.example.assistanceservice.service.impl;

import com.example.assistanceservice.dto.MedicalDto;
import com.example.assistanceservice.model.Medical;
import com.example.assistanceservice.repository.MedicalRepository;
import com.example.assistanceservice.service.IMedicalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalServiceImpl implements IMedicalService {
    private final MedicalRepository medicalRepository;

    @Override
    public List<Medical> getMedicals() {
        return medicalRepository.findAll();
    }

    @Override
    public void create(MedicalDto medicalDto, String fileUrl) {
        Medical newMedical = new Medical();
        newMedical.update(medicalDto);
        newMedical.setPhotoUrl(fileUrl);
        medicalRepository.save(newMedical);
    }
}
