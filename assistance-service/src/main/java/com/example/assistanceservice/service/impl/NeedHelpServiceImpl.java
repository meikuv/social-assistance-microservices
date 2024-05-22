package com.example.assistanceservice.service.impl;

import com.example.assistanceservice.dto.NeedHelpDto;
import com.example.assistanceservice.model.NeedHelp;
import com.example.assistanceservice.repository.NeedHelpRepository;
import com.example.assistanceservice.service.INeedHelpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NeedHelpServiceImpl implements INeedHelpService {
    private final NeedHelpRepository needHelpRepository;

    @Override
    public List<NeedHelp> getAllNeedHelpByUsername(String username) {
        return needHelpRepository.findAllByUsername(username);
    }

    @Override
    public void create(NeedHelpDto needHelpDto) {
        NeedHelp newNeedHelp = new NeedHelp();
        newNeedHelp.update(needHelpDto);
        needHelpRepository.save(newNeedHelp);
    }

    @Override
    public void deleteById(Long id) {
        needHelpRepository.deleteById(id);
    }
}
