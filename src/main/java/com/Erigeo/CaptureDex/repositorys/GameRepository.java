package com.Erigeo.CaptureDex.repositorys;

import com.Erigeo.CaptureDex.models.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, Long> {
}
