package com.Erigeo.CaptureDex.services;

import com.Erigeo.CaptureDex.models.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GameService {


    public Page<Game> getAllGames(Pageable pageable) {
    }

    public Game getGameById(Long id) {
    }

    public Page<Game> getGameByConsole(String consoleName) {
    }

    public Game createGame(Game game) {
    }

    public void deleteGameById(Long id) {
    }

    public Game updateGameById(Long id, Game game) {
    }
}
