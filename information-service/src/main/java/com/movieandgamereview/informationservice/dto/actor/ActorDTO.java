package com.movieandgamereview.informationservice.dto.actor;

import com.movieandgamereview.informationservice.dto.PersonDTO;
import lombok.Data;

@Data
public class ActorDTO {
    private PersonDTO person;

    public ActorDTO(PersonDTO person) {
        this.person = person;
    }
}
