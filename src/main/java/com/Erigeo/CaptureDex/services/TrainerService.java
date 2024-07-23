package com.Erigeo.CaptureDex.services;

import com.Erigeo.CaptureDex.models.Pokemon;
import com.Erigeo.CaptureDex.models.Trainer;
import com.Erigeo.CaptureDex.repositorys.PokemonRepository;
import com.Erigeo.CaptureDex.repositorys.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {


    private final TrainerRepository trainerRepository;
    private final PokemonRepository pokemonRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository, PokemonRepository pokemonRepository) {
        this.trainerRepository = trainerRepository;
        this.pokemonRepository = pokemonRepository;
    }

    public void registerTrainer(Trainer trainer) {
        try{
            trainerRepository.save(trainer);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Trainer editTrainer(Long id, Trainer trainer) {
        try {
            Optional<Trainer> existingTrainer = trainerRepository.findById(id);
            if (existingTrainer.isPresent()) {
                Trainer foundedTrainer = existingTrainer.get();
                foundedTrainer.setName(trainer.getName());
                foundedTrainer.setEmail(trainer.getEmail());
                foundedTrainer.setPassword(trainer.getPassword());
                foundedTrainer.setGameList(trainer.getGameList());
                foundedTrainer.setPokemonList(trainer.getPokemonList());
                return trainerRepository.save(foundedTrainer);
            } else {
                throw new RuntimeException("Trainer not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating trainer", e);
        }
    }



    public void deleteTrainer(Long id) {
        try{
            Optional<Trainer> existingTrainer = trainerRepository.findById(id);
            if(existingTrainer.isPresent()) {
                trainerRepository.delete(existingTrainer.get());
            }else{
                throw new RuntimeException("Trainer not found");
            }

        }catch (Exception e){
            throw new RuntimeException("Error deleting trainer", e);
        }
    }

    public Page<Trainer> getAllTrainers(Pageable pageable) {
        try{
            Page<Trainer> existingTrainers = trainerRepository.findAll(pageable);
            if(existingTrainers.hasContent()){
                return existingTrainers;
            }else{
                throw new RuntimeException("Trainers not found");
            }
        }catch (Exception e){
            throw new RuntimeException("Error retrieving trainers", e);
        }
    }



    public Page<Pokemon> getTrainerPokemons(Long trainerId, Pageable pageable) {
        try {
            Trainer trainer = trainerRepository.findById(trainerId)
                    .orElseThrow(() -> new RuntimeException("Trainer not found"));
            List<Pokemon> pokemons = trainer.getPokemonList();
            if (!pokemons.isEmpty()) {
                int start = (int) pageable.getOffset();
                int end = Math.min((start + pageable.getPageSize()), pokemons.size());

                List<Pokemon> paginatedList = pokemons.subList(start, end);
                return new PageImpl<>(paginatedList, pageable, pokemons.size());
            } else {
                throw new RuntimeException("pokemons not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving trainer's Pokemons", e);
        }
    }

    public Page<Game> getTrainerGames(int trainerId, Pageable pageable) {
    }

    public Pokemon addPokemonToTrainer(int trainerId, Pokemon pokemon) {
    }

    public Game addGameToTrainer(int trainerId, Game game) {
    }

    public List<Pokemon> updateTrainerPokemons(int trainerId, List<Pokemon> pokemons) {
    }

    public List<Game> updateTrainerGames(int trainerId, List<Game> games) {
    }

    public void removeTrainerGame(int trainerId, Long gameId) {
    }

    public void removeTrainerPokemon(int trainerId, int number) {
    }

    public Trainer getTrainer(Long trainerId) {
    }
}
