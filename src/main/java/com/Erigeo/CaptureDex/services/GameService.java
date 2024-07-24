package com.Erigeo.CaptureDex.services;

import com.Erigeo.CaptureDex.models.Game;
import com.Erigeo.CaptureDex.repositorys.GameRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {



    private final GameRepository gameRepository;



    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Page<Game> getAllGames(Pageable pageable) {
        try {
            return gameRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all games", e);
        }
    }

    public Game getGameById(Long id) {
        try {
            return gameRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Game not found"));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving game", e);
        }
    }

    public Page<Game> getGameByConsole(String consoleName, Pageable pageable) {
        try {
            return gameRepository.findByGameConsole(consoleName, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving games by console", e);
        }
    }

    public Game createGame(Game game) {
        try {
            boolean gameExists = gameRepository.findById(game.getGameId()).isPresent();
            if (gameExists) {
                throw new RuntimeException("Game already exists");
            }
            return gameRepository.save(game);
        } catch (Exception e) {
            throw new RuntimeException("Error creating game", e);
        }
    }

    public void deleteGameById(Long id) {
        try {
            if (gameRepository.existsById(id)) {
                gameRepository.deleteById(id);
            } else {
                throw new RuntimeException("Game not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting game", e);
        }
    }

    public Game updateGameById(Long id, Game game) {
        try {
            Optional<Game> existingGame = gameRepository.findById(id);
            if (existingGame.isPresent()) {
                Game foundedGame = existingGame.get();
                foundedGame.setGameName(game.getGameName());
                foundedGame.setGameConsole(game.getGameConsole());
                foundedGame.setGameImage(game.getGameImage());
                foundedGame.setGameDescription(game.getGameDescription());
                foundedGame.setGameDate(game.getGameDate());
                return gameRepository.save(foundedGame);
            } else {
                throw new RuntimeException("Game not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating game", e);
        }
    }
}
