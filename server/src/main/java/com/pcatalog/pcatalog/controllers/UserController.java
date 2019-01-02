package com.pcatalog.pcatalog.controllers;

import com.pcatalog.pcatalog.dtos.UserDto;
import com.pcatalog.pcatalog.models.User;
import com.pcatalog.pcatalog.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/users")
    public List<User> listUser(){
        return userService.findAll();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(value = "/users/{id}")
    public User getOne(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }


    @PostMapping(value="/signup")
    public User saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }



}
