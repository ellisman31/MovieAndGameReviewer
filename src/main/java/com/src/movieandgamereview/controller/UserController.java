package com.src.movieandgamereview.controller;

import com.src.movieandgamereview.dto.UserDTO;
import com.src.movieandgamereview.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/getAllUser")
    public List<UserDTO> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/findUserById/{id}")
    public UserDTO getUserById(@PathVariable Long userId) {
        return userService.findUserById(userId);
    }

}
