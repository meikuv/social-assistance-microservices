package com.example.assistanceservice.service.impl;

import com.example.assistanceservice.dto.ReviewDto;
import com.example.assistanceservice.model.Review;
import com.example.assistanceservice.repository.ReviewRepository;
import com.example.assistanceservice.service.IReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements IReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public void createReview(ReviewDto reviewDto) {
        Review newReview = new Review();
        newReview.update(reviewDto);
        reviewRepository.save(newReview);
    }
}
