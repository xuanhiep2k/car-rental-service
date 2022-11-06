package com.example.carrental.services;

import com.example.carrental.model.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();

    Role findByName(String name);
}
