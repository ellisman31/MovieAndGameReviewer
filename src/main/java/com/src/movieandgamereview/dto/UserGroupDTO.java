package com.src.movieandgamereview.dto;

import com.src.movieandgamereview.group.UserGroups;
import lombok.Data;

@Data
public class UserGroupDTO {
    private UserGroups name;

    public UserGroupDTO(UserGroups userGroup) {
        this.name = userGroup;
    }
}
