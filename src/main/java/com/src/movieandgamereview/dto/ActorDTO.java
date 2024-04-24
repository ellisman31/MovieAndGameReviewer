package com.src.movieandgamereview.dto;

import lombok.Data;

@Data
public class ActorDTO {
    private PersonDTO person;

    public ActorDTO(PersonDTO person) {
        this.person = person;
    }
}
