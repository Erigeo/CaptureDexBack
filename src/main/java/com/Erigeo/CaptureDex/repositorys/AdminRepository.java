package com.Erigeo.CaptureDex.repositorys;

import com.Erigeo.CaptureDex.models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin, Long> {
}
