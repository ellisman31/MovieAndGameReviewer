package com.src.movieandgamereview.dto.game;

import com.src.movieandgamereview.dto.information.InformationDTO;
import lombok.Data;

@Data
public class GameDTO {
    private InformationDTO information;

    public GameDTO(InformationDTO information) {
        this.information = information;
    }
}
