package com.movieandgamereview.informationservice.dto;

import com.movieandgamereview.informationservice.dto.game.GameDTO;
import com.movieandgamereview.informationservice.dto.movie.MovieDTO;
//import com.movieandgamereview.informationservice.dto.user.UserDTO;
import lombok.Data;

@Data
public class ReviewDTO {
    private String description;
    //private UserDTO _user;
    private MovieDTO movie;
    private GameDTO game;

    //UserDTO _user
    public ReviewDTO(String description, MovieDTO movie, GameDTO game) {
        this.description = description;
        //this._user = _user;
        this.movie = movie;
        this.game = game;
    }
}
