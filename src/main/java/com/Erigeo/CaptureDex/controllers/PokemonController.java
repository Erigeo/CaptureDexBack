package com.Erigeo.CaptureDex.controllers;

import com.Erigeo.CaptureDex.models.Pokemon;
import com.Erigeo.CaptureDex.services.PokemonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPokemons(Pageable pageable) {
        try {
            Page<Pokemon> pokemons = pokemonService.getAllPokemons(pageable);
            return ResponseEntity.ok(pokemons);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{number}")
    public ResponseEntity<?> getPokemon(@PathVariable int number) {
        try {
            Pokemon pokemon = pokemonService.getPokemonByNumber(number);
            if (pokemon != null) {
                return ResponseEntity.ok(pokemon);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokemon not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createPokemon(@RequestBody Pokemon pokemon) {
        try {
            Pokemon createdPokemon = pokemonService.createPokemon(pokemon);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPokemon);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{number}")
    public ResponseEntity<?> deletePokemon(@PathVariable int number) {
        try {
            boolean deleted = pokemonService.deletePokemon(number);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokemon not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/batch")
    public ResponseEntity<?> createManyPokemons(@RequestBody List<Pokemon> pokemons) {
        try {
            List<Pokemon> createdPokemons = pokemonService.createManyPokemons(pokemons);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPokemons);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping
    public ResponseEntity<?> updatePokemon(@RequestBody Pokemon pokemon) {
        try {
            Pokemon updatedPokemon = pokemonService.updatePokemon(pokemon);
            if (updatedPokemon != null) {
                return ResponseEntity.ok(updatedPokemon);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokemon not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
