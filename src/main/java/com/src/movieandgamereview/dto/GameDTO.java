package com.src.movieandgamereview.dto;

import lombok.Data;

@Data
public class GameDTO {
    private InformationDTO information;
    private GameGenreDTO gameGenre;

    public GameDTO(InformationDTO information, GameGenreDTO gameGenre) {
        this.information = information;
        this.gameGenre = gameGenre;
    }
}
