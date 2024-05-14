package com.src.movieandgamereview.dto.actor;

import com.src.movieandgamereview.dto.PersonDTO;
import com.src.movieandgamereview.dto.game.GameDTO;
import com.src.movieandgamereview.dto.movie.MovieDTO;
import lombok.Data;

import java.util.Set;

@Data
public class ActorDTO {
    private PersonDTO person;

    public ActorDTO(PersonDTO person) {
        this.person = person;
    }
}
