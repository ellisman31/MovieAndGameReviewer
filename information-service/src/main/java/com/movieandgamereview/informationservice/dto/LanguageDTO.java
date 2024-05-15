package com.movieandgamereview.informationservice.dto;

import com.movieandgamereview.informationservice.group.Languages;
import lombok.Data;

@Data
public class LanguageDTO {
    private Languages name;

    public LanguageDTO(Languages name) {
        this.name = name;
    }
}
