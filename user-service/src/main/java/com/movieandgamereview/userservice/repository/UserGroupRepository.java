package com.movieandgamereview.userservice.repository;

import com.movieandgamereview.userservice.group.UserGroups;
import com.movieandgamereview.userservice.model.UserGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {
    Optional<UserGroup> findByName(UserGroups userGroup);

}
