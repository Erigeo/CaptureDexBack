package com.Erigeo.CaptureDex.services;

import com.Erigeo.CaptureDex.models.Admin;
import com.Erigeo.CaptureDex.models.Trainer;
import com.Erigeo.CaptureDex.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
