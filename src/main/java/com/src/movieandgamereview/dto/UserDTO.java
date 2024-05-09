package com.src.movieandgamereview.dto;

import com.src.movieandgamereview.model.Review;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private LocalDate registrationDate;
    private UserGroupDTO userGroupDTO;
    private Set<ReviewDTO> reviews;

    public UserDTO(Long id, String firstName, String lastName, String email, LocalDate birthDate, LocalDate registrationDate, Set<ReviewDTO> reviews) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.reviews = reviews;
    }
}
