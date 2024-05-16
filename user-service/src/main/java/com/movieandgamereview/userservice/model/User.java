package com.movieandgamereview.userservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import lombok.Data;

@Data
@Table("_user")
public class User {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthDate;
    private LocalDate registrationDate;
    private AggregateReference<UserGroup, Long> _userGroup;

    public User(String firstName, String lastName, String email, String password, LocalDate birthDate, AggregateReference<UserGroup, Long> _userGroup) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.registrationDate = LocalDate.now();
        this._userGroup = _userGroup;
    }
}
