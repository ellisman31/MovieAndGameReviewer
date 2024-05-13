package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.game.GameDTO;
import com.src.movieandgamereview.dto.game.GameGenreDTO;
import com.src.movieandgamereview.dto.game.GameGenreGamesDTO;
import com.src.movieandgamereview.model.game.Game;
import com.src.movieandgamereview.model.game.GameGenre;
import com.src.movieandgamereview.repository.GameGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameGenreService {

    @Autowired
    private GameGenreRepository gameGenreRepository;
    @Autowired
    private GameService gameService;

    public List<GameGenreDTO> getAllGameGenre() {
        return ((List<GameGenre>) gameGenreRepository.findAll()).stream().map(this::convertToGameGenreDTO).collect(Collectors.toList());
    }

    public GameGenre findGameGenreById(Long currentGameGenreId) {
        Optional<GameGenre> getGameGenre = gameGenreRepository.findById(currentGameGenreId);
        return getGameGenre.orElse(null);
    }

    public GameGenreDTO findAndGetGameGenreDTOById(Long currentGameGenreId) {
        Optional<GameGenre> getGameGenre = gameGenreRepository.findById(currentGameGenreId);
        return getGameGenre.map(this::convertToGameGenreDTO).orElse(null);
    }

    public GameGenreGamesDTO findAndGetGameGenreGamesDTOById(Long currentGameGenreId) {
        Optional<GameGenre> getGameGenre = gameGenreRepository.findById(currentGameGenreId);
        return getGameGenre.map(this::convertGameGenreGamesToDTO).orElse(null);
    }

    public void saveGameGenre(GameGenre gameGenre) {
        gameGenreRepository.save(gameGenre);
    }

    public void updateGameGenre(Long currentGameGenreId, GameGenre newGameGenreData) {
        GameGenre currentGameGenre = findGameGenreById(currentGameGenreId);
        if (newGameGenreData.getName() != null) {
            currentGameGenre.setName(newGameGenreData.getName());
        }
        saveGameGenre(currentGameGenre);
    }

    public void addGameToGameGenre(Long currentGameGenreId, Game game) {
        GameGenre currentGameGenre = findGameGenreById(currentGameGenreId);
        currentGameGenre.getGames().add(game);
        saveGameGenre(currentGameGenre);
    }

    public void removeGameFromGameGenre(Long currentGameGenreId, Game game) {
        GameGenre currentGameGenre = findGameGenreById(currentGameGenreId);
        currentGameGenre.getGames().remove(game);
        saveGameGenre(currentGameGenre);
    }

    public void deleteGameGenre(Long currentGameGenreId) {
        GameGenre getGameGenre = findGameGenreById(currentGameGenreId);
        getGameGenre.getGames().forEach(game -> {
            game.setGameGenre(null);
            gameService.updateGame(game.getId(), game);
        });
        gameGenreRepository.delete(getGameGenre);
    }

    protected GameGenreDTO convertToGameGenreDTO(GameGenre gameGenre) {
        return new GameGenreDTO(gameGenre.getName());
    }

    protected GameGenreGamesDTO convertGameGenreGamesToDTO(GameGenre gameGenre) {
        Set<GameDTO> games = gameGenre.getGames()
                .stream()
                .map(gameService::convertToGameDTO)
                .collect(Collectors.toSet());

        return new GameGenreGamesDTO(games);
    }

}
