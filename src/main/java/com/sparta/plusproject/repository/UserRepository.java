package com.sparta.plusproject.repository;

import com.sparta.plusproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByNickname(String nickname);
}
