package com.movieandgamereview.informationservice.dto.game;

import com.movieandgamereview.informationservice.dto.information.InformationDTO;
import lombok.Data;

@Data
public class GameDTO {
    private InformationDTO information;

    public GameDTO(InformationDTO information) {
        this.information = information;
    }
}
