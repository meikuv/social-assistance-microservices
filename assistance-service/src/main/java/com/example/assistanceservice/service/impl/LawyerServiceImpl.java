package com.example.assistanceservice.service.impl;

import com.example.assistanceservice.dto.LawyerDto;
import com.example.assistanceservice.exception.HttpException;
import com.example.assistanceservice.model.Lawyer;
import com.example.assistanceservice.repository.LawyerRepository;
import com.example.assistanceservice.service.ILawyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LawyerServiceImpl implements ILawyerService {
    private final LawyerRepository lawyerRepository;

    @Override
    public List<Lawyer> getAllLawyer() {
        return lawyerRepository.findAll();
    }

    @Override
    public List<Lawyer> getLawyerByName(String name) {
        return lawyerRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Lawyer getLawyerById(Long id) {
        return lawyerRepository.findById(id).orElseThrow(
                () -> new HttpException(HttpStatus.NOT_FOUND, "Lawyer with id: " + id + " not found")
        );
    }

    @Override
    public void createLawyer(LawyerDto lawyerDto, String fileUrl) {
        Lawyer lawyer = new Lawyer();
        lawyer.update(lawyerDto);
        lawyer.setPhotoUrl(fileUrl);
        lawyerRepository.save(lawyer);
    }

    @Override
    public void updateLawyer(LawyerDto volunteerDto) {
        Lawyer lawyer = getLawyerById(volunteerDto.getId());
        lawyer.update(volunteerDto);
        lawyerRepository.save(lawyer);
    }

    @Override
    public void deleteLawyer(Long id) {
        lawyerRepository.deleteById(id);
    }
}
