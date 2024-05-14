package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.*;
import com.src.movieandgamereview.dto.actor.ActorDTO;
import com.src.movieandgamereview.dto.actor.ActorGamesDTO;
import com.src.movieandgamereview.dto.actor.ActorMoviesDTO;
import com.src.movieandgamereview.dto.game.GameDTO;
import com.src.movieandgamereview.dto.movie.MovieDTO;
import com.src.movieandgamereview.model.*;
import com.src.movieandgamereview.model.game.Game;
import com.src.movieandgamereview.model.movie.Movie;
import com.src.movieandgamereview.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
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
    @Autowired
    private InformationService informationService;

    public List<ActorDTO> getAllActor() {
        return ((List<Actor>) actorRepository.findAll()).stream().map(this::convertToActorDTO).collect(Collectors.toList());
    }

    public Actor findActorById(Long currentActorId) {
        Optional<Actor> getActor = actorRepository.findById(currentActorId);
        return getActor.orElse(null);
    }

    public ActorDTO findAndGetActorDTOById(Long currentActorId) {
        Optional<Actor> getReview = actorRepository.findById(currentActorId);
        return getReview.map(this::convertToActorDTO).orElse(null);
    }

    public ActorMoviesDTO findAndGetActorMoviesDTOById(Long currentActorId) {
        Optional<Actor> getReview = actorRepository.findById(currentActorId);
        return getReview.map(this::convertActorMoviesToDTO).orElse(null);
    }

    public ActorGamesDTO findAndGetActorGamesDTOById(Long currentActorId) {
        Optional<Actor> getReview = actorRepository.findById(currentActorId);
        return getReview.map(this::convertActorGamesToDTO).orElse(null);
    }

    public void saveActor(Actor actor) {
        actorRepository.save(actor);
    }

    public void saveNewActor(Long personId, Actor newActor) {
        Person getPerson = personService.findPersonById(personId);
        newActor.setPerson(AggregateReference.to(getPerson.getId()));
        saveActor(newActor);
    }

    public void updateActor(Long currentActorId, Actor newActorData) {
        Actor currentActor = findActorById(currentActorId);
        if (newActorData.getPerson() != null) {
            currentActor.setPerson(newActorData.getPerson());
        }
        if (newActorData.getMovies() != null) {
            currentActor.setMovies(newActorData.getMovies());
        }
        if (newActorData.getGames() != null) {
            currentActor.setGames(newActorData.getGames());
        }
        saveActor(currentActor);
    }

    public void addActorToGame(Long currentActorId, Long currentGameId) {
        Actor currentActor = findActorById(currentActorId);
        Game getGame = gameService.findGameById(currentGameId);
        if (getGame != null && !currentActor.getGames().contains(getGame)) {
            currentActor.getGames().add(getGame);
            updateActor(currentActorId, currentActor);
        }
    }

    public void removeActorFromGame(Long currentActorId, Long currentGameId) {
        Actor currentActor = findActorById(currentActorId);
        Game getGame = gameService.findGameById(currentGameId);
        if (getGame != null && currentActor.getGames().contains(getGame)) {
            currentActor.getGames().remove(getGame);
            updateActor(currentActorId, currentActor);
        }
    }

    public void addActorToMovie(Long currentActorId, Long currentMovieId) {
        Actor currentActor = findActorById(currentActorId);
        Movie getMovie = movieService.findMovieById(currentMovieId);
        if (getMovie != null && !currentActor.getMovies().contains(getMovie)) {
            currentActor.getMovies().add(getMovie);
            updateActor(currentActorId, currentActor);
        }
    }

    public void removeActorFromMovie(Long currentActorId, Long currentMovieId) {
        Actor currentActor = findActorById(currentActorId);
        Movie getMovie = movieService.findMovieById(currentMovieId);
        if (getMovie != null && currentActor.getMovies().contains(getMovie)) {
            currentActor.getMovies().remove(getMovie);
            updateActor(currentActorId, currentActor);
        }
    }

    public void deleteActor(Long currentActorId) {
        Actor getActor = findActorById(currentActorId);
        getActor.getMovies().forEach(movie -> {
            Information information = informationService.findInformationById(movie.getInformation().getId());
            information.getActors().remove(getActor);
            informationService.updateInformation(information.getId(), information);
            movie.setInformation(AggregateReference.to(information.getId()));
            movieService.updateMovie(movie.getId(), movie);
            personService.deletePerson(getActor.getPerson().getId());
        });
        actorRepository.delete(getActor);
    }

    public Actor findActorByPerson(AggregateReference<Person, Long> person) {
        return actorRepository.findByPerson(person).orElse(null);
    }

    protected ActorDTO convertToActorDTO(Actor actor) {
        PersonDTO personDTO = personService.findAndGetPersonDTOById(actor.getPerson().getId());

        return new ActorDTO(personDTO);
    }

    protected ActorMoviesDTO convertActorMoviesToDTO(Actor actor) {
        Set<MovieDTO> movies = actor.getMovies()
                .stream()
                .map(movieService::convertToMovieDTO)
                .collect(Collectors.toSet());

        return new ActorMoviesDTO(movies);
    }

    protected ActorGamesDTO convertActorGamesToDTO(Actor actor) {
        Set<GameDTO> games = actor.getGames()
                .stream()
                .map(gameService::convertToGameDTO)
                .collect(Collectors.toSet());

        return new ActorGamesDTO(games);
    }
}
