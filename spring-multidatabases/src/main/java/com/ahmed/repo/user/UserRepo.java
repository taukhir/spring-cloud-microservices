package com.ahmed.repo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahmed.model.user.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
