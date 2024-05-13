package com.src.movieandgamereview.dto;

import com.src.movieandgamereview.group.Languages;
import lombok.Data;

@Data
public class LanguageDTO {
    private Languages name;

    public LanguageDTO(Languages name) {
        this.name = name;
    }
}
