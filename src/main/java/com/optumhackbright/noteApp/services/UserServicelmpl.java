package com.optumhackbright.noteApp.services;

import com.optumhackbright.noteApp.dtos.UserDto;
import com.optumhackbright.noteApp.entities.User;
import com.optumhackbright.noteApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServicelmpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //2 methods: register a user, log in a user
    //1st method: register a user
    @Override
    @Transactional
    public List<String> addUser(UserDto userDto){
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        userRepository.saveAndFlush(user);
        //front end: redirect user to login after registering
        response.add("http://localhost:8080/login.html");
        return response;
    }
    //2nd method: log in a user

    @Override
    public List<String> userLogin(UserDto userDto){
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
        if(userOptional.isPresent()){ //a method in Optionals.
            if(passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())){
                response.add("http://localhost:8080/home.html");
                response.add(String.valueOf(userOptional.get().getId()));
            }else{
                response.add("Username or password incorrect");
            }
        }else{
            response.add("Username or password incorrect");
        }
        return response;
    }
}
