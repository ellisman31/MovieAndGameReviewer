package com.src.movieandgamereview.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class InformationDTO {
    private String title;
    private String description;
    private LocalDate releaseDate;
    private int budget;
    private RateDTO rate;
    private LanguageDTO language;
    private ReviewDTO review;
    private DirectorDTO director;
    private Set<ActorDTO> actorDTOS;

    public InformationDTO(String title, String description, LocalDate releaseDate, int budget, RateDTO rate, LanguageDTO language, ReviewDTO review, DirectorDTO director) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.budget = budget;
        this.rate = rate;
        this.language = language;
        this.review = review;
        this.director = director;
        this.actorDTOS = new HashSet<>();
    }
}
