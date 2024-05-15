package com.movieandgamereview.informationservice.dto.information;

import com.movieandgamereview.informationservice.dto.LanguageDTO;
import com.movieandgamereview.informationservice.dto.director.DirectorDTO;
import com.movieandgamereview.informationservice.dto.rate.RateDTO;
import lombok.Data;

import java.time.LocalDate;

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
