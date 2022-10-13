package com.optumhackbright.noteApp.services;

import com.optumhackbright.noteApp.dtos.UserDto;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    //2 methods: register a user, log in a user
    //1st method: register a user
    @Transactional
    List<String> addUser(UserDto userDto);

    List<String> userLogin(UserDto userDto);
}
