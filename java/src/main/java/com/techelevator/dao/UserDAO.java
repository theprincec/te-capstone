package com.techelevator.dao;

import java.util.List;

import com.techelevator.userModel.User;

public interface UserDAO {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role);
}
