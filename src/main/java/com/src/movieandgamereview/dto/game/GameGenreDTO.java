package com.src.movieandgamereview.dto.game;

import com.src.movieandgamereview.group.GameGenres;
import lombok.Data;

import java.util.Set;

@Data
public class GameGenreDTO {
    private GameGenres name;

    public GameGenreDTO(GameGenres name) {
        this.name = name;
    }
}
