package com.src.movieandgamereview.model;

import com.src.movieandgamereview.group.UserGroups;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("_user_group")
public class UserGroup {
    @Id
    private Long id;
    private UserGroups userGroup;
    private AggregateReference<User, Long> user;

    public UserGroup(UserGroups userGroup, AggregateReference<User, Long> user) {
        this.userGroup = userGroup;
        this.user = user;
    }
}
