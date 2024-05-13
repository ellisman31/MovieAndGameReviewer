package com.src.movieandgamereview.dto.information;

import com.src.movieandgamereview.dto.actor.ActorDTO;
import lombok.Data;

import java.util.Set;

@Data
public class InformationActorsDTO {
    private Set<ActorDTO> actors;

    public InformationActorsDTO(Set<ActorDTO> actors) {
        this.actors = actors;
    }
}
