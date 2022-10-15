package com.optumhackbright.noteApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.optumhackbright.noteApp.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity //this class is mapped to a data source
@Table(name = "Users") //these objects want to map to what table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //what strategy we want to use to generate those ids
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference //handle other half of mitigating infinite recursion
    private Set<Note> noteSet = new HashSet<>();

    //below are getter and setter for 3 variables.
    //In order to get/set values - private, we need generate getter and setter for each field (field = variable)
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    //Constructor - a way to create this object
    //in general, includes a No Argument constructor
//    public User() {
//    }
    //and an All Argument constructor
//    public User(Long id, String username, String password) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//    }

    public User(UserDto userDto){
        if(userDto.getUsername() != null){
            this.username = userDto.getUsername();
        }
        if(userDto.getPassword() != null){
            this.password = userDto.getPassword();
        }
    }

}

