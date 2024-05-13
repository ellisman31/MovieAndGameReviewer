package com.src.movieandgamereview.dto.rate;

import com.src.movieandgamereview.dto.game.GameDTO;
import lombok.Data;

import java.util.Set;

@Data
public class RateGamesDTO {
    private Set<GameDTO> games;

    public RateGamesDTO(Set<GameDTO> games) {
        this.games = games;
    }
}
