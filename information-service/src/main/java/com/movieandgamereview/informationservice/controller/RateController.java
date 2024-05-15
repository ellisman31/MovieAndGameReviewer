package com.movieandgamereview.informationservice.controller;

import com.movieandgamereview.informationservice.dto.rate.RateDTO;
import com.movieandgamereview.informationservice.dto.rate.RateGamesDTO;
import com.movieandgamereview.informationservice.dto.rate.RateMoviesDTO;
import com.movieandgamereview.informationservice.model.Rate;
import com.movieandgamereview.informationservice.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RateController {
    @Autowired
    private RateService rateService;

    @GetMapping("/getAllRate")
    @ResponseBody
    public List<RateDTO> getAllRate() {
        return rateService.getAllRate();
    }

    @GetMapping("/getRateById/{rateId}")
    @ResponseBody
    public RateDTO getRateId(@PathVariable("rateId") Long rateId) {
        return rateService.findAndGetRateDTOById(rateId);
    }

    @GetMapping("/getRateMoviesById/{rateId}")
    @ResponseBody
    public RateMoviesDTO getRateMoviesById(@PathVariable("rateId") Long rateId) {
        return rateService.findAndGetRateMoviesDTOById(rateId);
    }

    @GetMapping("/getRateGamesById/{rateId}")
    @ResponseBody
    public RateGamesDTO getRateGamesById(@PathVariable("rateId") Long rateId) {
        return rateService.findAndGetRateGamesDTOById(rateId);
    }

    @PostMapping("/addRate")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRate(@RequestBody Rate rate) {
        rateService.saveRate(rate);
    }

    @PutMapping("/updateRate/{rateId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRate(@PathVariable("rateId") Long rateId, @RequestBody Rate rate) {
        rateService.updateRate(rateId, rate);
    }

    @DeleteMapping("/deleteRate/{rateId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRate(@PathVariable("rateId") Long rateId) {
        rateService.deleteRate(rateId);
    }

    @PutMapping("/addRateToGame/{gameId}/{rateId}")
    @ResponseStatus(HttpStatus.OK)
    public void addRateToGame(@PathVariable("gameId") Long gameId, @PathVariable("rateId") Long rateId) {
        rateService.addRateToGame(rateId, gameId);
    }

    @PutMapping("/addRateToMovie/{movieId}/{rateId}")
    @ResponseStatus(HttpStatus.OK)
    public void addRateToMovie(@PathVariable("movieId") Long movieId, @PathVariable("rateId") Long rateId) {
        rateService.addRateToMovie(rateId, movieId);
    }

    @PutMapping("/removeRateFromGame/{gameId}/{rateId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeRateFromGame(@PathVariable("gameId") Long gameId, @PathVariable("rateId") Long rateId) {
        rateService.removeRateFromGame(rateId, gameId);
    }

    @PutMapping("/removeRateFromMovie/{movieId}/{rateId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeRateFromMovie(@PathVariable("movieId") Long movieId, @PathVariable("rateId") Long rateId) {
        rateService.removeRateFromMovie(rateId, movieId);
    }
}
