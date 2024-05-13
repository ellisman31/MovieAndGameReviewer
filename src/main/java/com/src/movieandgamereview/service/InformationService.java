package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.*;
import com.src.movieandgamereview.dto.actor.ActorDTO;
import com.src.movieandgamereview.dto.director.DirectorDTO;
import com.src.movieandgamereview.dto.information.InformationActorsDTO;
import com.src.movieandgamereview.dto.information.InformationDTO;
import com.src.movieandgamereview.dto.information.InformationReviewsDTO;
import com.src.movieandgamereview.dto.rate.RateDTO;
import com.src.movieandgamereview.model.*;
import com.src.movieandgamereview.model.game.Game;
import com.src.movieandgamereview.model.movie.Movie;
import com.src.movieandgamereview.repository.InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InformationService {

    @Autowired
    private InformationRepository informationRepository;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private RateService rateService;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private DirectorService directorService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private GameService gameService;

    public List<InformationDTO> getAllInformation() {
        return ((List<Information>) informationRepository.findAll()).stream().map(this::convertToInformationDTO).collect(Collectors.toList());
    }

    public Information findInformationById(Long currentInformationId) {
        Optional<Information> getInformation = informationRepository.findById(currentInformationId);
        return getInformation.orElse(null);
    }

    public InformationDTO findAndGetInformationDTOById(Long currentInformationId) {
        Optional<Information> getInformation = informationRepository.findById(currentInformationId);
        return getInformation.map(this::convertToInformationDTO).orElse(null);
    }

    public InformationActorsDTO findAndGetInformationActorsDTOById(Long currentInformationId) {
        Optional<Information> getInformation = informationRepository.findById(currentInformationId);
        return getInformation.map(this::convertInformationActorsToDTO).orElse(null);
    }

    public InformationReviewsDTO findAndGetInformationReviewsDTOById(Long currentInformationId) {
        Optional<Information> getInformation = informationRepository.findById(currentInformationId);
        return getInformation.map(this::convertInformationReviewsToDTO).orElse(null);
    }

    public void saveInformation(Information information) {
        informationRepository.save(information);
    }

    public void updateInformation(Long currentInformationId, Information newInformationData) {
        Information currentInformation = findInformationById(currentInformationId);
        if (newInformationData.getTitle() != null) {
            currentInformation.setTitle(newInformationData.getTitle());
        }
        if (newInformationData.getDescription() != null) {
            currentInformation.setDescription(currentInformation.getDescription());
        }
        if (newInformationData.getReleaseDate() != null) {
            currentInformation.setReleaseDate(newInformationData.getReleaseDate());
        }
        if (newInformationData.getBudget() > 0) {
            currentInformation.setBudget(newInformationData.getBudget());
        }
        if (newInformationData.getRate() != null) {
            currentInformation.setRate(newInformationData.getRate());
        }
        if (newInformationData.get_language() != null) {
            currentInformation.set_language(newInformationData.get_language());
        }
        if (newInformationData.getDirector() != null) {
            currentInformation.setDirector(newInformationData.getDirector());
        }
        if (newInformationData.getActors() != null) {
            currentInformation.setActors(newInformationData.getActors());
        }
        if (newInformationData.getReviews() != null) {
            currentInformation.setReviews(newInformationData.getReviews());
        }
        saveInformation(currentInformation);
    }

    public void addActorToInformation(Long currentInformationId, Actor actor) {
        Information currentInformation = findInformationById(currentInformationId);
        currentInformation.getActors().add(actor);
        saveInformation(currentInformation);
    }

    public void removeActorFromInformation(Long currentInformationId, Actor actor) {
        Information currentInformation = findInformationById(currentInformationId);
        currentInformation.getActors().remove(actor);
        saveInformation(currentInformation);
    }

    public void addReviewToInformation(Long currentInformationId, Review review) {
        Information currentInformation = findInformationById(currentInformationId);
        currentInformation.getReviews().add(review);
        saveInformation(currentInformation);
    }

    public void removeReviewFromInformation(Long currentInformationId, Review review) {
        Information currentInformation = findInformationById(currentInformationId);
        currentInformation.getReviews().remove(review);
        saveInformation(currentInformation);
    }

    public void deleteInformation(Long currentInformationId) {
        Information getInformation = findInformationById(currentInformationId);
        Movie movie = movieService.findMovieByInformation(AggregateReference.to(currentInformationId));
        Game game = gameService.findGameByInformation(AggregateReference.to(currentInformationId));
        movieService.deleteMovie(movie.getId());
        gameService.deleteGame(game.getId());
        informationRepository.delete(getInformation);
    }

    public List<Information> findInformationByRate(AggregateReference<Rate, Long> rate) {
        return informationRepository.findByRate(rate);
    }

    public List<Information> findInformationByLanguage(AggregateReference<Language, Long> language) {
        return informationRepository.findBy_Language(language);
    }

    public List<Information> findInformationByDirector(AggregateReference<Director, Long> director) {
        return informationRepository.findByDirector(director);
    }

    public Information findInformationByReview(Review review) {
        List<Information> information = (List<Information>) informationRepository.findAll();
        return (Information) information
                .stream()
                .filter(value -> value.getReviews().contains(review));
    }

    protected InformationDTO convertToInformationDTO(Information information) {
        RateDTO rateDTO = rateService.findAndGetRateDTOById(information.getRate().getId());
        LanguageDTO languageDTO = languageService.findAndGetLanguageDTOById(information.get_language().getId());
        DirectorDTO directorDTO = directorService.findAndGetDirectorDTOById(information.getDirector().getId());

        return new InformationDTO(information.getTitle(), information.getDescription(), information.getReleaseDate(), information.getBudget(),
                rateDTO, languageDTO, directorDTO);
    }

    protected InformationActorsDTO convertInformationActorsToDTO(Information information) {
        Set<ActorDTO> actors = information.getActors()
                .stream()
                .map(actorService::convertToActorDTO)
                .collect(Collectors.toSet());

        return new InformationActorsDTO(actors);
    }

    protected InformationReviewsDTO convertInformationReviewsToDTO(Information information) {
        Set<ReviewDTO> reviews = information.getReviews()
                .stream()
                .map(reviewService::convertToReviewDTO)
                .collect(Collectors.toSet());

        return new InformationReviewsDTO(reviews);
    }
}
