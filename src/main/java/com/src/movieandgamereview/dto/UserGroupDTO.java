package com.src.movieandgamereview.dto;

import com.src.movieandgamereview.group.UserGroups;
import lombok.Data;

@Data
public class UserGroupDTO {
    private UserGroups userGroup;
    private UserDTO user;

    public UserGroupDTO(UserGroups userGroup, UserDTO user) {
        this.userGroup = userGroup;
        this.user = user;
    }
}
