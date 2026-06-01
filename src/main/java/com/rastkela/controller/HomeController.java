package com.rastkela.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rastkela.dto.HomeResponse;
import com.rastkela.model.Game;
import com.rastkela.service.GameService;

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

        List<Game> games = gameService.findAllActive();

        // boolean isLoggedIn = session.getAttribute("user") != null;
        boolean isLoggedIn = true;// za testiranje

        if (isLoggedIn) {
            res.setGames(gameService.toDetailDto(games));
        } else {
            res.setGames(GameService.toBasicDto(games));
        }

        return res;
    }

}
