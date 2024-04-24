package com.src.movieandgamereview.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private String description;
    private UserDTO _user;
    private MovieDTO movie;
    private GameDTO game;

    public ReviewDTO(String description, UserDTO _user, MovieDTO movie, GameDTO game) {
        this.description = description;
        this._user = _user;
        this.movie = movie;
        this.game = game;
    }
}
