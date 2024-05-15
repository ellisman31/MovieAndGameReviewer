package com.movieandgamereview.informationservice.dto.director;

import com.movieandgamereview.informationservice.dto.game.GameDTO;
import lombok.Data;

import java.util.Set;

@Data
public class DirectorGamesDTO {
    private Set<GameDTO> games;

    public DirectorGamesDTO(Set<GameDTO> games) {
        this.games = games;
    }
}
