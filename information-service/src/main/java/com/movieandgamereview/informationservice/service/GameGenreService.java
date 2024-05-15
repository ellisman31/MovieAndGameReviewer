package com.movieandgamereview.informationservice.service;

import com.movieandgamereview.informationservice.dto.game.GameDTO;
import com.movieandgamereview.informationservice.dto.game.GameGenreDTO;
import com.movieandgamereview.informationservice.dto.game.GameGenreGamesDTO;
import com.movieandgamereview.informationservice.model.game.Game;
import com.movieandgamereview.informationservice.model.game.GameGenre;
import com.movieandgamereview.informationservice.repository.GameGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameGenreService {
    @Autowired(required=false)
    private GameGenreRepository gameGenreRepository;
    @Autowired(required=false)
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
        if (newGameGenreData.getGames() != null) {
            currentGameGenre.setGames(newGameGenreData.getGames());
        }
        saveGameGenre(currentGameGenre);
    }

    public void addGameToGameGenre(Long currentGameGenreId, Long currentGameId) {
        GameGenre currentGameGenre = findGameGenreById(currentGameGenreId);
        Game getGame = gameService.findGameById(currentGameId);
        if (getGame != null && !currentGameGenre.getGames().contains(getGame)) {
            currentGameGenre.getGames().add(getGame);
            updateGameGenre(currentGameGenreId, currentGameGenre);
        }
    }

    public void removeGameFromGameGenre(Long currentGameGenreId, Long currentGameId) {
        GameGenre currentGameGenre = findGameGenreById(currentGameGenreId);
        Game getGame = gameService.findGameById(currentGameId);
        if (getGame != null && currentGameGenre.getGames().contains(getGame)) {
            currentGameGenre.getGames().remove(getGame);
            updateGameGenre(currentGameGenreId, currentGameGenre);
        }
    }

    public void deleteGameGenre(Long currentGameGenreId) {
        GameGenre getGameGenre = findGameGenreById(currentGameGenreId);
        getGameGenre.getGames().forEach(game -> {
            game.getGameGenres().remove(getGameGenre);
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
