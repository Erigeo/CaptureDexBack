package com.Erigeo.CaptureDex.controllers;

import com.Erigeo.CaptureDex.models.Game;
import com.Erigeo.CaptureDex.models.Pokemon;
import com.Erigeo.CaptureDex.services.TrainerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping("/{trainerId}/pokemons")
    public ResponseEntity<?> getTrainerPokemons(@PathVariable Long trainerId, Pageable pageable) {
        try {
            Page<Pokemon> pokemons = trainerService.getTrainerPokemons(trainerId, pageable);
            return ResponseEntity.ok(pokemons);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{trainerId}/games")
    public ResponseEntity<?> getTrainerGames(@PathVariable int trainerId, Pageable pageable) {
        try {
            Page<Game> games = trainerService.getTrainerGames(trainerId, pageable);
            return ResponseEntity.ok(games);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/{trainerId}/pokemons")
    public ResponseEntity<?> postTrainerPokemon(@PathVariable int trainerId, @RequestBody Pokemon pokemon) {
        try {
            Pokemon createdPokemon = trainerService.addPokemonToTrainer(trainerId, pokemon);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPokemon);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/{trainerId}/games")
    public ResponseEntity<?> postTrainerGames(@PathVariable int trainerId, @RequestBody Game game) {
        try {
            Game createdGame = trainerService.addGameToTrainer(trainerId, game);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/{trainerId}/pokemons")
    public ResponseEntity<?> patchTrainerPokemons(@PathVariable int trainerId, @RequestBody List<Pokemon> pokemons) {
        try {
            List<Pokemon> updatedPokemons = trainerService.updateTrainerPokemons(trainerId, pokemons);
            return ResponseEntity.ok(updatedPokemons);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/{trainerId}/games")
    public ResponseEntity<?> patchTrainerGames(@PathVariable int trainerId, @RequestBody List<Game> games) {
        try {
            List<Game> updatedGames = trainerService.updateTrainerGames(trainerId, games);
            return ResponseEntity.ok(updatedGames);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{trainerId}/games/{gameId}")
    public ResponseEntity<?> removeTrainerGame(@PathVariable int trainerId, @PathVariable Long gameId) {
        try {
            trainerService.removeTrainerGame(trainerId, gameId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{trainerId}/pokemons/{number}")
    public ResponseEntity<?> removeTrainerPokemon(@PathVariable int trainerId, @PathVariable int number) {
        try {
            trainerService.removeTrainerPokemon(trainerId, number);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
