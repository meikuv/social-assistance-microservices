package com.example.assistanceservice.service.impl;

import com.example.assistanceservice.dto.VolunteerDto;
import com.example.assistanceservice.exception.HttpException;
import com.example.assistanceservice.model.Volunteer;
import com.example.assistanceservice.repository.VolunteerRepository;
import com.example.assistanceservice.service.IVolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerServiceImpl implements IVolunteerService {
    private final VolunteerRepository volunteerRepository;

    @Override
    public List<Volunteer> getAllVolunteer() {
        return volunteerRepository.findAll();
    }

    @Override
    public List<Volunteer> getVolunteerByName(String volunteerName) {
        return volunteerRepository.findByNameContainingIgnoreCase(volunteerName);
    }

    @Override
    public Volunteer getVolunteerById(Long id) {
        return volunteerRepository.findById(id).orElseThrow(
                () -> new HttpException(HttpStatus.NOT_FOUND, "The volunteer with id " + id + " does not exist.")
        );
    }

    @Override
    @Transactional
    public void createVolunteer(VolunteerDto volunteerDto, String fileUrl) {
        Volunteer volunteer = new Volunteer();
        volunteer.update(volunteerDto);
        volunteer.setPhotoUrl(fileUrl);
        volunteerRepository.save(volunteer);
    }

    @Override
    @Transactional
    public void updateVolunteer(VolunteerDto volunteerDto) {
        Volunteer volunteer = getVolunteerById(volunteerDto.getId());
        volunteer.update(volunteerDto);
        volunteerRepository.save(volunteer);
    }

    @Override
    @Transactional
    public void deleteVolunteer(Long id) {
        volunteerRepository.deleteById(id);
    }
}
