package com.example.assistanceservice.service.impl;

import com.example.assistanceservice.dto.CharityDto;
import com.example.assistanceservice.exception.HttpException;
import com.example.assistanceservice.model.Charity;
import com.example.assistanceservice.repository.CharityRepository;
import com.example.assistanceservice.service.ICharityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharityServiceImpl implements ICharityService {
    private final CharityRepository charityRepository;

    @Override
    public List<Charity> getAllCharity() {
        return charityRepository.findAll();
    }

    @Override
    public List<Charity> getCharityByName(String name) {
        return charityRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Charity getCharityById(Long id) {
        return charityRepository.findById(id).orElseThrow(
                () -> new HttpException(HttpStatus.NOT_FOUND, "Charity with id: " + id + " not found")
        );
    }

    @Override
    @Transactional
    public void createCharity(CharityDto charityDto, String fileUrl) {
        Charity charity = new Charity();
        charity.update(charityDto);
        charity.setPhotoUrl(fileUrl);
        charityRepository.save(charity);
    }

    @Override
    @Transactional
    public void updateCharity(CharityDto charityDto) {
        Charity charity = getCharityById(charityDto.getId());
        charity.update(charityDto);
        charityRepository.save(charity);
    }

    @Override
    @Transactional
    public void deleteCharity(Long id) {
        charityRepository.deleteById(id);
    }
}
