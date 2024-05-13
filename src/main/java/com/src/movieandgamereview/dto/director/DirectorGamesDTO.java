package com.src.movieandgamereview.dto.director;

import com.src.movieandgamereview.dto.game.GameDTO;
import lombok.Data;

import java.util.Set;

@Data
public class DirectorGamesDTO {
    private Set<GameDTO> games;

    public DirectorGamesDTO(Set<GameDTO> games) {
        this.games = games;
    }
}
