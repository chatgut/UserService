package com.example.userservice.controller;

import com.example.userservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

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
}
