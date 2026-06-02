package com.rastkela.controller;

import com.rastkela.dto.ReviewResponseDto;
import com.rastkela.dto.game.GameBasicDto;
import com.rastkela.dto.game.GameDetailDto;
import com.rastkela.dto.game.GameFormDto;
import com.rastkela.dto.game.GameResponse;
import com.rastkela.exception.ForbiddenException;
import com.rastkela.model.Game;
import com.rastkela.model.Review;
import com.rastkela.service.AuthService;
import com.rastkela.service.GameService;
import com.rastkela.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

        Game game = gameService.findOne(id);

        List<Review> reviews = reviewService.getReviewsByGame(id);
        double avgScore = reviewService.getAverageScore(reviews);

        List<ReviewResponseDto> reviewsDto = reviews.stream().map(review -> ReviewService.toDto(review)).toList();

        if(!isLoggedIn || !game.isActive()){
            return new GameBasicDto(
                game.getId(),
                game.getName(),
                reviewsDto
            );
        }
        return new GameDetailDto(
                game.getId(),
                game.getName(),
                game.getImage(),
                game.getCategory().getName(),
                avgScore,
                game.getPath(),
                reviewsDto,
                game.isActive(),
                game.getDescription());
    }

    @PostMapping
    public ResponseEntity<Game> createGame(HttpSession session, @RequestBody GameFormDto newGameData) {
        boolean isAdmin = authService.isAdmin(session);

        if(!isAdmin){
            throw new ForbiddenException("Admin privileges required") ;
        }
        Game newGame = gameService.createGame(newGameData);

        return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@RequestBody GameFormDto gameData,@PathVariable Long id, HttpSession session) {
        boolean isAdmin = authService.isAdmin(session);

        if(!isAdmin){
            throw new ForbiddenException("Admin privileges required") ;
        }
        Game newGame = gameService.updateGame(id, gameData);

        return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<String> activateGame(@PathVariable Long id, HttpSession session) {
        boolean isAdmin = authService.isAdmin(session);

        if(!isAdmin){
            throw new ForbiddenException("Admin privileges required") ;
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
        boolean isAdmin = authService.isAdmin(session);

        if(!isAdmin){
            throw new ForbiddenException("Admin privileges required") ;
        }

        Game game = gameService.findOne(id);

        if(!game.isActive()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Game is already not active");
        } 
        
        gameService.deactivateGame(id);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
