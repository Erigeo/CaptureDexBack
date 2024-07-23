package com.Erigeo.CaptureDex.services;

import com.Erigeo.CaptureDex.models.User;
import com.Erigeo.CaptureDex.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Page<User> getAllUsers(Pageable pageable) {
        try {
            return userRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving users", e);
        }
    }
}
