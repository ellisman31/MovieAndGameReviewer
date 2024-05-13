package com.src.movieandgamereview.dto.movie;

import lombok.Data;

import java.util.Set;

@Data
public class MovieGenreDTO {
    private String name;

    public MovieGenreDTO(String name) {
        this.name = name;
    }
}
