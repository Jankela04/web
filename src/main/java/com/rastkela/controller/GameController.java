package com.rastkela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rastkela.dto.game.GameBasicDto;
import com.rastkela.dto.game.GameDetailDto;
import com.rastkela.dto.game.GameResponse;
import com.rastkela.model.Game;
import com.rastkela.model.Review;
import com.rastkela.service.GameService;
import com.rastkela.service.ReviewService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<? extends GameBasicDto> getAllGames(HttpSession session) {
        List<? extends GameBasicDto> res;

        // boolean isLoggedIn = session.getAttribute("user") != null;
        boolean isLoggedIn = true;// za testiranje

        List<Game> games = gameService.findAll();

        if (isLoggedIn) {
            res = gameService.toDetailDto(games);
        } else {
            res = GameService.toBasicDto(games);
        }    

        return res;
    }

    @GetMapping("/{id}")
    public GameResponse getGameById(@PathVariable Long id, HttpSession session) {
        Game game = gameService.findOne(id);

        List<Review> reviews = reviewService.getReviewsByGame(id);
        double avgScore = reviewService.getAverageScore(reviews);


        boolean isLoggedIn = session.getAttribute("user") != null;
        // boolean isLoggedIn = true;// za testiranje

        if(isLoggedIn){
            return new GameDetailDto(
                game.getId(),
                game.getName(),
                game.getImage(),
                game.getCategory().getName(),
                avgScore,
                game.getPath(),
                reviews
            );
        } else{
            return new GameBasicDto(
                game.getId(),
                game.getName(),
                reviews
            );
        }
    }
}
