package com.example.assistanceservice.model;

import com.example.assistanceservice.dto.LawDto;
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
@Entity(name = "s_laws")
@Table
public class Law {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String title;

    @OneToMany(targetEntity = LawArticle.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "law_id", referencedColumnName = "id")
    private List<LawArticle> lawArticles;

    public void update(LawDto lawDto) {
        this.type = lawDto.getType();
        this.title = lawDto.getTitle();
        this.lawArticles = lawDto.getLawArticles();
    }
}
