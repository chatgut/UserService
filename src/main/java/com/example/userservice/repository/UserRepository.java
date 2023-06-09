package com.example.userservice.repository;

import com.example.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.userID = :userID")
    UserEntity findByUserID(String userID);

    @Query("SELECT u.amountOfMessages FROM UserEntity u WHERE u.userID = :userID")
    Integer getAmountOfMessages(String userID);
}
