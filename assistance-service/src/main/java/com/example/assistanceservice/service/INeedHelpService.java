package com.example.assistanceservice.service;

import com.example.assistanceservice.dto.NeedHelpDto;
import com.example.assistanceservice.model.NeedHelp;

import java.util.List;

public interface INeedHelpService {
    List<NeedHelp> getAllNeedHelpByUsername(String username);

    void create(NeedHelpDto needHelpDto);

    void deleteById(Long id);
}
