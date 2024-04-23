package com.src.movieandgamereview.dto;

import lombok.Data;

@Data
public class RateDTO {
    private String name;

    public RateDTO(String name) {
        this.name = name;
    }
}
