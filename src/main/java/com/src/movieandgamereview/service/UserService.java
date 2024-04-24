package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.UserDTO;
import com.src.movieandgamereview.dto.UserGroupDTO;
import com.src.movieandgamereview.model.User;
import com.src.movieandgamereview.model.UserGroup;
import com.src.movieandgamereview.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserGroupService userGroupService;

    public List<UserDTO> getAllUser() {
        return ((List<User>) userRepository.findAll()).stream().map(this::convertToUserDTO).collect(Collectors.toList());
    }

    public UserDTO findUserById(Long userId) {
        Optional<User> getUser = userRepository.findById(userId);
        return getUser.map(this::convertToUserDTO).orElse(null);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    protected UserDTO convertToUserDTO(User user) {

        UserDTO userDTO = new UserDTO(user.getId(), user.getFirstName(),
                user.getLastName(), user.getEmail(),
                user.getBirthDate(), user.getRegistrationDate(), user.getReviews());

        UserGroup getUserGroup = userGroupService.findUserGroupById(user.get_userGroup().getId());
        getUserGroup.getUsers().add(user);

        UserGroupDTO getUserGroupDTO = userGroupService.convertToUserGroupDTO(getUserGroup);

        userDTO.setUserGroupDTO(getUserGroupDTO);
        userGroupService.updateUserGroup(getUserGroup);
        return userDTO;
    }

}
