package com.movieandgamereview.informationservice.dto.rate;

import com.movieandgamereview.informationservice.dto.game.GameDTO;
import lombok.Data;

import java.util.Set;

@Data
public class RateGamesDTO {
    private Set<GameDTO> games;

    public RateGamesDTO(Set<GameDTO> games) {
        this.games = games;
    }
}
