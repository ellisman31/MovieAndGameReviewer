package com.src.movieandgamereview.dto.information;

import com.src.movieandgamereview.dto.LanguageDTO;
import com.src.movieandgamereview.dto.rate.RateDTO;
import com.src.movieandgamereview.dto.ReviewDTO;
import com.src.movieandgamereview.dto.actor.ActorDTO;
import com.src.movieandgamereview.dto.director.DirectorDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class InformationDTO {
    private String title;
    private String description;
    private LocalDate releaseDate;
    private int budget;
    private RateDTO rate;
    private LanguageDTO _language;
    private DirectorDTO director;

    public InformationDTO(String title, String description, LocalDate releaseDate, int budget, RateDTO rate, LanguageDTO _language, DirectorDTO director) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.budget = budget;
        this.rate = rate;
        this._language = _language;
        this.director = director;
    }
}
