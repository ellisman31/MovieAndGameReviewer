package com.src.movieandgamereview.model.user;

import com.src.movieandgamereview.group.UserGroups;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Data
@Table("_user_group")
public class UserGroup {
    @Id
    private Long id;
    private UserGroups name;
    private Set<User> users;

    public UserGroup(UserGroups name) {
        this.name = name;
        this.users = new HashSet<>();
    }
}
