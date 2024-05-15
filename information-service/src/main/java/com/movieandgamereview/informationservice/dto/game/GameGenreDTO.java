package com.movieandgamereview.informationservice.dto.game;

import com.movieandgamereview.informationservice.group.GameGenres;
import lombok.Data;

@Data
public class GameGenreDTO {
    private GameGenres name;

    public GameGenreDTO(GameGenres name) {
        this.name = name;
    }
}
