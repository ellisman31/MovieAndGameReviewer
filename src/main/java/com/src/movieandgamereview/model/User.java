package com.src.movieandgamereview.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Review> reviews;

    public User(String firstName, String lastName, String email, String password, LocalDate birthDate, LocalDate registrationDate, AggregateReference<UserGroup, Long> _userGroup) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this._userGroup = _userGroup;
        this.reviews = new HashSet<>();
    }
}
