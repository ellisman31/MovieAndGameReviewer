package com.movieandgamereview.informationservice.dto.game;

import lombok.Data;

import java.util.Set;

@Data
public class GameGenreGamesDTO {
    private Set<GameDTO> games;

    public GameGenreGamesDTO(Set<GameDTO> games) {
        this.games = games;
    }
}
