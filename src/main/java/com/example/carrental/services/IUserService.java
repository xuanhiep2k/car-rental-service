package com.example.carrental.services;

import com.example.carrental.model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    User findByUsername(String username);

    User addUser(User user);
}
