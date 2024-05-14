package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.game.GameDTO;
import com.src.movieandgamereview.dto.movie.MovieDTO;
import com.src.movieandgamereview.dto.rate.RateDTO;
import com.src.movieandgamereview.dto.rate.RateGamesDTO;
import com.src.movieandgamereview.dto.rate.RateMoviesDTO;
import com.src.movieandgamereview.group.Rates;
import com.src.movieandgamereview.model.game.Game;
import com.src.movieandgamereview.model.Information;
import com.src.movieandgamereview.model.movie.Movie;
import com.src.movieandgamereview.model.Rate;
import com.src.movieandgamereview.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RateService {
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private InformationService informationService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private GameService gameService;

    public List<RateDTO> getAllRate() {
        return ((List<Rate>) rateRepository.findAll()).stream().map(this::convertToRateDTO).collect(Collectors.toList());
    }

    public Rate findRateById(Long rateId) {
        Optional<Rate> getRate = rateRepository.findById(rateId);
        return getRate.orElse(null);
    }

    public RateDTO findAndGetRateDTOById(Long currentRateId) {
        Optional<Rate> getRate = rateRepository.findById(currentRateId);
        return getRate.map(this::convertToRateDTO).orElse(null);
    }

    public RateMoviesDTO findAndGetRateMoviesDTOById(Long currentRateId) {
        Optional<Rate> getRate = rateRepository.findById(currentRateId);
        return getRate.map(this::convertDirectorMoviesToDTO).orElse(null);
    }

    public RateGamesDTO findAndGetRateGamesDTOById(Long currentRateId) {
        Optional<Rate> getRate = rateRepository.findById(currentRateId);
        return getRate.map(this::convertDirectorGamesToDTO).orElse(null);
    }

    public void saveRate(Rate rate) {
        rateRepository.save(rate);
    }

    public void updateRate(Long currentRateId, Rate newRateData) {
        Rate currentRate = findRateById(currentRateId);
        if (newRateData.getName() != null) {
            currentRate.setName(newRateData.getName());
        }
        if (newRateData.getMovies() != null) {
            currentRate.setMovies(newRateData.getMovies());
        }
        if (newRateData.getGames() != null) {
            currentRate.setGames(newRateData.getGames());
        }
        saveRate(currentRate);
    }

    public void addRateToGame(Long currentRateId, Long currentGameId) {
        Rate currentRate = findRateById(currentRateId);
        Game getGame = gameService.findGameById(currentGameId);
        if (getGame != null && !currentRate.getGames().contains(getGame)) {
            currentRate.getGames().add(getGame);
            updateRate(currentRateId, currentRate);
        }
    }

    public void removeRateFromGame(Long currentRateId, Long currentGameId) {
        Rate currentRate = findRateById(currentRateId);
        Game getGame = gameService.findGameById(currentGameId);
        if (getGame != null && currentRate.getGames().contains(getGame)) {
            currentRate.getGames().remove(getGame);
            updateRate(currentRateId, currentRate);
        }
    }

    public void addRateToMovie(Long currentRateId, Long currentMovieId) {
        Rate currentRate = findRateById(currentRateId);
        Movie getMovie = movieService.findMovieById(currentMovieId);
        if (getMovie != null && !currentRate.getMovies().contains(getMovie)) {
            currentRate.getMovies().add(getMovie);
            updateRate(currentRateId, currentRate);
        }
    }

    public void removeRateFromMovie(Long currentRateId, Long currentMovieId) {
        Rate currentRate = findRateById(currentRateId);
        Movie getMovie = movieService.findMovieById(currentMovieId);
        if (getMovie != null && !currentRate.getMovies().contains(getMovie)) {
            currentRate.getMovies().remove(getMovie);
            updateRate(currentRateId, currentRate);
        }
    }

    public void deleteRate(Long currentRateId) {
        Rate getRate = findRateById(currentRateId);
        List<Information> informationList = informationService.findInformationByRate(AggregateReference.to(currentRateId));
        informationList.forEach(information -> {
            information.setRate(null);
            informationService.updateInformation(information.getId(), information);
        });
        rateRepository.delete(getRate);
    }

    protected RateDTO convertToRateDTO(Rate rate) {
        return new RateDTO(rate.getName());
    }

    protected RateMoviesDTO convertDirectorMoviesToDTO(Rate rate) {
        Set<MovieDTO> movies = rate.getMovies()
                .stream()
                .map(movieService::convertToMovieDTO)
                .collect(Collectors.toSet());

        return new RateMoviesDTO(movies);
    }

    protected RateGamesDTO convertDirectorGamesToDTO(Rate rate) {
        Set<GameDTO> games = rate.getGames()
                .stream()
                .map(gameService::convertToGameDTO)
                .collect(Collectors.toSet());

        return new RateGamesDTO(games);
    }
}
