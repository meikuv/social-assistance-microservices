package com.example.assistanceservice.service;

import com.example.assistanceservice.dto.CharityDto;
import com.example.assistanceservice.model.Charity;

import java.util.List;

public interface ICharityService {
    List<Charity> getAllCharity();

    List<Charity> getCharityByName(String name);

    Charity getCharityById(Long id);

    void createCharity(CharityDto charityDto, String fileUrl);

    void updateCharity(CharityDto charityDto);

    void deleteCharity(Long id);
}
