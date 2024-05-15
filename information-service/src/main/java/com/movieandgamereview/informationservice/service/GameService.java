package com.movieandgamereview.informationservice.service;

import com.movieandgamereview.informationservice.dto.game.GameDTO;
import com.movieandgamereview.informationservice.dto.game.GameGenreDTO;
import com.movieandgamereview.informationservice.dto.game.GamesGameGenresDTO;
import com.movieandgamereview.informationservice.dto.information.InformationDTO;
import com.movieandgamereview.informationservice.model.Information;
import com.movieandgamereview.informationservice.model.Review;
import com.movieandgamereview.informationservice.model.game.Game;
import com.movieandgamereview.informationservice.model.game.GameGenre;
import com.movieandgamereview.informationservice.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired(required=false)
    private GameRepository gameRepository;
    @Autowired(required=false)
    private GameGenreService gameGenreService;
    @Autowired(required=false)
    private InformationService informationService;
    @Autowired(required=false)
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
