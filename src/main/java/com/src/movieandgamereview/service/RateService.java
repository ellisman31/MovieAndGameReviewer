package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.RateDTO;
import com.src.movieandgamereview.model.Game;
import com.src.movieandgamereview.model.Movie;
import com.src.movieandgamereview.model.Rate;
import com.src.movieandgamereview.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    public List<RateDTO> getAllRate() {
        return ((List<Rate>) rateRepository.findAll()).stream().map(this::convertToRateDTO).collect(Collectors.toList());
    }

    public Rate findRateById(Long rateId) {
        Optional<Rate> getRate = rateRepository.findById(rateId);
        return getRate.orElse(null);
    }

    public RateDTO findAndGetRateDTOById(Long rateId) {
        Optional<Rate> getRate = rateRepository.findById(rateId);
        return getRate.map(this::convertToRateDTO).orElse(null);
    }

    public void saveRate(Rate rate) {
        rateRepository.save(rate);
    }

    public void updateRate(Long currentRateId, Rate newRateData) {
        Rate currentRate = findRateById(currentRateId);
        if (newRateData.getName() != null) {
            currentRate.setName(newRateData.getName());
        }
        saveRate(currentRate);
    }

    public void addRateToGame(Long currentRateId, Game game) {
        Rate currentRate = findRateById(currentRateId);
        currentRate.getGames().add(game);
        saveRate(currentRate);
    }

    public void removeRateFromGame(Long currentRateId, Game game) {
        Rate currentRate = findRateById(currentRateId);
        currentRate.getGames().remove(game);
        saveRate(currentRate);
    }

    public void addRateToMovie(Long currentRateId, Movie movie) {
        Rate currentRate = findRateById(currentRateId);
        currentRate.getMovies().add(movie);
        saveRate(currentRate);
    }

    public void removeRateFromMovie(Long currentRateId, Movie movie) {
        Rate currentRate = findRateById(currentRateId);
        currentRate.getMovies().remove(movie);
        saveRate(currentRate);
    }

    protected RateDTO convertToRateDTO(Rate rate) {
        return new RateDTO(rate.getName());
    }

}
