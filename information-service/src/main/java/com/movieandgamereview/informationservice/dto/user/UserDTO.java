package com.movieandgamereview.informationservice.dto.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private LocalDate registrationDate;
    private UserGroupDTO userGroupDTO;

    public UserDTO(Long id, String firstName, String lastName, String email, LocalDate birthDate, LocalDate registrationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
    }
}
