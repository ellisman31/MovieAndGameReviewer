package com.movieandgamereview.informationservice.dto.director;

import com.movieandgamereview.informationservice.dto.PersonDTO;
import lombok.Data;


@Data
public class DirectorDTO {
    private PersonDTO person;

    public DirectorDTO(PersonDTO person) {
        this.person = person;
    }
}
