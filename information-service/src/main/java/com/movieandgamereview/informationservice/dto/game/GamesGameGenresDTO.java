package com.movieandgamereview.informationservice.dto.game;

import lombok.Data;

import java.util.Set;

@Data
public class GamesGameGenresDTO {
    private Set<GameGenreDTO> gameGenre;

    public GamesGameGenresDTO(Set<GameGenreDTO> gameGenre) {
        this.gameGenre = gameGenre;
    }
}
