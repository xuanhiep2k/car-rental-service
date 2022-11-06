package com.example.carrental.controller;

import com.example.carrental.model.ResponseObject;
import com.example.carrental.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/role")
public class RoleController {
    @Autowired
    private IRoleService iRoleService;

    @GetMapping("/getAllRoles")
    ResponseEntity<ResponseObject> getAllRoles() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", iRoleService.findAll())
        );
    }
}
