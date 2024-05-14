package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.game.GameDTO;
import com.src.movieandgamereview.dto.game.GameGenreDTO;
import com.src.movieandgamereview.dto.game.GamesGameGenresDTO;
import com.src.movieandgamereview.dto.information.InformationDTO;
import com.src.movieandgamereview.model.*;
import com.src.movieandgamereview.model.game.Game;
import com.src.movieandgamereview.model.game.GameGenre;
import com.src.movieandgamereview.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameGenreService gameGenreService;
    @Autowired
    private InformationService informationService;
    @Autowired
    private ReviewService reviewService;

    public List<GameDTO> getAllGame() {
        return ((List<Game>) gameRepository.findAll()).stream().map(this::convertToGameDTO).collect(Collectors.toList());
    }

    public Game findGameById(Long currentGameId) {
        Optional<Game> getGame = gameRepository.findById(currentGameId);
        return getGame.orElse(null);
    }

    public GameDTO findAndGetGameDTOById(Long currentGameId) {
        Optional<Game> getGame = gameRepository.findById(currentGameId);
        return getGame.map(this::convertToGameDTO).orElse(null);
    }

    public GamesGameGenresDTO findAndGetGamesGameGenresDTO(Long currentGameId) {
        Optional<Game> getGame = gameRepository.findById(currentGameId);
        return getGame.map(this::convertToGamesGameGenresDTO).orElse(null);
    }

    public void saveGame(Game game) {
        gameRepository.save(game);
    }

    public void updateGame(Long gameId, Game newGameData) {
        Game currentGame = findGameById(gameId);
        if (currentGame.getInformation() != null) {
            currentGame.setInformation(newGameData.getInformation());
        }
        if (currentGame.getGameGenres() != null) {
            currentGame.setGameGenres(newGameData.getGameGenres());
        }
        saveGame(currentGame);
    }

    public void deleteGame(Long currentGameId) {
        Game getGame = findGameById(currentGameId);
        getGame.getGameGenres().forEach(gameGenre -> {
            GameGenre getGameGenre = gameGenreService.findGameGenreById(gameGenre.getId());
            getGameGenre.getGames().remove(getGame);
            gameGenreService.updateGameGenre(gameGenre.getId(), getGameGenre);
        });
        List<Review> reviews = reviewService.findReviewByGame(AggregateReference.to(currentGameId));
        reviews.forEach(review -> {
            try {
                reviewService.deleteReview(review.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        gameRepository.delete(getGame);
    }

    public void addGameGenreToGame(Long currentGameId, Long currentGameGenreId) {
        Game getGame = findGameById(currentGameId);
        GameGenre getGameGenre = gameGenreService.findGameGenreById(currentGameGenreId);
        if (getGameGenre != null && !getGame.getGameGenres().contains(getGameGenre)) {
            getGame.getGameGenres().add(getGameGenre);
            updateGame(currentGameId, getGame);
        }
    }

    public void removeGameGenreFromGame(Long currentGameId, Long currentGameGenreId) {
        Game getGame = findGameById(currentGameId);
        GameGenre getGameGenre = gameGenreService.findGameGenreById(currentGameGenreId);
        if (getGameGenre != null && getGame.getGameGenres().contains(getGameGenre)) {
            getGame.getGameGenres().remove(getGameGenre);
            updateGame(currentGameId, getGame);
        }
    }

    protected GameDTO convertToGameDTO(Game game) {
        InformationDTO informationDTO = informationService.findAndGetInformationDTOById(game.getInformation().getId());
        return new GameDTO(informationDTO);
    }

    protected GamesGameGenresDTO convertToGamesGameGenresDTO(Game game) {
        Set<GameGenreDTO> games = game.getGameGenres()
                .stream()
                .map(gameGenre -> gameGenreService.convertToGameGenreDTO(gameGenre))
                .collect(Collectors.toSet());
        return new GamesGameGenresDTO(games);
    }

    public Game findGameByInformation(AggregateReference<Information, Long> information) {
        return gameRepository.findByInformation(information).orElse(null);
    }
}
