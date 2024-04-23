package com.src.movieandgamereview.dto;

import lombok.Data;


@Data
public class DirectorDTO {
    private PersonDTO person;

    public DirectorDTO(PersonDTO person) {
        this.person = person;
    }
}
