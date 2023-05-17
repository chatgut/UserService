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


    @GetMapping("/all")
    public Iterable<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/{userID}")
    public UserDTO getUserProfileById(@PathVariable("userID") String userID) {
        return userService.getUserProfileById(userID);
    }

    @GetMapping
    public UserDTO getOwnUserProfile(@RequestHeader("userID") String userID) {
        return userService.getOwnUserProfile(userID);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrUpdateUserProfile(@RequestBody UserDTO userDTO, @RequestHeader("userID") String userID) {
        userService.createUser(userDTO, userID);
    }

    // TODO: add endpoint for GET method to users based on a list of UserIDs

}
