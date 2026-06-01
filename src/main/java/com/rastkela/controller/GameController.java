package com.rastkela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rastkela.dto.game.GameBasicDto;
import com.rastkela.model.Game;
import com.rastkela.service.GameService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

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
    

    
}
