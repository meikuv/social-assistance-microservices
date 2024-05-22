package com.example.assistanceservice.service.impl;

import com.example.assistanceservice.dto.LawDto;
import com.example.assistanceservice.exception.HttpException;
import com.example.assistanceservice.model.Law;
import com.example.assistanceservice.repository.LawRepository;
import com.example.assistanceservice.service.ILawService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LawServiceImpl implements ILawService {
    private final LawRepository lawRepository;

    @Override
    public List<Law> getAllLaw() {
        return lawRepository.findAll();
    }

    @Override
    public void createLaw(LawDto lawDto) {
        Law law = new Law();
        law.update(lawDto);
        lawRepository.save(law);
    }

    @Override
    public void updateLaw(LawDto lawDto) {
        Law law = lawRepository.findById(lawDto.getId()).orElseThrow(
                () -> new HttpException(HttpStatus.NOT_FOUND, "Law with id: " + lawDto.getId() + " not found")
        );
        law.update(lawDto);
        lawRepository.save(law);
    }

    @Override
    public void deleteLaw(Long id) {
        lawRepository.deleteById(id);
    }
}
