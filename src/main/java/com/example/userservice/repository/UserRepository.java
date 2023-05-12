package com.example.userservice.repository;

import com.example.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.userID = :userID")
    UserEntity findByUserID(String userID);
}
