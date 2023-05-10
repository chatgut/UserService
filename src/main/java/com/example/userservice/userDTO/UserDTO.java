package com.example.userservice.userDTO;

public class UserDTO {

    private final String name;
    private final String imageLink;

    public UserDTO(String name, String imageLink) {
        this.name = name;
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public String getImageLink() {
        return imageLink;
    }
}

