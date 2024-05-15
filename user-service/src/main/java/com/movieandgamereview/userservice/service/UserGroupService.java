package com.movieandgamereview.userservice.service;

import com.movieandgamereview.userservice.dto.UserGroupDTO;
import com.movieandgamereview.userservice.group.UserGroups;
import com.movieandgamereview.userservice.model.User;
import com.movieandgamereview.userservice.model.UserGroup;
import com.movieandgamereview.userservice.repository.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserGroupService {
    @Autowired(required=false)
    private UserGroupRepository userGroupRepository;
    @Autowired(required=false)
    private UserService userService;

    public List<UserGroupDTO> getAllUserGroup() {
        return ((List<UserGroup>) userGroupRepository.findAll()).stream().map(this::convertToUserGroupDTO).collect(Collectors.toList());
    }

    public UserGroup findUserGroupById(Long currentUserGroupId) {
        Optional<UserGroup> getUserGroup = userGroupRepository.findById(currentUserGroupId);
        return getUserGroup.orElse(null);
    }

    public UserGroupDTO findUserGroupDTOById(Long currentUserGroupId) {
        Optional<UserGroup> getUserGroup = userGroupRepository.findById(currentUserGroupId);
        return getUserGroup.map(this::convertToUserGroupDTO).orElse(null);
    }

    public UserGroup findUserGroupByName(String userGroupName) {
        UserGroups getUserGroupName = UserGroups.valueOf(userGroupName.toUpperCase());
        Optional<UserGroup> getUserGroup = userGroupRepository.findByName(getUserGroupName);
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

    public UserGroupDTO addUserToUserGroup(Long currentUserGroupId, Long currentUserId) {
        UserGroup currentUserGroup = findUserGroupById(currentUserGroupId);
        User getUser = userService.findUserById(currentUserId);
        if (getUser != null && !currentUserGroup.getUsers().contains(getUser)) {
            currentUserGroup.getUsers().add(getUser);
            UserGroup updatedUserGroup = updateUserGroup(currentUserGroupId, currentUserGroup);
            return convertToUserGroupDTO(updatedUserGroup);
        }
        return convertToUserGroupDTO(currentUserGroup);
    }

    public void removeUserFromUserGroup(Long currentUserGroupId, Long currentUserId) {
        UserGroup currentUserGroup = findUserGroupById(currentUserGroupId);
        User getUser = userService.findUserById(currentUserId);
        if (getUser != null && !currentUserGroup.getUsers().contains(getUser)) {
            currentUserGroup.getUsers().remove(getUser);
            updateUserGroup(currentUserGroupId, currentUserGroup);
        }
    }

    public void deleteUserGroup(Long currentUserGroupId) {
        UserGroup currentUserGroup = findUserGroupById(currentUserGroupId);
        currentUserGroup.getUsers().forEach(user -> {
            user.set_userGroup(userService.setToDefaultUserGroup(user).get_userGroup());
            try {
                userService.updateUser(user.getId(), user);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        userGroupRepository.delete(currentUserGroup);
    }

    protected UserGroupDTO convertToUserGroupDTO(UserGroup userGroup) {
        return new UserGroupDTO(userGroup.getName());
    }
}
