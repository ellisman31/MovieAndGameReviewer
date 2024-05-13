package com.src.movieandgamereview.model.game;

import com.src.movieandgamereview.model.Information;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("game")
public class Game {
    @Id
    private Long id;
    private AggregateReference<Information, Long> information;
    private AggregateReference<GameGenre, Long> gameGenre;

    public Game(AggregateReference<Information, Long> information, AggregateReference<GameGenre, Long> gameGenre) {
        this.information = information;
        this.gameGenre = gameGenre;
    }
}
