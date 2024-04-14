package com.example.assistanceservice.controller;

import com.example.assistanceservice.dto.AssistanceDTO;
import com.example.assistanceservice.model.Assistance;
import com.example.assistanceservice.payload.response.MessageResponse;
import com.example.assistanceservice.service.IAssistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assistance")
@RequiredArgsConstructor
public class AssistanceController {
    private final IAssistanceService assistanceService;

    @GetMapping("/getAllAssistance")
    public List<Assistance> getAllAssistance() {
        return assistanceService.getAllAssistance();
    }

    @PostMapping("/createAssistance")
    public ResponseEntity<MessageResponse> createAssistance(@RequestBody AssistanceDTO assistanceDTO) {
        assistanceService.createAssistance(assistanceDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Assistance: " + assistanceDTO.getName() + " created successfully"));
    }

    @PutMapping("/updateAssistance")
    public ResponseEntity<MessageResponse> updateAssistance(@RequestBody AssistanceDTO assistanceDTO) {
        System.out.println(assistanceDTO.getName());
        assistanceService.updateAssistance(assistanceDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Assistance: " + assistanceDTO.getName() + " updated successfully"));
    }

    @DeleteMapping("/deleteAssistance")
    public ResponseEntity<MessageResponse> deleteAssistance(@RequestBody AssistanceDTO assistanceDTO) {
        assistanceService.deleteAssistance(assistanceDTO.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Assistance: " + assistanceDTO.getName() + " deleted successfully"));
    }
}
