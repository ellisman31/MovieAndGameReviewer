package com.movieandgamereview.informationservice.dto.user;

import com.movieandgamereview.informationservice.group.UserGroups;
import lombok.Data;

@Data
public class UserGroupDTO {
    private UserGroups name;

    public UserGroupDTO(UserGroups name) {
        this.name = name;
    }
}
