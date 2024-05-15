package com.movieandgamereview.userservice.dto;

import com.movieandgamereview.userservice.group.UserGroups;
import lombok.Data;

@Data
public class UserGroupDTO {
    private UserGroups name;

    public UserGroupDTO(UserGroups name) {
        this.name = name;
    }
}
