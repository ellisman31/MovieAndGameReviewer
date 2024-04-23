package com.src.movieandgamereview.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private String description;
    private UserDTO user;
    private MovieDTO movie;
    private GameDTO game;

    public ReviewDTO(String description, UserDTO user, MovieDTO movie, GameDTO game) {
        this.description = description;
        this.user = user;
        this.movie = movie;
        this.game = game;
    }
}
