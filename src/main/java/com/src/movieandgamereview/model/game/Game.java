package com.src.movieandgamereview.model.game;

import com.src.movieandgamereview.model.Information;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Data
@Table("game")
public class Game {
    @Id
    private Long id;
    private AggregateReference<Information, Long> information;
    private Set<GameGenre> gameGenres;

    public Game(AggregateReference<Information, Long> information) {
        this.information = information;
        this.gameGenres = new HashSet<>();
    }
}
