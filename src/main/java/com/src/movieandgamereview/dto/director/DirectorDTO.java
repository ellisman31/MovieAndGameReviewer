package com.src.movieandgamereview.dto.director;

import com.src.movieandgamereview.dto.PersonDTO;
import lombok.Data;


@Data
public class DirectorDTO {
    private PersonDTO person;

    public DirectorDTO(PersonDTO person) {
        this.person = person;
    }
}
