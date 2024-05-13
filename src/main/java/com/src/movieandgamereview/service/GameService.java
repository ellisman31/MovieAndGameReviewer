package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.game.GameDTO;
import com.src.movieandgamereview.dto.game.GameGenreDTO;
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

    public void saveGame(Game game) {
        setGameGenreForGame(game);
        gameRepository.save(game);
    }

    public void updateGame(Long gameId, Game newGameData) {
        Game currentGame = findGameById(gameId);
        if (currentGame.getInformation() != null) {
            currentGame.setInformation(newGameData.getInformation());
        }
        if (currentGame.getGameGenre() != null) {
            currentGame.setGameGenre(newGameData.getGameGenre());
            if (currentGame.getGameGenre() != newGameData.getGameGenre()) {
                gameGenreService.removeGameFromGameGenre(currentGame.getGameGenre().getId(), currentGame);
            }
        }
        saveGame(currentGame);
    }

    public void deleteGame(Long currentGameId) {
        Game getGame = findGameById(currentGameId);
        GameGenre gameGenre = gameGenreService.findGameGenreById(getGame.getGameGenre().getId());
        gameGenre.getGames().remove(getGame);
        gameGenreService.updateGameGenre(gameGenre.getId(), gameGenre);
        List<Review> reviews = reviewService.findReviewByGame(AggregateReference.to(currentGameId));
        reviews.forEach(review -> {
            reviewService.deleteReview(review.getId());
        });
        gameRepository.delete(getGame);
    }

    protected GameDTO convertToGameDTO(Game game) {
        InformationDTO informationDTO = informationService.findAndGetInformationDTOById(game.getInformation().getId());
        GameGenreDTO gameGenreDTO = gameGenreService.findAndGetGameGenreDTOById(game.getGameGenre().getId());
        return new GameDTO(informationDTO, gameGenreDTO);
    }

    private void setGameGenreForGame(Game game) {
        GameGenre getGameGenre = gameGenreService.findGameGenreById(game.getGameGenre().getId());
        if (getGameGenre != null) {
            boolean isGameGenreHasTheGame = getGameGenre.getGames().contains(game);
            if (!isGameGenreHasTheGame) {
                gameGenreService.addGameToGameGenre(game.getGameGenre().getId(), game);
            }
        }
    }

    public Game findGameByInformation(AggregateReference<Information, Long> information) {
        return gameRepository.findByInformation(information).orElse(null);
    }
}
