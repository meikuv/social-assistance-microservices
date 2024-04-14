package com.example.assistanceservice.service.impl;

import com.example.assistanceservice.dto.AssistanceDTO;
import com.example.assistanceservice.exception.HttpException;
import com.example.assistanceservice.model.Assistance;
import com.example.assistanceservice.repository.AssistanceRepository;
import com.example.assistanceservice.service.IAssistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssistanceServiceImpl implements IAssistanceService {
    private final AssistanceRepository assistanceRepository;

    @Override
    @Transactional
    public void createAssistance(AssistanceDTO assistanceDTO) {
        Assistance assistance = assistanceDTO.toModel();
        assistanceRepository.save(assistance);
    }

    @Override
    @Transactional
    public void updateAssistance(AssistanceDTO assistanceDTO) {
        Assistance assistance = assistanceRepository.findById(assistanceDTO.getId()).orElseThrow(
                () -> new HttpException(HttpStatus.NOT_FOUND, "Assistance not found")
        );
        assistance.update(assistanceDTO);
        assistanceRepository.save(assistance);
    }

    @Override
    @Transactional
    public void deleteAssistance(Long id) {
        assistanceRepository.deleteById(id);
    }

    @Override
    public List<Assistance> getAllAssistance() {
        return assistanceRepository.findAll();
    }
}
