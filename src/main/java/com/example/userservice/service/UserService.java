package com.example.userservice.service;

import com.example.userservice.entity.UserEntity;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.userDTO.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserService {


    public UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int getAmountOfMessages(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"))
                .getAmountOfMessages();
    }

    public String getName(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"))
                .getName();
    }

    public String getImageLink(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"))
                .getImageLink();
    }

    public UserDTO getUserProfile(Long userID) {
        UserEntity user = getUserByHeader(userID);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return new UserDTO(user.getName(), user.getImageLink());
    }

    private UserEntity getUserByHeader(Long userID) {
        System.out.println(userID);
        return userRepository.findByUserID(userID);
    }

    public void createUser(UserDTO userDTO, Long userID) {
        UserEntity user = new UserEntity();
        try {
            user.setName(userDTO.name());
            user.setImageLink(userDTO.imageLink());
            user.setUserID(userID);
            userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists or invalid input");
        }
    }

    public void updateUserProfile(UserDTO userDTO, Long userID) {
        try {
            UserEntity user = userRepository.findByUserID(userID);
            user.setName(userDTO.name());
            user.setImageLink(userDTO.imageLink());
            userRepository.save(user);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
}
