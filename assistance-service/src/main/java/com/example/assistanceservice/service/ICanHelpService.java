package com.example.assistanceservice.service;

import com.example.assistanceservice.dto.CanHelpDto;
import com.example.assistanceservice.model.CanHelp;

import java.util.List;

public interface ICanHelpService {
    List<CanHelp> getAllCanHelpByUsername(String username);

    void create(CanHelpDto canHelpDto);

    void deleteById(Long id);
}
