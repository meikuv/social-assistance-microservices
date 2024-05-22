package com.example.assistanceservice.controller;

import com.example.assistanceservice.dto.CanHelpDto;
import com.example.assistanceservice.dto.NeedHelpDto;
import com.example.assistanceservice.model.CanHelp;
import com.example.assistanceservice.model.NeedHelp;
import com.example.assistanceservice.payload.response.MessageResponse;
import com.example.assistanceservice.service.ICanHelpService;
import com.example.assistanceservice.service.INeedHelpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assistance/help")
@RequiredArgsConstructor
public class HelpController {
    private final ICanHelpService canHelpService;
    private final INeedHelpService needHelpService;

    @GetMapping("/getAllCanHelp/{username}")
    public List<CanHelp> getAllCanHelp(@PathVariable String username) {
        return canHelpService.getAllCanHelpByUsername(username);
    }

    @PostMapping("/createCanHelp")
    public ResponseEntity<MessageResponse> createCanHelp(@RequestBody CanHelpDto canHelpDto) {
        canHelpService.create(canHelpDto);
        return ResponseEntity.ok(new MessageResponse("CanHelp: " + canHelpDto.getOrganization() + " created successfully"));
    }

    @DeleteMapping("/deleteCanHelp/{id}")
    public ResponseEntity<MessageResponse> deleteCanHelp(@PathVariable Long id) {
        canHelpService.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("CanHelp: " + id + " deleted successfully"));
    }

    @GetMapping("/getAllNeedHelp/{username}")
    public List<NeedHelp> getAllNeedHelp(@PathVariable String username) {
        return needHelpService.getAllNeedHelpByUsername(username);
    }

    @PostMapping("/createNeedHelp")
    public ResponseEntity<MessageResponse> createNeedHelp(@RequestBody NeedHelpDto needHelpDto) {
        needHelpService.create(needHelpDto);
        return ResponseEntity.ok(new MessageResponse("NeedHelp: " + needHelpDto.getOrganization() + " created successfully"));
    }

    @DeleteMapping("/deleteNeedHelp/{id}")
    public ResponseEntity<MessageResponse> deleteNeedHelp(@PathVariable Long id) {
        needHelpService.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("NeedHelp: " + id + " deleted successfully"));
    }
}
