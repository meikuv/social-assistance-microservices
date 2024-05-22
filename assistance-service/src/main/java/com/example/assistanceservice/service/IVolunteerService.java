package com.example.assistanceservice.service;

import com.example.assistanceservice.dto.VolunteerDto;
import com.example.assistanceservice.model.Volunteer;

import java.util.List;

public interface IVolunteerService {
    List<Volunteer> getAllVolunteer();

    List<Volunteer> getVolunteerByName(String volunteerName);

    Volunteer getVolunteerById(Long id);

    void createVolunteer(VolunteerDto volunteerDto, String fileUrl);

    void updateVolunteer(VolunteerDto volunteerDto);

    void deleteVolunteer(Long id);
}
