package com.src.movieandgamereview.dto.actor;

import com.src.movieandgamereview.dto.game.GameDTO;
import lombok.Data;

import java.util.Set;

@Data
public class ActorGamesDTO {
    private Set<GameDTO> games;

    public ActorGamesDTO(Set<GameDTO> games) {
        this.games = games;
    }
}
