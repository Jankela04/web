package com.rastkela.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rastkela.dto.home.HomeResponse;
import com.rastkela.dto.home.game.GameBasicDto;
import com.rastkela.dto.home.game.GameDetailDto;
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

    // @Autowired TODO: kada Rastko zavrsi
    // private UserService userService;

    @GetMapping
    public HomeResponse getHomeStats(HttpSession session) {
        HomeResponse res = new HomeResponse();

        res.setGameCount(gameService.countActiveGames());
        // stats.setPlayerCount(userService.countRegisteredUsers());

        List<Game> games = gameService.findAll();

        boolean isLoggedIn = session.getAttribute("user") != null;
        // boolean isLoggedIn = true;// za testiranje

        if (isLoggedIn) {
            res.setGames(gameService.toDetailDto(games));
        } else {
            res.setGames(GameService.toBasicDto(games));
        }

        return res;
    }

}
