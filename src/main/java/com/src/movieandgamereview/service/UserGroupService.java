package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.user.UserGroupDTO;
import com.src.movieandgamereview.group.UserGroups;
import com.src.movieandgamereview.model.user.User;
import com.src.movieandgamereview.model.user.UserGroup;
import com.src.movieandgamereview.repository.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public UserGroup findUserGroupById(Long currentUserGroupId) {
        Optional<UserGroup> getUserGroup = userGroupRepository.findById(currentUserGroupId);
        return getUserGroup.orElse(null);
    }

    public UserGroupDTO findUserGroupDTOById(Long currentUserGroupId) {
        Optional<UserGroup> getUserGroup = userGroupRepository.findById(currentUserGroupId);
        return getUserGroup.map(this::convertToUserGroupDTO).orElse(null);
    }

    public UserGroup findUserGroupByName(UserGroups userGroup) {
        Optional<UserGroup> getUserGroup = userGroupRepository.findByName(userGroup);
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
        UserGroup currentUserGroup = updateUserGroup(user.get_userGroup().getId(), findUserGroup);

        return convertToUserGroupDTO(currentUserGroup);
    }

    public void removeUserFromUserGroup(User user) {
        UserGroup currentUserGroup = findUserGroupById(user.get_userGroup().getId());
        currentUserGroup.getUsers().remove(user);
        userGroupRepository.save(currentUserGroup);
    }

    public void deleteUserGroup(Long currentUserGroupId) {
        UserGroup currentUserGroup = findUserGroupById(currentUserGroupId);
        currentUserGroup.getUsers().forEach(user -> {
            user.set_userGroup(userService.setToDefaultUserGroup(user).get_userGroup());
            userService.saveUser(user);
        });
        userGroupRepository.delete(currentUserGroup);
    }

    protected UserGroupDTO convertToUserGroupDTO(UserGroup userGroup) {
        return new UserGroupDTO(userGroup.getName());
    }
}
