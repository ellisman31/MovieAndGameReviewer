package com.src.movieandgamereview.dto.rate;

import com.src.movieandgamereview.group.Rates;
import lombok.Data;

@Data
public class RateDTO {
    private Rates name;

    public RateDTO(Rates name) {
        this.name = name;
    }
}
