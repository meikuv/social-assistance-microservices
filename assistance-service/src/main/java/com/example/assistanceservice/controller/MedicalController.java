package com.example.assistanceservice.controller;

import com.example.assistanceservice.dto.LawyerDto;
import com.example.assistanceservice.dto.MedicalDto;
import com.example.assistanceservice.model.Medical;
import com.example.assistanceservice.payload.response.MessageResponse;
import com.example.assistanceservice.service.IMedicalService;
import com.example.assistanceservice.service.IS3Service;
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
@RequestMapping("/api/v1/assistance/medical")
@RequiredArgsConstructor
public class MedicalController {
    private final IMedicalService medicalService;
    private final IS3Service s3Service;

    @GetMapping("/getAll")
    public List<Medical> getAll() {
        return medicalService.getMedicals();
    }

    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MessageResponse> create(
            @RequestParam("file") MultipartFile file,
            @RequestParam("request") String medicalDto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        MedicalDto medicalDtoObject = mapper.readValue(medicalDto, MedicalDto.class);
        s3Service.uploadFile(file.getOriginalFilename(), file);
        String url = s3Service.getFile(file.getOriginalFilename());
        medicalService.create(medicalDtoObject, url);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Medical " + medicalDtoObject.getName() + " created successfully"));
    }
}
