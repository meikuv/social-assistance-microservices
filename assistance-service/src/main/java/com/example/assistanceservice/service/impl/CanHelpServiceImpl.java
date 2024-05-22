package com.example.assistanceservice.service.impl;

import com.example.assistanceservice.dto.CanHelpDto;
import com.example.assistanceservice.model.CanHelp;
import com.example.assistanceservice.repository.CanHelpRepository;
import com.example.assistanceservice.service.ICanHelpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CanHelpServiceImpl implements ICanHelpService {
    private final CanHelpRepository canHelpRepository;


    @Override
    public List<CanHelp> getAllCanHelpByUsername(String username) {
        return canHelpRepository.findAllByUsername(username);
    }

    @Override
    public void create(CanHelpDto canHelpDto) {
        CanHelp newCanHelp = new CanHelp();
        newCanHelp.update(canHelpDto);
        canHelpRepository.save(newCanHelp);
    }

    @Override
    public void deleteById(Long id) {
        canHelpRepository.deleteById(id);
    }
}
