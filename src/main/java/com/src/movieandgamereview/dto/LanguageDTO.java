package com.src.movieandgamereview.dto;

import lombok.Data;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

@Data
public class LanguageDTO {
    private String name;

    public LanguageDTO(String name) {
        this.name = name;
    }
}
