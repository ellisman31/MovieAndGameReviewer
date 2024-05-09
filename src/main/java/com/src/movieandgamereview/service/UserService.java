package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.ReviewDTO;
import com.src.movieandgamereview.dto.UserDTO;
import com.src.movieandgamereview.dto.UserGroupDTO;
import com.src.movieandgamereview.model.Review;
import com.src.movieandgamereview.model.User;
import com.src.movieandgamereview.model.UserGroup;
import com.src.movieandgamereview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    //TODO: REMOVE METHOD FROM DATABASE FOR ALL SERVICES.
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private ReviewService reviewService;

    public List<UserDTO> getAllUser() {
        return ((List<User>) userRepository.findAll()).stream().map(this::convertToUserDTO).collect(Collectors.toList());
    }

    public User findUserById(Long userId) {
        Optional<User> getUser = userRepository.findById(userId);
        return getUser.orElse(null);
    }

    public UserDTO findAndGetUserByIdDTO(Long userId) {
        Optional<User> getUser = userRepository.findById(userId);
        return getUser.map(this::convertToUserDTO).orElse(null);
    }

    public void saveUser(User user) {
        if (user.get_userGroup() != null) {
            UserGroup defaultUserGroup = userGroupService.findUserGroupById(1l);
            AggregateReference<UserGroup, Long> userGroup = (AggregateReference<UserGroup, Long>) defaultUserGroup;
            user.set_userGroup(userGroup);
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
        userRepository.save(currentUser);
    }

    public void addReview(Long currentUserId, Review newReview) {
        User currentUser = findUserById(currentUserId);
        currentUser.getReviews().add(newReview);
        saveUser(currentUser);
    }

    public Review updateReview(Long currentUserId, Review currentUserReview, Review newReviewData) {
        User currentUser = findUserById(currentUserId);
        currentUser.getReviews().remove(currentUserReview);
        if (newReviewData.getGame() != null) {
            currentUserReview.setGame(newReviewData.getGame());
        }
        if (newReviewData.getMovie() != null) {
            currentUserReview.setMovie(newReviewData.getMovie());
        }
        if (newReviewData.getDescription() != null) {
            currentUserReview.setDescription(newReviewData.getDescription());
        }

        addReview(currentUserId, currentUserReview);
        return currentUserReview;
    }

    public void removeReview(Long currentUserId, Review currentUserReview) {
        User currentUser = findUserById(currentUserId);
        currentUser.getReviews().remove(currentUserReview);
        saveUser(currentUser);
    }

    protected UserDTO convertToUserDTO(User user) {
        Set<ReviewDTO> reviews = user.getReviews()
                .stream()
                .map(reviewService::convertToReviewDTO)
                .collect(Collectors.toSet());

        UserDTO userDTO = new UserDTO(user.getId(), user.getFirstName(),
                user.getLastName(), user.getEmail(),
                user.getBirthDate(), user.getRegistrationDate(), reviews);

        UserGroupDTO getUserGroupDTO = userGroupService.addUserToUserGroup(user);
        userDTO.setUserGroupDTO(getUserGroupDTO);

        return userDTO;
    }
}
