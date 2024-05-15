package com.movieandgamereview.informationservice.dto.information;

import com.movieandgamereview.informationservice.dto.actor.ActorDTO;
import lombok.Data;

import java.util.Set;

@Data
public class InformationActorsDTO {
    private Set<ActorDTO> actors;

    public InformationActorsDTO(Set<ActorDTO> actors) {
        this.actors = actors;
    }
}
