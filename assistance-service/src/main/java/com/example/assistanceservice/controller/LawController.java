package com.example.assistanceservice.controller;

import com.example.assistanceservice.dto.LawDto;
import com.example.assistanceservice.model.Law;
import com.example.assistanceservice.payload.response.MessageResponse;
import com.example.assistanceservice.service.ILawService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assistance/law")
@RequiredArgsConstructor
public class LawController {
    private final ILawService lawService;

    @GetMapping("/getAll")
    public List<Law> getAll() {
        return lawService.getAllLaw();
    }

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> create(@RequestBody LawDto lawDto) {
        lawService.createLaw(lawDto);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Law " + lawDto.getTitle() + " created successfully"));
    }
}
