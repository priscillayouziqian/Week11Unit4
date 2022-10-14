package com.optumhackbright.noteApp.controllers;

import com.optumhackbright.noteApp.dtos.UserDto;
import com.optumhackbright.noteApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    //Autowire in our dependencies
    //UserService because Controllers interact with Service layer
    @Autowired
    private UserService userService;

    //and interact with PasswordEncoder
    @Autowired
    private PasswordEncoder passwordEncoder;

    //post request: register a User
    @PostMapping("/register")
    public List<String> addUser(@RequestBody UserDto userDto){
        String passHash = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(passHash);
        return userService.addUser(userDto);
    }
    //post request: log in a User
    @PostMapping("/login")
    public List<String> userLogin (@RequestBody UserDto userDto){
        return userService.userLogin(userDto);
    }
}

