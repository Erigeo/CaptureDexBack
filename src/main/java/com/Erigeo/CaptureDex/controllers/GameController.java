package com.Erigeo.CaptureDex.controllers;

import com.Erigeo.CaptureDex.models.Game;
import com.Erigeo.CaptureDex.services.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/games")
public class GameController {

    private final GameService gameService;


    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @GetMapping
    public ResponseEntity<?> getAllGames(Pageable pageable) {
        try {
            Page<Game> games = gameService.getAllGames( pageable);
            return ResponseEntity.ok(games);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGameById(@PathVariable Long id) {
            try {
                Game game = gameService.getGameById(id);
                return ResponseEntity.ok(game);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
    }


    @GetMapping("/console")
    public ResponseEntity<?> getGamesByconsole(String consoleName, Pageable pageable) {
        try {
            Page<Game> games = gameService.getGameByConsole(consoleName);
            return ResponseEntity.ok(games);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createNewGame(@RequestBody Game game) {
        try {
            Game createdGame = gameService.createGame(game);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGameById(@PathVariable Long id) {
        try {
            gameService.deleteGameById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGameById(@PathVariable Long id, @RequestBody Game game) {
        try {
            Game updatedGame = gameService.updateGameById(id, game);
            return ResponseEntity.ok(updatedGame);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



}
