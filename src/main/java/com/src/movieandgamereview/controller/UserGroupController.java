package com.src.movieandgamereview.controller;

import com.src.movieandgamereview.dto.user.UserGroupDTO;
import com.src.movieandgamereview.model.user.UserGroup;
import com.src.movieandgamereview.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserGroupController {
    @Autowired
    private UserGroupService userGroupService;

    @GetMapping("/getAllUserGroup")
    @ResponseBody
    public List<UserGroupDTO> getAllUserGroup() {
        return userGroupService.getAllUserGroup();
    }

    @GetMapping("/getUserGroupById/{userGroupId}")
    @ResponseBody
    public UserGroupDTO getUserGroupById(@PathVariable("userGroupId") Long userGroupId) {
        return userGroupService.findUserGroupDTOById(userGroupId);
    }

    @PostMapping("/addUserGroup")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUserGroup(@RequestBody UserGroup userGroup) {
        userGroupService.saveUserGroup(userGroup);
    }

    @PutMapping("/updateUserGroup/{userGroupId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserGroup(@PathVariable("userGroupId") Long userGroupId, @RequestBody UserGroup userGroup) {
        userGroupService.updateUserGroup(userGroupId, userGroup);
    }

    @DeleteMapping("/deleteUserGroup/{userGroupId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserGroup(@PathVariable("userGroupId") Long userGroupId) {
        userGroupService.deleteUserGroup(userGroupId);
    }

    @PutMapping("/addUserToUserGroup/{userId}/{userGroupId}")
    @ResponseStatus(HttpStatus.OK)
    public void addUserToUserGroup(@PathVariable("userId") Long userId, @PathVariable("userGroupId") Long userGroupId) {
        userGroupService.addUserToUserGroup(userGroupId, userId);
    }

    @PutMapping("/removeUserFromUserGroup/{userId}/{userGroupId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeUserFromUserGroup(@PathVariable("userId") Long userId, @PathVariable("userGroupId") Long userGroupId) {
        userGroupService.removeUserFromUserGroup(userGroupId, userId);
    }
}
