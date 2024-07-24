package com.Erigeo.CaptureDex.repositorys;

import com.Erigeo.CaptureDex.models.Pokemon;
import com.Erigeo.CaptureDex.models.Trainer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends MongoRepository<Trainer, Long> {


}
