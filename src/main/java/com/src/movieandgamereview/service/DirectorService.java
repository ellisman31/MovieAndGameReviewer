package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.director.DirectorDTO;
import com.src.movieandgamereview.dto.PersonDTO;
import com.src.movieandgamereview.dto.director.DirectorGamesDTO;
import com.src.movieandgamereview.dto.director.DirectorMoviesDTO;
import com.src.movieandgamereview.dto.game.GameDTO;
import com.src.movieandgamereview.dto.movie.MovieDTO;
import com.src.movieandgamereview.model.*;
import com.src.movieandgamereview.model.game.Game;
import com.src.movieandgamereview.model.movie.Movie;
import com.src.movieandgamereview.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private InformationService informationService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private GameService gameService;

    public List<DirectorDTO> getAllDirector() {
        return ((List<Director>) directorRepository.findAll()).stream().map(this::convertToDirectorDTO).collect(Collectors.toList());
    }

    public Director findDirectorById(Long currentDirectorId) {
        Optional<Director> getDirector = directorRepository.findById(currentDirectorId);
        return getDirector.orElse(null);
    }

    public DirectorDTO findAndGetDirectorDTOById(Long currentDirectorId) {
        Optional<Director> getDirector = directorRepository.findById(currentDirectorId);
        return getDirector.map(this::convertToDirectorDTO).orElse(null);
    }

    public DirectorMoviesDTO findAndGetDirectorMoviesDTOById(Long currentDirectorId) {
        Optional<Director> getDirector = directorRepository.findById(currentDirectorId);
        return getDirector.map(this::convertDirectorMoviesToDTO).orElse(null);
    }

    public DirectorGamesDTO findAndGetDirectorGamesDTOById(Long currentDirectorId) {
        Optional<Director> getDirector = directorRepository.findById(currentDirectorId);
        return getDirector.map(this::convertDirectorGamesToDTO).orElse(null);
    }

    public void saveDirector(Director director) {
        directorRepository.save(director);
    }

    public void updateDirector(Long currentDirectorId, Director newDirectorData) {
        Director currentDirector = findDirectorById(currentDirectorId);
        if (newDirectorData.getPerson() != null) {
            currentDirector.setPerson(newDirectorData.getPerson());
        }
        saveDirector(currentDirector);
    }

    public void addDirectorToGame(Long currentDirectorId, Game game) {
        Director currentDirector = findDirectorById(currentDirectorId);
        currentDirector.getGames().add(game);
        saveDirector(currentDirector);
    }

    public void removeDirectorFromGame(Long currentDirectorId, Game game) {
        Director currentDirector = findDirectorById(currentDirectorId);
        currentDirector.getGames().remove(game);
        saveDirector(currentDirector);
    }

    public void addDirectorToMovie(Long currentDirectorId, Movie movie) {
        Director currentDirector = findDirectorById(currentDirectorId);
        currentDirector.getMovies().add(movie);
        saveDirector(currentDirector);
    }

    public void removeDirectorFromMovie(Long currentDirectorId, Movie movie) {
        Director currentDirector = findDirectorById(currentDirectorId);
        currentDirector.getMovies().remove(movie);
        saveDirector(currentDirector);
    }

    public void deleteDirector(Long currentDirectorId) {
        Director getDirector = findDirectorById(currentDirectorId);
        List<Information> informationList = informationService.findInformationByDirector(AggregateReference.to(currentDirectorId));
        informationList.forEach(information -> {
            information.setDirector(null);
            informationService.updateInformation(information.getId(), information);
        });
        personService.deletePerson(getDirector.getPerson().getId());
        directorRepository.delete(getDirector);
    }

    public Director findDirectorByPerson(AggregateReference<Person, Long> person) {
        return directorRepository.findByPerson(person).orElse(null);
    }

    protected DirectorDTO convertToDirectorDTO(Director director) {
        PersonDTO personDTO = personService.findAndGetPersonDTOById(director.getPerson().getId());
        return new DirectorDTO(personDTO);
    }

    protected DirectorMoviesDTO convertDirectorMoviesToDTO(Director director) {
        Set<MovieDTO> movies = director.getMovies()
                .stream()
                .map(movieService::convertToMovieDTO)
                .collect(Collectors.toSet());

        return new DirectorMoviesDTO(movies);
    }

    protected DirectorGamesDTO convertDirectorGamesToDTO(Director director) {
        Set<GameDTO> games = director.getGames()
                .stream()
                .map(gameService::convertToGameDTO)
                .collect(Collectors.toSet());

        return new DirectorGamesDTO(games);
    }
}
