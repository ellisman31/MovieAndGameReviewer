package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.*;
import com.src.movieandgamereview.model.Actor;
import com.src.movieandgamereview.model.Game;
import com.src.movieandgamereview.model.Movie;
import com.src.movieandgamereview.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    private GameService gameService;
    @Autowired
    private PersonService personService;

    public List<ActorDTO> getAllActor() {
        return ((List<Actor>) actorRepository.findAll()).stream().map(this::convertToActorDTO).collect(Collectors.toList());
    }

    public Actor findActorById(Long actorId) {
        Optional<Actor> getActor = actorRepository.findById(actorId);
        return getActor.orElse(null);
    }

    public ActorDTO findAndGetActorDTOById(Long actorId) {
        Optional<Actor> getReview = actorRepository.findById(actorId);
        return getReview.map(this::convertToActorDTO).orElse(null);
    }

    public void saveActor(Actor newActor) {
        actorRepository.save(newActor);
    }

    public void updateActor(Long currentActorId, Actor newActorData) {
        Actor currentActor = findActorById(currentActorId);
        if (newActorData.getPerson() != null) {
            currentActor.setPerson(newActorData.getPerson());
        }
        saveActor(currentActor);
    }

    public void addActorToGame(Long currentActorId, Game game) {
        Actor currentActor = findActorById(currentActorId);
        currentActor.getGames().add(game);
        saveActor(currentActor);
    }

    public void removeActorFromGame(Long currentActorId, Game game) {
        Actor currentActor = findActorById(currentActorId);
        currentActor.getGames().remove(game);
        saveActor(currentActor);
    }

    public void addActorToMovie(Long currentActorId, Movie movie) {
        Actor currentActor = findActorById(currentActorId);
        currentActor.getMovies().add(movie);
        saveActor(currentActor);
    }

    public void removeActorFromMovie(Long currentActorId, Movie movie) {
        Actor currentActor = findActorById(currentActorId);
        currentActor.getMovies().remove(movie);
        saveActor(currentActor);
    }

    protected ActorDTO convertToActorDTO(Actor actor) {
        PersonDTO personDTO = personService.findAndGetPersonDTOById(actor.getPerson().getId());
        Set<MovieDTO> movies = actor.getMovies()
                .stream()
                .map(movieService::convertToMovieDTO)
                .collect(Collectors.toSet());
        Set<GameDTO> games = actor.getGames()
                .stream()
                .map(gameService::convertToGameDTO)
                .collect(Collectors.toSet());

        return new ActorDTO(personDTO, movies, games);
    }
}
