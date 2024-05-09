package com.src.movieandgamereview.dto;

import com.src.movieandgamereview.group.UserGroups;
import lombok.Data;

import java.util.Set;


@Data
public class UserGroupDTO {
    private UserGroups name;
    private Set<UserDTO> users;

    public UserGroupDTO(UserGroups name, Set<UserDTO> users) {
        this.name = name;
        this.users = users;
    }
}
