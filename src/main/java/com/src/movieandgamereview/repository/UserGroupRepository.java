package com.src.movieandgamereview.repository;

import com.src.movieandgamereview.group.UserGroups;
import com.src.movieandgamereview.model.user.UserGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {

    Optional<UserGroup> findByName(UserGroups userGroup);

}
