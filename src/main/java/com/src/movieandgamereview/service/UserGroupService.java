package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.UserGroupDTO;
import com.src.movieandgamereview.model.UserGroup;
import com.src.movieandgamereview.repository.UserGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserGroupService {

    private UserGroupRepository userGroupRepository;

    public List<UserGroupDTO> getAllUserGroupDTO() {
        return ((List<UserGroup>) userGroupRepository.findAll()).stream().map(this::convertToUserGroupDTO).collect(Collectors.toList());
    }

    public UserGroup findUserGroupById(Long userGroupId) {
        Optional<UserGroup> getUserGroup = userGroupRepository.findById(userGroupId);
        return getUserGroup.orElse(null);
    }

    public void addUserGroup(UserGroup newUserGroup) {
        userGroupRepository.save(newUserGroup);
    }

    public void updateUserGroup(UserGroup getUserGroup) {
        userGroupRepository.save(getUserGroup);
    }

    protected UserGroupDTO convertToUserGroupDTO(UserGroup userGroup) {
        return new UserGroupDTO(userGroup.getName());
    }
}
