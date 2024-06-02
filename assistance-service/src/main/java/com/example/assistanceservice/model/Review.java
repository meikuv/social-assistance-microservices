package com.example.assistanceservice.model;

import com.example.assistanceservice.dto.ReviewDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cx_review")
@Table
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Integer rating;
    private String comment;

    public void update(ReviewDto reviewDto) {
        this.username = reviewDto.getUsername();
        this.firstName = reviewDto.getFirstName();
        this.lastName = reviewDto.getLastName();
        this.email = reviewDto.getEmail();
        this.rating = reviewDto.getRating();
        this.comment = reviewDto.getComment();
    }
}
