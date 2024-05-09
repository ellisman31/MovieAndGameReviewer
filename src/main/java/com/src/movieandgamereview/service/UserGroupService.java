package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.UserDTO;
import com.src.movieandgamereview.dto.UserGroupDTO;
import com.src.movieandgamereview.model.User;
import com.src.movieandgamereview.model.UserGroup;
import com.src.movieandgamereview.repository.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserGroupService {
    @Autowired
    private UserGroupRepository userGroupRepository;
    @Autowired
    private UserService userService;

    public List<UserGroupDTO> getAllUserGroup() {
        return ((List<UserGroup>) userGroupRepository.findAll()).stream().map(this::convertToUserGroupDTO).collect(Collectors.toList());
    }

    public UserGroup findUserGroupById(Long userGroupId) {
        Optional<UserGroup> getUserGroup = userGroupRepository.findById(userGroupId);
        return getUserGroup.orElse(null);
    }

    public void saveUserGroup(UserGroup userGroup) {
        userGroupRepository.save(userGroup);
    }

    public UserGroup updateUserGroup(Long currentUserGroupId, UserGroup newUserGroupData) {
        UserGroup currentUserGroup = findUserGroupById(currentUserGroupId);
        if (newUserGroupData.getName() != null) {
            currentUserGroup.setName(newUserGroupData.getName());
        }
        if (newUserGroupData.getUsers() != null) {
            currentUserGroup.setUsers(newUserGroupData.getUsers());
        }
        saveUserGroup(currentUserGroup);
        return currentUserGroup;
    }

    public UserGroupDTO addUserToUserGroup(User user) {
        UserGroup findUserGroup = findUserGroupById(user.get_userGroup().getId());
        findUserGroup.getUsers().add(user);
        UserGroup currentUserGroup = updateUserGroup(user.get_userGroup().getId(), null);

        return convertToUserGroupDTO(currentUserGroup);
    }

    protected UserGroupDTO convertToUserGroupDTO(UserGroup userGroup) {
        Set<UserDTO> users = userGroup.getUsers()
                .stream()
                .map(userService::convertToUserDTO)
                .collect(Collectors.toSet());
        return new UserGroupDTO(userGroup.getName(), users);
    }
}
