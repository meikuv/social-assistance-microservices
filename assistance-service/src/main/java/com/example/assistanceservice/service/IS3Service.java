package com.example.assistanceservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface IS3Service {
    void uploadFile(String keyName, MultipartFile file);

    String getFile(String keyName);

    boolean deleteFile(String keyName);
}