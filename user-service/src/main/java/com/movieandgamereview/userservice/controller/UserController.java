package com.movieandgamereview.userservice.controller;

import com.movieandgamereview.userservice.dto.UserDTO;
//import com.movieandgamereview.userservice.dto.UserReviewsDTO;
import com.movieandgamereview.userservice.model.User;
import com.movieandgamereview.userservice.service.UserService;
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

    @GetMapping("/findUserDTOById/{userId}")
    @ResponseBody
    public UserDTO getUserDTOById(@PathVariable("userId") Long userId) {
        return userService.findAndGetUserByIdDTO(userId);
    }

    @GetMapping("/findUserById/{userId}")
    @ResponseBody
    public User getUserById(@PathVariable("userId") Long userId) {
        return userService.findUserById(userId);
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

    //TODO: STORE REVIEW IDS AND RECEIVE IT.
    /*@GetMapping("/getUserReviews/{userId}")
    @ResponseBody
    public UserReviewsDTO getUserReviews(@PathVariable("userId") Long userId) {
        return userService.findAndGetUserReviewsByIdDTO(userId);
    }
     */
}
