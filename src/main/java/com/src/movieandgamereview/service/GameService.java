package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.GameDTO;
import com.src.movieandgamereview.dto.GameGenreDTO;
import com.src.movieandgamereview.dto.InformationDTO;
import com.src.movieandgamereview.model.Game;
import com.src.movieandgamereview.model.GameGenre;
import com.src.movieandgamereview.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<GameDTO> getAllGame() {
        return ((List<Game>) gameRepository.findAll()).stream().map(this::convertToGameDTO).collect(Collectors.toList());
    }

    public Game findGameById(Long gameId) {
        Optional<Game> getGame = gameRepository.findById(gameId);
        return getGame.orElse(null);
    }

    public GameDTO findAndGetGameDTOById(Long gameId) {
        Optional<Game> getGame = gameRepository.findById(gameId);
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

}
