package com.example.assistanceservice.controller;

import com.example.assistanceservice.dto.VolunteerDto;
import com.example.assistanceservice.model.Volunteer;
import com.example.assistanceservice.payload.response.MessageResponse;
import com.example.assistanceservice.service.IS3Service;
import com.example.assistanceservice.service.IVolunteerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assistance/volunteer")
@RequiredArgsConstructor
public class VolunteerController {
    private final IS3Service s3Service;
    private final IVolunteerService volunteerService;

    @GetMapping("/getAll")
    public List<Volunteer> getAll() {
        return volunteerService.getAllVolunteer();
    }

    @GetMapping("/getVolunteer/{id}")
    public Volunteer getVolunteer(@PathVariable Long id) {
        return volunteerService.getVolunteerById(id);
    }

    @GetMapping("/search/{name}")
    public List<Volunteer> searchVolunteerByName(@PathVariable String name) {
        return volunteerService.getVolunteerByName(name);
    }

    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MessageResponse> create(
            @RequestParam("file") MultipartFile file,
            @RequestParam("request") String volunteerDto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        VolunteerDto volunteerDtoObject = mapper.readValue(volunteerDto, VolunteerDto.class);
        s3Service.uploadFile(file.getOriginalFilename(), file);
        String url = s3Service.getFile(file.getOriginalFilename());
        volunteerService.createVolunteer(volunteerDtoObject, url);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Volunteer " + volunteerDtoObject.getName() + " created successfully"));
    }

    @PostMapping("/update")
    public ResponseEntity<MessageResponse> update(@RequestBody VolunteerDto volunteerDto) {
        volunteerService.updateVolunteer(volunteerDto);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Volunteer " + volunteerDto.getName() + " updated successfully"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessageResponse> delete(@RequestBody VolunteerDto volunteerDto) {
        volunteerService.deleteVolunteer(volunteerDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Volunteer " + volunteerDto.getName() + " deleted successfully"));
    }
}
