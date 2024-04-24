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
    private LanguageDTO _language;
    private Set<ReviewDTO> reviews;
    private DirectorDTO director;
    private Set<ActorDTO> actors;

    public InformationDTO(String title, String description, LocalDate releaseDate, int budget, RateDTO rate, LanguageDTO _language, DirectorDTO director) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.budget = budget;
        this.rate = rate;
        this._language = _language;
        this.reviews = new HashSet<>();
        this.director = director;
        this.actors = new HashSet<>();
    }
}
