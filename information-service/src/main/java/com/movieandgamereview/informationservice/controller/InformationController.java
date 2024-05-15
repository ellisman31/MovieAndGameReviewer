package com.movieandgamereview.informationservice.controller;

import com.movieandgamereview.informationservice.dto.information.InformationActorsDTO;
import com.movieandgamereview.informationservice.dto.information.InformationDTO;
import com.movieandgamereview.informationservice.dto.information.InformationReviewsDTO;
import com.movieandgamereview.informationservice.model.Information;
import com.movieandgamereview.informationservice.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InformationController {
    @Autowired
    private InformationService informationService;

    @GetMapping("/getAllInformation")
    @ResponseBody
    public List<InformationDTO> getAllInformation() {
        return informationService.getAllInformation();
    }

    @GetMapping("/getInformationById/{informationId}")
    @ResponseBody
    public InformationDTO getInformationById(@PathVariable("informationId") Long informationId) {
        return informationService.findAndGetInformationDTOById(informationId);
    }

    @GetMapping("/getInformationActorsById/{informationId}")
    @ResponseBody
    public InformationActorsDTO getInformationActors(@PathVariable("informationId") Long informationId) {
        return informationService.findAndGetInformationActorsDTOById(informationId);
    }

    @GetMapping("/getInformationReviewsById/{informationId}")
    @ResponseBody
    public InformationReviewsDTO getInformationReviews(@PathVariable("informationId") Long informationId) {
        return informationService.findAndGetInformationReviewsDTOById(informationId);
    }

    @PostMapping("/addInformation/{rateId}/{languageId}/{directorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addInformation(@PathVariable("rateId") Long rateId, @PathVariable("languageId") Long languageId,
                            @PathVariable("directorId") Long directorId, @RequestBody Information information) {
        informationService.saveNewInformation(rateId, languageId, directorId, information);
    }

    @PutMapping("/updateInformation/{informationId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateInformation(@PathVariable("informationId") Long informationId, @RequestBody Information information) {
        informationService.updateInformation(informationId, information);
    }

    @DeleteMapping("/deleteInformation/{informationId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteActor(@PathVariable("informationId") Long informationId) {
        informationService.deleteInformation(informationId);
    }

    @PutMapping("/addActorToInformation/{actorId}/{informationId}")
    @ResponseStatus(HttpStatus.OK)
    public void addActorToInformation(@PathVariable("actorId") Long actorId, @PathVariable("informationId") Long informationId) {
        informationService.addActorToInformation(informationId, actorId);
    }

    @PutMapping("/removeActorFromInformation/{actorId}/{informationId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeActorFromInformation(@PathVariable("actorId") Long actorId, @PathVariable("informationId") Long informationId) {
        informationService.removeActorFromInformation(informationId, actorId);
    }

    @PutMapping("/addReviewToGame/{reviewId}/{informationId}")
    @ResponseStatus(HttpStatus.OK)
    public void addReviewToInformation(@PathVariable("reviewId") Long reviewId, @PathVariable("informationId") Long informationId) {
        informationService.addReviewToInformation(informationId, reviewId);
    }

    @PutMapping("/removeReviewFromGame/{reviewId}/{informationId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeReviewFromInformation(@PathVariable("reviewId") Long reviewId, @PathVariable("informationId") Long informationId) {
        informationService.removeReviewFromInformation(informationId, reviewId);
    }
}
