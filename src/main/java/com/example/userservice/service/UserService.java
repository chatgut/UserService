package com.example.userservice.service;

import com.example.userservice.entity.UserEntity;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.userDTO.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;


@Service
public class UserService {

    public UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDTO getOwnUserProfile(String userID) {
        UserEntity user = getUserByHeader(userID);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return new UserDTO(user.getName(), user.getImageLink());
    }

    private UserEntity getUserByHeader(String userID) {
        System.out.println(userID);
        return userRepository.findByUserID(userID);
    }

    public UserDTO getUserProfileById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return new UserDTO(user.get().getName(), user.get().getImageLink());
    }

    public void createUser(UserDTO userDTO, String userID) {
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

    public void updateUserProfile(UserDTO userDTO, String userID) {

        try {
            UserEntity user = userRepository.findByUserID(userID);
            if (!user.getUserID().equals(userID)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User ID does not match");
            }
            user.setName(userDTO.name());
            user.setImageLink(userDTO.imageLink());
            userRepository.save(user);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
}
