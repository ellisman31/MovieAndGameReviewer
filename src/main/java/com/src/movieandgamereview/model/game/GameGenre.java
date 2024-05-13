package com.src.movieandgamereview.model.game;

import com.src.movieandgamereview.group.GameGenres;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Data
@Table("game_genre")
public class GameGenre {
    @Id
    private Long id;
    private GameGenres name;
    private Set<Game> games;

    public GameGenre(GameGenres name) {
        this.name = name;
        this.games = new HashSet<>();
    }
}
