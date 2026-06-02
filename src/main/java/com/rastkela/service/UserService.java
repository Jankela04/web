package com.rastkela.service;

import com.rastkela.dto.UserDTO;
import com.rastkela.model.User;
import com.rastkela.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO findOne(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return UserDTO.fromEntity(user);
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public UserDTO create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }

        user.setRegistrationDate(LocalDate.now());

        User savedUser = userRepository.save(user);

        return UserDTO.fromEntity(savedUser);
    }

    public UserDTO updateProfile(
            Long id,
            String username,
            String email,
            String profilePicture) {

        User user = userRepository.findById(id).orElseThrow();

        if (username != null) {
            user.setUsername(username);
        }

        if (email != null) {
            user.setEmail(email);
        }

        if (profilePicture != null) {
            user.setProfilePicture(profilePicture);
        }

        User updatedUser = userRepository.save(user);

        return UserDTO.fromEntity(updatedUser);
    }

    public UserDTO block(Long id) {
        User user = userRepository.findById(id).orElseThrow();

        user.setBlocked(true);

        return UserDTO.fromEntity(userRepository.save(user));
    }

    public UserDTO unblock(Long id) {
        User user = userRepository.findById(id).orElseThrow();

        user.setBlocked(false);

        return UserDTO.fromEntity(userRepository.save(user));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}