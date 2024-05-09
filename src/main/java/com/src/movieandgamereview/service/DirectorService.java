package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.DirectorDTO;
import com.src.movieandgamereview.dto.PersonDTO;
import com.src.movieandgamereview.model.Director;
import com.src.movieandgamereview.model.Game;
import com.src.movieandgamereview.model.Movie;
import com.src.movieandgamereview.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private PersonService personService;

    public List<DirectorDTO> getAllDirector() {
        return ((List<Director>) directorRepository.findAll()).stream().map(this::convertToDirectorDTO).collect(Collectors.toList());
    }

    public Director findDirectorById(Long directorId) {
        Optional<Director> getDirector = directorRepository.findById(directorId);
        return getDirector.orElse(null);
    }

    public DirectorDTO findAndGetDirectorDTOById(Long directorId) {
        Optional<Director> getDirector = directorRepository.findById(directorId);
        return getDirector.map(this::convertToDirectorDTO).orElse(null);
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

    protected DirectorDTO convertToDirectorDTO(Director director) {
        PersonDTO personDTO = personService.findAndGetPersonDTOById(director.getPerson().getId());
        return new DirectorDTO(personDTO);
    }
}
