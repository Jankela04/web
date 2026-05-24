package com.rastkela.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rastkela.dto.home.GameBasic;
import com.rastkela.dto.home.GameDetail;
import com.rastkela.dto.home.HomeResponse;
import com.rastkela.model.Game;
import com.rastkela.model.Review;
import com.rastkela.service.GameCategoryService;
import com.rastkela.service.GameService;
import com.rastkela.service.ReviewService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    private GameService gameService;
    
    @Autowired
    private ReviewService reviewService;

    // @Autowired TODO: kada Rastko zavrsi
    // private UserService userService;

    @GetMapping
    public HomeResponse  getHomeStats(HttpSession session) {
        HomeResponse res = new HomeResponse();

        res.setGameCount(gameService.countActiveGames());
        // stats.setPlayerCount(userService.countRegisteredUsers());

        List<Game> games = gameService.findAll();

        boolean isLoggedIn = session.getAttribute("user") != null;
        // boolean isLoggedIn = true; za testiranje

        if(isLoggedIn){
            List<GameDetail> detailedGames = new ArrayList<>();
            for(Game game: games){
                List<Review> reviews = reviewService.getReviewsByGame(game.getId());
                double avgScore = reviewService.getAverageScore(reviews);

                detailedGames.add(new GameDetail(
                    game.getId(),
                    game.getName(),
                    game.getImage(),
                    game.getCategory().getName(),
                    avgScore
                ));
            }
            res.setGames(detailedGames);
        }
        else{
            List<GameBasic> basicGames = new ArrayList<>();
            for(Game game: games){
                basicGames.add(new GameBasic(game.getId(),game.getName()));
            }
            res.setGames(basicGames);
        }

        return res;
    }
    
}
