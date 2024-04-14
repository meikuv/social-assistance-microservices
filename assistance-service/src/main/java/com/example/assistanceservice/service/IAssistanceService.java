package com.example.assistanceservice.service;

import com.example.assistanceservice.dto.AssistanceDTO;
import com.example.assistanceservice.model.Assistance;

import java.util.List;

public interface IAssistanceService {
    void createAssistance(AssistanceDTO assistanceDTO);

    void updateAssistance(AssistanceDTO assistanceDTO);

    void deleteAssistance(Long id);

    List<Assistance> getAllAssistance();
}
