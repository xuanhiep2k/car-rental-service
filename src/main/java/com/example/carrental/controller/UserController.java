package com.example.carrental.controller;

import com.example.carrental.model.ResponseObject;
import com.example.carrental.model.Role;
import com.example.carrental.model.User;
import com.example.carrental.services.IRoleService;
import com.example.carrental.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/getAllUsers")
    ResponseEntity<ResponseObject> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", iUserService.findAll())
        );
    }

    @PostMapping("/addUser")
    ResponseEntity<ResponseObject> addUser(@RequestBody User user) {
        User userExisted = iUserService.findByUsername(user.getUsername().trim());
        if (userExisted != null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Add User Failed", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Add User Successfully", iUserService.addUser(user))
        );
    }
}
