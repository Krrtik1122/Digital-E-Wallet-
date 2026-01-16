package com.krrtk.demo.controller;

import com.krrtk.demo.dto.UserRequest;
import com.krrtk.demo.entity.User;
import com.krrtk.demo.service.BankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final BankingService bankingService;

    //creating a user
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        bankingService.createUser(userRequest);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }


    //get All the users
    @GetMapping
    public List<User> getAllUsers() {
        return bankingService.getAllUsers();
    }
    }

