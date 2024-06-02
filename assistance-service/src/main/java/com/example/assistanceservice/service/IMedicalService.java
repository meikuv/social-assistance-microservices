package com.example.assistanceservice.service;

import com.example.assistanceservice.dto.MedicalDto;
import com.example.assistanceservice.model.Medical;

import java.util.List;

public interface IMedicalService {
    List<Medical> getMedicals();

    void create(MedicalDto medicalDto, String fileUrl);
}
