package com.example.userservice.service;

import com.example.userservice.entity.UserEntity;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.userDTO.UserDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    public static UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        UserService.userRepository = userRepository;
    }


    // TODO: Fix issue so that the amount of messages is updated and saved correctly
    @RabbitListener(queues = "messages")
    public void incrementMessageCount(@RequestHeader String userID) {
        UserEntity user = userRepository.findByUserID(userID);
        if (user != null) {
            Integer amountOfMessages = userRepository.getAmountOfMessages(userID);
            if (amountOfMessages != null) {
                int newAmount = amountOfMessages + 1;
                user.setAmountOfMessages(newAmount);
                userRepository.save(user);
            }
        }


    }


    public UserDTO getOwnUserProfile(String userID) {
        UserEntity user = getUserByHeader(userID);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return new UserDTO(user.getName(), user.getImageLink(), user.getUserID());
    }

    private UserEntity getUserByHeader(String userID) {
        return userRepository.findByUserID(userID);
    }

    public UserDTO getUserProfileById(String id) {
        UserEntity user = userRepository.findByUserID(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return new UserDTO(user.getName(), user.getImageLink(), user.getUserID());
    }

    public void createUser(UserDTO userDTO, String userID) {
        UserEntity user = new UserEntity();

        if (userRepository.findByUserID(userID) != null) {
            updateUserProfile(userDTO, userID);
        } else {
            user.setName(userDTO.name());
            user.setImageLink(userDTO.imageLink());
            user.setUserID(userID);
            userRepository.save(user);
        }
    }

    public void updateUserProfile(UserDTO userDTO, String userID) {

        try {
            UserEntity user = userRepository.findByUserID(userID);
            if (!user.getUserID().equals(userID)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Something went wrong");
            }
            user.setName(userDTO.name());
            user.setImageLink(userDTO.imageLink());
            userRepository.save(user);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Something went wrong");
        }
    }

    public Iterable<UserDTO> getAllUsers() {
        Iterable<UserEntity> userEntities = userRepository.findAll();
        List<UserDTO> usersAsDTO = new ArrayList<>();

        for (UserEntity userEntity : userEntities) {
            usersAsDTO.add(new UserDTO(userEntity.getName(), userEntity.getImageLink(), userEntity.getUserID()));
        }

        return usersAsDTO;


    }
}
