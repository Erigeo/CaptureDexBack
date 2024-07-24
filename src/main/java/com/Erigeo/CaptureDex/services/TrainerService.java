package com.Erigeo.CaptureDex.services;

import com.Erigeo.CaptureDex.models.Game;
import com.Erigeo.CaptureDex.models.Pokemon;
import com.Erigeo.CaptureDex.models.Trainer;
import com.Erigeo.CaptureDex.producers.CreateUserProducer;
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
    private final CreateUserProducer createUserProducer;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository, CreateUserProducer createUserProducer) {
        this.trainerRepository = trainerRepository;
        this.createUserProducer = createUserProducer;
    }

    public void registerTrainer(Trainer trainer) {
        try {
            boolean trainerExists = trainerRepository.findById(trainer.getId()).isPresent();
            if (trainerExists) {
                throw new RuntimeException("Trainer already exists");
            }
            createUserProducer.publishMessageEmail(trainer);
            trainerRepository.save(trainer);
        } catch (Exception e) {
            throw new RuntimeException("Error registering admin", e);
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

    public Page<Game> getTrainerGames(Long trainerId, Pageable pageable) {
        try {
            Trainer trainer = trainerRepository.findById(trainerId)
                    .orElseThrow(() -> new RuntimeException("Trainer not found"));
            List<Game> games = trainer.getGameList();
            if (!games.isEmpty()) {
                int start = (int) pageable.getOffset();
                int end = Math.min((start + pageable.getPageSize()), games.size());

                List<Game> paginatedList = games.subList(start, end);
                return new PageImpl<>(paginatedList, pageable, games.size());
            } else {
                throw new RuntimeException("games not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving trainer's Games", e);
        }
    }

    public Pokemon addPokemonToTrainer(Long trainerId, Pokemon pokemon) {
        try{
            Trainer trainer = trainerRepository.findById(trainerId)
                    .orElseThrow(() -> new RuntimeException("Trainer not found"));
            boolean existsPokemon = trainer.getPokemonList().stream().anyMatch(pokemon::equals);
            if (!existsPokemon) {
                trainer.getPokemonList().add(pokemon);
                trainerRepository.save(trainer);
            }else{
                throw new RuntimeException("Pokemon already exists");
            }
           return pokemon;

        }catch (Exception e){
            throw new RuntimeException("Error adding pokemon to trainer", e);
        }
    }

    public Game addGameToTrainer(Long trainerId, Game game) {
        try{
            Trainer trainer = trainerRepository.findById(trainerId)
                    .orElseThrow(() -> new RuntimeException("Trainer not found"));
            boolean existsGame = trainer.getGameList().stream().anyMatch(game::equals);
            if (!existsGame) {
                trainer.getGameList().add(game);
                trainerRepository.save(trainer);
            }else{
                throw new RuntimeException("Game already added");
            }
            return game;

        }catch (Exception e){
            throw new RuntimeException("Error adding game to trainer", e);
        }
    }

    public List<Pokemon> updateTrainerPokemons(Long trainerId, List<Pokemon> pokemons) {
        try{
            Trainer trainer = trainerRepository.findById(trainerId)
                    .orElseThrow(() -> new RuntimeException("Trainer not found"));
                trainer.setPokemonList(pokemons);
                trainerRepository.save(trainer);

            return trainer.getPokemonList();

        }catch (Exception e){
            throw new RuntimeException("Error adding pokemons list to trainer", e);
        }
    }

    public List<Game> updateTrainerGames(Long trainerId, List<Game> games) {
        try{
            Trainer trainer = trainerRepository.findById(trainerId)
                    .orElseThrow(() -> new RuntimeException("Trainer not found"));
            trainer.setGameList(games);
            trainerRepository.save(trainer);

            return trainer.getGameList();

        }catch (Exception e){
            throw new RuntimeException("Error adding games list to trainer", e);
        }
    }

    public void removeTrainerGame(Long trainerId, Long gameId) {
        try{
            Trainer trainer = trainerRepository.findById(trainerId)
                    .orElseThrow(() -> new RuntimeException("Trainer not found"));

            boolean gameRemoved = trainer.getGameList().removeIf(game -> gameId.equals(game.getGameId()));
            if(gameRemoved){
                trainerRepository.save(trainer);
            }
            else {
                throw new RuntimeException("Game not found in trainer list");
            }
        }catch (Exception e){
            throw new RuntimeException("Error deleting game", e);
        }
    }

    public void removeTrainerPokemon(Long trainerId, int number) {
        try{
            Trainer trainer = trainerRepository.findById(trainerId)
                    .orElseThrow(() -> new RuntimeException("Trainer not found"));

            boolean PokemonRemoved = trainer.getPokemonList().removeIf(pokemon -> pokemon.getNumber() == number);
            if(PokemonRemoved){
                trainerRepository.save(trainer);
            }
            else {
                throw new RuntimeException("Pokemon not found in trainer list");
            }
        }catch (Exception e){
            throw new RuntimeException("Error deleting pokemon", e);
        }
    }

    public Trainer getTrainer(Long trainerId) {
        try{
            return trainerRepository.findById(trainerId)
                    .orElseThrow(() -> new RuntimeException("Trainer not found"));
        }catch (Exception e){
            throw new RuntimeException("Error getting trainer", e);
        }

    }
}
