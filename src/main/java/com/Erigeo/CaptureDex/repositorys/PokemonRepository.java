package com.Erigeo.CaptureDex.repositorys;

import com.Erigeo.CaptureDex.models.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends MongoRepository<Pokemon, Integer> {
}
