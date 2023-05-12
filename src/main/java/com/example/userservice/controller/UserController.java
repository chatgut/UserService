package com.example.userservice.controller;

import com.example.userservice.service.UserService;
import com.example.userservice.userDTO.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/profile")
    public UserDTO getUserProfile(@RequestHeader("userID") String userID) {
        return userService.getUserProfile(userID);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addUserProfile(@RequestBody UserDTO userDTO, @RequestHeader("userID") String userID) {
        userService.createUser(userDTO, userID);
    }

    @PutMapping("/update")
    public void updateUserProfile(@RequestBody UserDTO userDTO, @RequestHeader("userID") String userID) {
        userService.updateUserProfile(userDTO, userID);
    }

}
