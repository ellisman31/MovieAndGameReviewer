package com.movieandgamereview.userservice.service;

import com.movieandgamereview.userservice.dto.UserDTO;
import com.movieandgamereview.userservice.dto.UserGroupDTO;
import com.movieandgamereview.userservice.group.UserGroups;
import com.movieandgamereview.userservice.model.User;
import com.movieandgamereview.userservice.model.UserGroup;
import com.movieandgamereview.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired(required=false)
    private UserRepository userRepository;
    @Autowired(required=false)
    private UserGroupService userGroupService;

    public List<UserDTO> getAllUser() {
        return ((List<User>) userRepository.findAll()).stream().map(this::convertToUserDTO).collect(Collectors.toList());
    }

    public User findUserById(Long currentUserId) {
        Optional<User> getUser = userRepository.findById(currentUserId);
        return getUser.orElse(null);
    }

    public UserDTO findAndGetUserByIdDTO(Long currentUserId) {
        Optional<User> getUser = userRepository.findById(currentUserId);
        return getUser.map(this::convertToUserDTO).orElse(null);
    }

    public void saveUser(User user) throws Exception {
        if (isEmailExist(user.getEmail()) != null) {
            if (user.get_userGroup() == null) {
                user = setToDefaultUserGroup(user);
            }
        } else {
            throw new Exception("There is an account with that email address: " + user.getEmail());
        }
        userRepository.save(user);
    }

    public void updateUser(Long currentUserId, User newUserData) {
        User currentUser = findUserById(currentUserId);
        if (newUserData.getFirstName() != null) {
            currentUser.setFirstName(newUserData.getFirstName());
        }
        if (newUserData.getLastName() != null) {
            currentUser.setLastName(newUserData.getLastName());
        }
        if (newUserData.getEmail() != null) {
            currentUser.setEmail(newUserData.getEmail());
        }
        if (newUserData.getPassword() != null) {
            currentUser.setPassword(newUserData.getPassword());
        }
        if (newUserData.getBirthDate() != null) {
            currentUser.setBirthDate(newUserData.getBirthDate());
        }
        if (newUserData.get_userGroup() != null) {
            currentUser.set_userGroup(newUserData.get_userGroup());
        }
        userRepository.save(currentUser);
    }

    public void deleteUser(Long currentUserId) {
        User currentUser = findUserById(currentUserId);
        /*currentUser.getReviews().forEach(review -> {
            try {
                //reviewService.deleteReview(review.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        */userGroupService.removeUserFromUserGroup(currentUser.get_userGroup().getId(), currentUserId);
        userRepository.delete(currentUser);
    }

    protected UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO(user.getId(), user.getFirstName(),
                user.getLastName(), user.getEmail(),
                user.getBirthDate(), user.getRegistrationDate());

        UserGroupDTO getUserGroupDTO = userGroupService.addUserToUserGroup(user.get_userGroup().getId(), user.getId());
        userDTO.setUserGroupDTO(getUserGroupDTO);

        return userDTO;
    }

    protected User setToDefaultUserGroup(User user) {
        UserGroup defaultUserGroup = userGroupService.findUserGroupByName(UserGroups.MEMBER.name());
        AggregateReference<UserGroup, Long> userGroup = AggregateReference.to((defaultUserGroup).getId());
        user.set_userGroup(userGroup);
        return user;
    }

    private UserDTO isEmailExist(String emailAddress) {
        return getAllUser().stream()
                .filter(userDTO -> Objects.equals(userDTO.getEmail(), emailAddress))
                .findFirst()
                .orElse(null);
    }
}
