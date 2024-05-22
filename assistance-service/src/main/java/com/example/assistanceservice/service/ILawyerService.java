package com.example.assistanceservice.service;


import com.example.assistanceservice.dto.LawyerDto;
import com.example.assistanceservice.model.Lawyer;

import java.util.List;

public interface ILawyerService {
    List<Lawyer> getAllLawyer();

    List<Lawyer> getLawyerByName(String name);

    Lawyer getLawyerById(Long id);

    void createLawyer(LawyerDto lawyerDto, String fileUrl);

    void updateLawyer(LawyerDto volunteerDto);

    void deleteLawyer(Long id);
}
