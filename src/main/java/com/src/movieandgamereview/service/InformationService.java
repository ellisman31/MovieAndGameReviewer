package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.*;
import com.src.movieandgamereview.model.Actor;
import com.src.movieandgamereview.model.Information;
import com.src.movieandgamereview.model.Review;
import com.src.movieandgamereview.repository.InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<InformationDTO> getAllInformation() {
        return ((List<Information>) informationRepository.findAll()).stream().map(this::convertToInformationDTO).collect(Collectors.toList());
    }

    public Information findInformationById(Long informationId) {
        Optional<Information> getInformation = informationRepository.findById(informationId);
        return getInformation.orElse(null);
    }

    public InformationDTO findAndGetInformationDTOById(Long informationId) {
        Optional<Information> getInformation = informationRepository.findById(informationId);
        return getInformation.map(this::convertToInformationDTO).orElse(null);
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

    protected InformationDTO convertToInformationDTO(Information information) {
        RateDTO rateDTO = rateService.findAndGetRateDTOById(information.getRate().getId());
        LanguageDTO languageDTO = languageService.findAndGetLanguageDTOById(information.get_language().getId());
        DirectorDTO directorDTO = directorService.findAndGetDirectorDTOById(information.getDirector().getId());
        Set<ReviewDTO> reviews = information.getReviews()
                .stream()
                .map(reviewService::convertToReviewDTO)
                .collect(Collectors.toSet());
        Set<ActorDTO> actors = information.getActors()
                .stream()
                .map(actorService::convertToActorDTO)
                .collect(Collectors.toSet());

        return new InformationDTO(information.getTitle(), information.getDescription(), information.getReleaseDate(), information.getBudget(),
                rateDTO, languageDTO, reviews, directorDTO, actors);
    }
}
