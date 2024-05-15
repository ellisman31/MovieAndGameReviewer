package com.movieandgamereview.informationservice.dto.rate;

import com.movieandgamereview.informationservice.group.Rates;
import lombok.Data;

@Data
public class RateDTO {
    private Rates name;

    public RateDTO(Rates name) {
        this.name = name;
    }
}
