package com.example.assistanceservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cx_law_articles")
@Table
public class LawArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToMany(targetEntity = LawArticleDescription.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private List<LawArticleDescription> descriptions;
}
