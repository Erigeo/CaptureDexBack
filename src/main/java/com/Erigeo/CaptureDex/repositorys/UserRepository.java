package com.Erigeo.CaptureDex.repositorys;

import com.Erigeo.CaptureDex.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
}
