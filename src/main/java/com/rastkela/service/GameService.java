package com.rastkela.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rastkela.dto.game.GameBasicDto;
import com.rastkela.dto.game.GameDetailDto;
import com.rastkela.dto.game.GameFormDto;
import com.rastkela.model.Game;
import com.rastkela.model.GameCategory;
import com.rastkela.repository.GameCategoryRepository;
import com.rastkela.repository.GameRepository;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private GameCategoryRepository categoryRepository;

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public List<Game> findAllActive() {
        return gameRepository.findByActiveTrue();
    }

    public Game findOne(Long id) {
        return gameRepository.findById(id).orElseThrow();
    }

    public Game updateGame(Long gameId, GameFormDto gameFormDto) {
        Game game = gameRepository.findById(gameId).orElseThrow(); // ne bi trebalo da baci izuzetak

        game.setName(gameFormDto.getName());
        game.setDescription(gameFormDto.getDescription());
        game.setPath(gameFormDto.getPath());
        game.setImage(gameFormDto.getImagePath());
        game.setActive(gameFormDto.isActive());

        GameCategory category = categoryRepository.findById(gameFormDto.getCategoryId()).orElseThrow(); // mada nikad ne
                                                                                                        // bi trebalo da
                                                                                                        // baci izuzetak
        game.setCategory(category);

        return gameRepository.save(game);
    }

    public Game createGame(GameFormDto gameFormDto) {
        Game newGame = new Game();
        newGame.setName(gameFormDto.getName());
        newGame.setDescription(gameFormDto.getDescription());
        newGame.setPath(gameFormDto.getPath());
        newGame.setImage(gameFormDto.getImagePath());
        newGame.setAddedDate(LocalDate.now());
        newGame.setActive(gameFormDto.isActive());

        GameCategory category = categoryRepository.findById(gameFormDto.getCategoryId()).orElseThrow(); // mada nikad ne
                                                                                                        // bi trebalo da
                                                                                                        // baci izuzetak
        newGame.setCategory(category);

        return gameRepository.save(newGame);
    }

    public Long countActiveGames() {
        return gameRepository.countByActiveTrue();
    }

    public void activateGame(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow();
        game.setActive(true);
        gameRepository.save(game);
    }

    public void deactivateGame(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow();
        game.setActive(false);
        gameRepository.save(game);
    }

    public static List<GameBasicDto> toBasicDto(List<Game> games) {
        List<GameBasicDto> basicGames = new ArrayList<>();
        for (Game game : games) {
            basicGames.add(new GameBasicDto(game.getId(), game.getName()));
        }
        return basicGames;
    }

    public List<GameDetailDto> toDetailDto(List<Game> games) {
        List<GameDetailDto> detailedGames = new ArrayList<>();
        Map<Long,Double> avgScores = reviewService.getAverageScoreMap();

        for (Game game : games) {
            detailedGames.add(new GameDetailDto(
                    game.getId(),
                    game.getName(),
                    game.getImage(),
                    game.getCategory().getName(),
                    avgScores.getOrDefault(game.getId(), 0.0),
                    game.getPath(),
                    game.isActive(),
                    game.getDescription()
                ));
        }
        return detailedGames;
    }
}
