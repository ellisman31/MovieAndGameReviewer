package com.movieandgamereview.informationservice.dto.actor;

import com.movieandgamereview.informationservice.dto.game.GameDTO;
import lombok.Data;

import java.util.Set;

@Data
public class ActorGamesDTO {
    private Set<GameDTO> games;

    public ActorGamesDTO(Set<GameDTO> games) {
        this.games = games;
    }
}
