package com.example.journalapp.controller;

import com.example.journalapp.entity.User;
import com.example.journalapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/check")
    public void getAllUsers() {

    }

    @PostMapping("/create-user")
    public String createUsers(@RequestBody User user) {
        userService.saveEntry(user);
        return null;
    }
}
