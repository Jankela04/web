package com.rastkela.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.rastkela.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rastkela.dto.CreateReviewDto;
import com.rastkela.dto.ReviewResponseDto;
import com.rastkela.dto.UserDTO;
import com.rastkela.dto.game.GameDetailDto;
import com.rastkela.exception.ResourceNotFoundException;
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

    public static ReviewResponseDto toDto(Review review){
        UserDTO userDto = UserDTO.fromEntity(review.getUser());
        Game game = review.getGame();
        GameDetailDto gameDto = new GameDetailDto(
            game.getId(),
            game.getName(),
            game.getImage(),
            game.getCategory().getName(),
            0,
            game.getPath(),
            game.isActive(),
            game.getDescription()
        );

        return new ReviewResponseDto(
            review.getId(),
            review.getDescription(),
            review.getRating(),
            userDto,
            gameDto
        );
    }

    public double getAverageScore(List<Review> reviews){
        long sum = 0;
        for(Review rev: reviews){
            sum+=rev.getRating();
        }
        return (double) sum / reviews.size();
    }

    public Map<Long,Double> getAverageScoreMap(){
        return reviewRepository.findAverageScores()
        .stream()
        .collect(Collectors.toMap(
                row -> (Long) row[0],
                row -> (Double) row[1]
        ));
    }

    public Review createReview(CreateReviewDto reviewDto){
        if(reviewRepository.existsByGameIdAndUserId(reviewDto.getGameId(), reviewDto.getUserId())){
            throw new RuntimeException("Recenzija od tog korisnika za tu igricu vec postoji");
        }
        int rating = reviewDto.getRating();

        if(rating >= 6 || rating <= 0){
            throw new RuntimeException("Rating mora biti izmedju 1 i 5");
        }

        Review newReview = new Review();

        Game game = gameRepository.findById(reviewDto.getGameId())
            .orElseThrow(() -> new ResourceNotFoundException("Game not found"));
        // User user = userRepository.findById(reviewDto.getUserId())
            // .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        newReview.setRating(reviewDto.getRating());
        newReview.setGame(game);
        // newReview.setUser(user);
        if(reviewDto.getDescription().isPresent())
            newReview.setDescription(reviewDto.getDescription().get());

        return reviewRepository.save(newReview);
    }
}
