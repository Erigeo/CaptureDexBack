package com.Erigeo.CaptureDex.services;

import com.Erigeo.CaptureDex.models.Pokemon;
import com.Erigeo.CaptureDex.repositorys.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {


    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> createManyPokemons(List<Pokemon> pokemons) {
        try{
            pokemons.stream().map(pokemonRepository::save);
        }catch (Exception e){
            throw new RuntimeException("Error creating pokemons based in list provided", e);
        }
        return pokemons;
    }

    public Pokemon updatePokemon(Pokemon pokemon) {
        try{
            Pokemon pokemonfounded = pokemonRepository.findById(pokemon.getNumber())
            .orElseThrow(() -> new RuntimeException("Pokemon not found"));

            pokemonfounded.setName(pokemon.getName());
            pokemonfounded.setGames(pokemon.getGames());
            pokemonfounded.setImage(pokemon.getImage());
            pokemonfounded.setTypes(pokemon.getTypes());
            return pokemonRepository.save(pokemonfounded);
        }catch (Exception e){
            throw new RuntimeException("Error updating pokemon based in list provided", e);
        }
    }

    public boolean deletePokemon(int number) {
        try{
            Pokemon pokemonfounded = pokemonRepository.findById(number)
                    .orElseThrow(() -> new RuntimeException("Pokemon not found"));
            pokemonRepository.delete(pokemonfounded);
            return true;
        }catch (Exception e){
            throw new RuntimeException("Error removing pokemon", e);
        }
    }

    public Pokemon createPokemon(Pokemon pokemon) {
        try{
            boolean pokemonFounded = pokemonRepository.findById(pokemon.getNumber()).isPresent();


            if(pokemonFounded){
                throw new RuntimeException("Pokemon already exists");
            }

            pokemonRepository.save(pokemon);
        }catch (Exception e){
            throw new RuntimeException("Error creating pokemon", e);
        }
        return pokemon;
    }

    public Pokemon getPokemonByNumber(int number) {
        try{
            return pokemonRepository.findById(number)
                    .orElseThrow(() -> new RuntimeException("Pokemon not found"));
        }catch ( Exception e){
            throw new RuntimeException("Error getting pokemon", e);
        }
    }

    public Page<Pokemon> getAllPokemons(Pageable pageable) {
        try{
            Page<Pokemon> pokemonfounded = pokemonRepository.findAll(pageable)
            if(pokemonfounded.isEmpty()){
                throw new RuntimeException("Pokemons not found in system");
            }
            return pokemonfounded;
        }catch ( Exception e){
            throw new RuntimeException("Error getting pokemons", e);
        }
    }
}
