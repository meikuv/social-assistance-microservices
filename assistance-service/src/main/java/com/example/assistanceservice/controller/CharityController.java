package com.example.assistanceservice.controller;

import com.example.assistanceservice.dto.CharityDto;
import com.example.assistanceservice.model.Charity;
import com.example.assistanceservice.payload.response.MessageResponse;
import com.example.assistanceservice.service.ICharityService;
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
@RequestMapping("/api/v1/assistance/charity")
@RequiredArgsConstructor
public class CharityController {
    private final ICharityService charityService;
    private final IS3Service s3Service;

    @GetMapping("/getAll")
    public List<Charity> getAll() {
        return charityService.getAllCharity();
    }

    @GetMapping("/getCharity/{id}")
    public Charity getCharity(@PathVariable Long id) {
        return charityService.getCharityById(id);
    }

    @GetMapping("/search/{name}")
    public List<Charity> searchCharityByName(@PathVariable String name) {
        return charityService.getCharityByName(name);
    }

    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MessageResponse> create(
            @RequestParam("file") MultipartFile file,
            @RequestParam("request") String charityDto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        CharityDto charityDtoObject = mapper.readValue(charityDto, CharityDto.class);
        s3Service.uploadFile(file.getOriginalFilename(), file);
        String url = s3Service.getFile(file.getOriginalFilename());
        charityService.createCharity(charityDtoObject, url);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Charity " + charityDtoObject.getName() + " created successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> update(@RequestBody CharityDto charityDto) {
        charityService.updateCharity(charityDto);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Charity " + charityDto.getName() + " updated successfully"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessageResponse> delete(@RequestBody CharityDto charityDto) {
        charityService.deleteCharity(charityDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Charity " + charityDto.getName() + " deleted successfully"));
    }
}
