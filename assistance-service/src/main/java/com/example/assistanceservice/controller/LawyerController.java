package com.example.assistanceservice.controller;

import com.example.assistanceservice.dto.LawyerDto;
import com.example.assistanceservice.model.Lawyer;
import com.example.assistanceservice.payload.response.MessageResponse;
import com.example.assistanceservice.service.ILawyerService;
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
@RequestMapping("/api/v1/assistance/lawyer")
@RequiredArgsConstructor
public class LawyerController {
    private final ILawyerService lawyerService;
    private final IS3Service s3Service;

    @GetMapping("/getAll")
    public List<Lawyer> getAll() {
        return lawyerService.getAllLawyer();
    }

    @GetMapping("/getLawyer/{id}")
    public Lawyer getLawyer(@PathVariable Long id) {
        return lawyerService.getLawyerById(id);
    }

    @GetMapping("/search/{name}")
    public List<Lawyer> searchLawyerByName(@PathVariable String name) {
        return lawyerService.getLawyerByName(name);
    }

    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MessageResponse> create(
            @RequestParam("file") MultipartFile file,
            @RequestParam("request") String lawyerDto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        LawyerDto lawyerDtoObject = mapper.readValue(lawyerDto, LawyerDto.class);
        s3Service.uploadFile(file.getOriginalFilename(), file);
        String url = s3Service.getFile(file.getOriginalFilename());
        lawyerService.createLawyer(lawyerDtoObject, url);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Lawyer " + lawyerDtoObject.getName() + " created successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> update(@RequestBody LawyerDto lawyerDto) {
        lawyerService.updateLawyer(lawyerDto);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Lawyer " + lawyerDto.getName() + " updated successfully"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessageResponse> delete(@RequestBody LawyerDto lawyerDto) {
        lawyerService.deleteLawyer(lawyerDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Lawyer " + lawyerDto.getId() + " deleted successfully"));
    }
}
