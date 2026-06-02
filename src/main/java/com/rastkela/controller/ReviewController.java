package com.rastkela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rastkela.dto.CreateReviewDto;
import com.rastkela.model.Review;
import com.rastkela.service.ReviewService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RequestMapping("/api/review")
@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getReiviews(
        @RequestParam(required = false) Long userId,
        @RequestParam(required = false) Long gameId) {
            if((userId == null && gameId == null) || (userId != null && gameId != null)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            if(userId != null){
                return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewsByUser(userId));
            } else{
                return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewsByGame(gameId));
            }
    }

    @PostMapping
    public ResponseEntity<Review> createReview(HttpSession session, @RequestBody CreateReviewDto reviewForm) {
        // boolean isAuthorised = session.getAttribute("user").getId() == reviewForm.getUserId();
        boolean isAuthorised = true;

        if(!isAuthorised){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Review newReview = reviewService.createReview(reviewForm);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(newReview);
    }
    
    
}
