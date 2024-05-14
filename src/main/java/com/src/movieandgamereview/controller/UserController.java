package com.src.movieandgamereview.controller;

import com.src.movieandgamereview.dto.user.UserDTO;
import com.src.movieandgamereview.dto.user.UserReviewsDTO;
import com.src.movieandgamereview.model.user.User;
import com.src.movieandgamereview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAllUser")
    @ResponseBody
    public List<UserDTO> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/findUserById/{userId}")
    @ResponseBody
    public UserDTO getUserById(@PathVariable("userId") Long userId) {
        return userService.findAndGetUserByIdDTO(userId);
    }

    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User newUser) throws Exception {
        userService.saveUser(newUser);
    }

    @PutMapping("/updateUser/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable("userId") Long userId, @RequestBody User newUserData) {
        userService.updateUser(userId, newUserData);
    }

    @DeleteMapping("/deleteUser/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/getUserReviews/{userId}")
    @ResponseBody
    public UserReviewsDTO getUserReviews(@PathVariable("userId") Long userId) {
        return userService.findAndGetUserReviewsByIdDTO(userId);
    }
}
