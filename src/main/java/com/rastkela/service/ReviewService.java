package com.rastkela.service;

import java.util.List;

import com.rastkela.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rastkela.dto.CreateReviewDto;
import com.rastkela.model.Game;
import com.rastkela.model.Review;
import com.rastkela.repository.GameRepository;
import com.rastkela.repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Review> getReviewsByGame(Long gameId){
        return reviewRepository.findByGameId(gameId);
    }

    public List<Review> getReviewsByUser(Long userId){
        return reviewRepository.findByUserId(userId);
    }


    public double getAverageScore(List<Review> reviews){
        long sum = 0;
        for(Review rev: reviews){
            sum+=rev.getRating();
        }
        return (double) sum / reviews.size();
    }

    public Review createReview(CreateReviewDto reviewDto){
        if(reviewRepository.existsByGameIdAndUserId(reviewDto.getGameId(), reviewDto.getUserId())){
            throw new RuntimeException("Recenzija od tog korisnika za tu igricu vec postoji");
        }

        Review newReview = new Review();

        Game game = gameRepository.findById(reviewDto.getGameId()).orElseThrow();
        // User user = userRepository.findById(reviewDto.getUserId()).orElseThrow();

        newReview.setRating(reviewDto.getRating());
        newReview.setGame(game);
        // newReview.setUser(user);
        if(reviewDto.getDescription().isPresent())
            newReview.setDescription(reviewDto.getDescription().get());

        return reviewRepository.save(newReview);
    }
}
