package com.movieandgamereview.informationservice.model;

import com.movieandgamereview.informationservice.group.Languages;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("_language")
public class Language {
    @Id
    private Long id;
    private Languages name;

    public Language(Languages name) {
        this.name = name;
    }
}
