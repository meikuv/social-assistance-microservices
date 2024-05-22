package com.example.assistanceservice.dto;

import com.example.assistanceservice.model.LawArticle;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LawDto {
    private Long id;
    private String type;
    private String title;
    private List<LawArticle> lawArticles;
}
