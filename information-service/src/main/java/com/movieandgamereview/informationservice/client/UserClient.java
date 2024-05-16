package com.movieandgamereview.informationservice.client;

import com.movieandgamereview.informationservice.dto.user.UserDTO;
import com.movieandgamereview.informationservice.model.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface UserClient {

    @GetMapping("/api/getUserDTOById/{userId}")
    public UserDTO getUserDTOById(@PathVariable("userId") Long userId);

    @GetMapping("/api/getUserById/{userId}")
    public User getUserById(@PathVariable("userId") Long userId);
}
