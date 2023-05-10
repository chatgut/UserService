package com.example.userservice.controller;

import com.example.userservice.service.UserService;
import com.example.userservice.userDTO.UserDTO;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/amountOfMessages")
    public int getAmountOfMessages(@PathVariable("userId") Long userId) {
        return userService.getAmountOfMessages(userId);
    }

    @GetMapping("/{userId}/name")
    public String getName(@PathVariable("userId") Long userId) {
        return userService.getName(userId);
    }

    @GetMapping("/{userId}/imageLink")
    public String getImageLink(@PathVariable("userId") Long userId) {
        return userService.getImageLink(userId);
    }

    @GetMapping("/profile")
    public UserDTO getUserProfile(@RequestHeader("userID") Long userID) {
        System.out.println(userID);
        return userService.getUserProfile(userID);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addUserProfile(@RequestBody UserDTO userDTO, @RequestHeader("userID") Long userID) {
        userService.createUser(userDTO, userID);
    }

    // ta emot HEADER ID och lägg till information utefter det
}
