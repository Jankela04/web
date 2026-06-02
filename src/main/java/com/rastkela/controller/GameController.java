package com.rastkela.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rastkela.dto.game.GameBasicDto;
import com.rastkela.dto.game.GameDetailDto;
import com.rastkela.dto.game.GameFormDto;
import com.rastkela.dto.game.GameResponse;
import com.rastkela.model.Game;
import com.rastkela.model.Review;
import com.rastkela.service.AuthService;
import com.rastkela.service.GameService;
import com.rastkela.service.ReviewService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private AuthService authService;

    @GetMapping
    public List<? extends GameBasicDto> getAllGames(HttpSession session) {
        boolean isLoggedIn = authService.isLoggedIn(session);
        // boolean isLoggedIn = true;// za testiranje

        List<? extends GameBasicDto> res;

        List<Game> games = gameService.findAllActive();

        if (isLoggedIn) {
            res = gameService.toDetailDto(games);
        } else {
            res = GameService.toBasicDto(games);
        }    

        return res;
    }

    @GetMapping("/{id}")
    public GameResponse getGameById(@PathVariable Long id, HttpSession session) {
        boolean isLoggedIn = authService.isLoggedIn(session);
        // boolean isLoggedIn = true;// za testiranje

        Game game = gameService.findOne(id);

        List<Review> reviews = reviewService.getReviewsByGame(id);
        double avgScore = reviewService.getAverageScore(reviews);

        if(isLoggedIn){
            return new GameDetailDto(
                game.getId(),
                game.getName(),
                game.getImage(),
                game.getCategory().getName(),
                avgScore,
                game.getPath(),
                reviews,
                game.isActive(),
                game.getDescription()
            );
        } else{
            return new GameBasicDto(
                game.getId(),
                game.getName(),
                reviews
            );
        }
    }

    @PostMapping
    public ResponseEntity<Game> createGame(HttpSession session, @RequestBody GameFormDto newGameData) {
        // boolean isAdmin = authService.isAdmin(session);
        boolean isAdmin = true;

        if(!isAdmin){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Game newGame = gameService.createGame(newGameData);

        return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@RequestBody GameFormDto gameData,@PathVariable Long id, HttpSession session) {
        // boolean isAdmin = authService.isAdmin(session);
        boolean isAdmin = true;

        if(!isAdmin){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Game newGame = gameService.updateGame(id, gameData);

        return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<String> activateGame(@PathVariable Long id, HttpSession session) {
        // boolean isAdmin = authService.isAdmin(session);
        boolean isAdmin = true;

        if(!isAdmin){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Game game = gameService.findOne(id);

        if(game.isActive()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Game is already active");
        } 
        
        gameService.activateGame(id);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
    
    @PostMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateGame(@PathVariable Long id, HttpSession session) {
        // boolean isAdmin = authService.isAdmin(session);
        boolean isAdmin = true;

        if(!isAdmin){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Game game = gameService.findOne(id);

        if(!game.isActive()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Game is already not active");
        } 
        
        gameService.deactivateGame(id);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
