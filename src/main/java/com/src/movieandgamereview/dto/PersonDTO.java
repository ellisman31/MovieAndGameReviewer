package com.src.movieandgamereview.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonDTO {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public PersonDTO(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }
}
