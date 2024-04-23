package com.src.movieandgamereview.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthDate;
    private LocalDate registrationDate;
    private UserGroupDTO userGroup;
    private Set<ReviewDTO> reviews;

    public UserDTO(String firstName, String lastName, String email, String password, LocalDate birthDate, LocalDate registrationDate, UserGroupDTO userGroup) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.userGroup = userGroup;
        this.reviews = new HashSet<>();
    }
}
