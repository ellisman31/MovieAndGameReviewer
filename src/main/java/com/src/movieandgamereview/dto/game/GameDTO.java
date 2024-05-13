package com.src.movieandgamereview.dto.game;

import com.src.movieandgamereview.dto.information.InformationDTO;
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
