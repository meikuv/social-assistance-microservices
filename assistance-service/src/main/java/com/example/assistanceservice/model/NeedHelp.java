package com.example.assistanceservice.model;

import com.example.assistanceservice.dto.NeedHelpDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cx_need_help")
@Table
public class NeedHelp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String organization;
    private String username;
    private String fullName;
    private String supportType;
    private String expand;
    private String phoneNumber;
    private LocalDateTime createdAt;

    public void update(NeedHelpDto needHelpDto) {
        this.type = "needHelp";
        this.organization = needHelpDto.getOrganization();
        this.username = needHelpDto.getUsername();
        this.fullName = needHelpDto.getFullName();
        this.supportType = needHelpDto.getSupportType();
        this.expand = needHelpDto.getExpand();
        this.phoneNumber = needHelpDto.getPhoneNumber();
        this.createdAt = LocalDateTime.now();
    }
}
