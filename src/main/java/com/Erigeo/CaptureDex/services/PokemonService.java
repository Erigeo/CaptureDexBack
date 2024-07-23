package com.Erigeo.CaptureDex.services;

import com.Erigeo.CaptureDex.models.Pokemon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {
    public List<Pokemon> createManyPokemons(List<Pokemon> pokemons) {
    }

    public Pokemon updatePokemon(Pokemon pokemon) {
    }

    public boolean deletePokemon(int number) {
    }

    public Pokemon createPokemon(Pokemon pokemon) {
    }

    public Pokemon getPokemonByNumber(int number) {
    }

    public Page<Pokemon> getAllPokemons(Pageable pageable) {
    }
}
