package com.Erigeo.CaptureDex.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/users")
public class UserController {


    @PostMapping
    public ResponseEntity<?> createAdmin(){

    }

    @PostMapping
    public ResponseEntity<?> createTrainer(){

    }

    @DeleteMapping
    public ResponseEntity<?> deleteAdmin(){

    }

    @DeleteMapping
    public ResponseEntity<?> deleteTrainer(){

    }

    @PutMapping
    public ResponseEntity<?> editAdmin(){

    }

    @PutMapping
    public ResponseEntity<?> editTrainer(){

    }

    @GetMapping
    public ResponseEntity<?> getAdmin(){}

    @GetMapping
    public ResponseEntity<?> getTrainer(){}

    @GetMapping
    public ResponseEntity<?> getAllAdmins(){}

    @GetMapping
    public ResponseEntity<?> getAllTrainers(){}







}
