package com.src.movieandgamereview.repository;

import com.src.movieandgamereview.model.UserGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {
}
