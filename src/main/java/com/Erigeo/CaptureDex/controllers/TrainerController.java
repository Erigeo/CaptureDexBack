package com.Erigeo.CaptureDex.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/trainer")
public class TrainerController {

    @GetMapping
    public ResponseEntity<?> getTrainerPokemons(){

    }

    @GetMapping
    public ResponseEntity<?> getTrainerGames(){

    }

    @PostMapping
    public ResponseEntity<?> postTrainerPokemons(){

    }

    @PostMapping
    public ResponseEntity<?> postTrainerGames(){

    }

    @PatchMapping
    public ResponseEntity<?> patchTrainerPokemons(){

    }

    @PatchMapping
    public ResponseEntity<?> patchTrainerGames(){

    }


}
