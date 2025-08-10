package com.example.journalapp.controller;

import com.example.journalapp.entity.User;
import com.example.journalapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAll();
        if (allUsers != null && !allUsers.isEmpty())
            return new ResponseEntity<>(allUsers, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
        userService.saveAdmin(user);
    }
}
