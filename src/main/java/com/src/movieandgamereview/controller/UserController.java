package com.src.movieandgamereview.controller;

import com.src.movieandgamereview.dto.user.UserDTO;
import com.src.movieandgamereview.model.user.User;
import com.src.movieandgamereview.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/getAllUser")
    @ResponseBody
    public List<UserDTO> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/findUserById/{id}")
    @ResponseBody
    public UserDTO getUserById(@PathVariable("id") Long userId) {
        return userService.findAndGetUserByIdDTO(userId);
    }

    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User newUser) {
        userService.saveUser(newUser);
    }

    @PutMapping("/updateUser/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable("id") Long userId, @RequestBody User newUserData) {
        userService.updateUser(userId, newUserData);
    }

    @DeleteMapping("/deleteUser/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
    }

}
