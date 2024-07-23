package com.Erigeo.CaptureDex.services;

import com.Erigeo.CaptureDex.enums.Game;
import com.Erigeo.CaptureDex.models.Admin;
import com.Erigeo.CaptureDex.models.Pokemon;
import com.Erigeo.CaptureDex.models.Trainer;
import com.Erigeo.CaptureDex.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {
    public static void registerTrainer(Trainer trainer) {
    }

    public static Object patchTrainer(Long id, Trainer trainer) {
    }

    public static Object patchAdmin(Long id, Admin admin) {
    }

    public static void deleteTrainer(Long id) {
    }

    public Page<Trainer> getAllTrainers(Pageable pageable) {
    }

    public Page<User> getAllUsers(Pageable pageable) {
    }

    public Page<Pokemon> getTrainerPokemons(int trainerId, Pageable pageable) {
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
