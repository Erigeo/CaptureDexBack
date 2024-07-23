package com.Erigeo.CaptureDex.controllers;

import com.Erigeo.CaptureDex.models.Admin;
import com.Erigeo.CaptureDex.models.Trainer;
import com.Erigeo.CaptureDex.models.User;
import com.Erigeo.CaptureDex.services.AdminService;
import com.Erigeo.CaptureDex.services.TrainerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AdminService adminService;
    private final TrainerService trainerService;

    public UserController(AdminService adminService, TrainerService trainerService) {
        this.adminService = adminService;
        this.trainerService = trainerService;
    }

    @PostMapping("/createAdmin")
    public ResponseEntity<?> createAdmin(@RequestBody @Valid Admin admin) {
        try {
            adminService.registerAdmin(admin);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/createTrainer")
    public ResponseEntity<?> createTrainer(@RequestBody @Valid Trainer trainer) {
        try {
            trainerService.registerTrainer(trainer);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/admin")
    public ResponseEntity<?> deleteAdminById(@RequestParam Long id) {
        try {
            adminService.deleteAdmin(id);
            return ResponseEntity.status(HttpStatus.GONE).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/trainer")
    public ResponseEntity<?> deleteTrainer(@RequestParam Long id) {
        try {
            trainerService.deleteTrainer(id);
            return ResponseEntity.status(HttpStatus.GONE).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/admin")
    public ResponseEntity<?> editAdmin(@RequestParam Long id, @RequestBody Admin admin) {
        try {
            Admin updatedAdmin = adminService.patchAdmin(id, admin);
            return ResponseEntity.status(HttpStatus.OK).body(updatedAdmin);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/trainer")
    public ResponseEntity<?> editTrainer(@RequestParam Long id, @RequestBody Trainer trainer) {
        try {
            var updatedTrainer = trainerService.patchTrainer(id, trainer);
            return ResponseEntity.status(HttpStatus.OK).body(updatedTrainer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getAdmin(@RequestParam Long adminId) {
        try {
            var admin = adminService.getAdmin(adminId);
            if (admin != null) {
                return ResponseEntity.ok(admin);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/trainer")
    public ResponseEntity<?> getTrainer(@RequestParam Long trainerId) {
        try {
            Trainer trainer = trainerService.getTrainer(trainerId);
            if (trainer != null) {
                return ResponseEntity.ok(trainer);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trainer not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/admins")
    public ResponseEntity<?> getAllAdmins(Pageable pageable) {
        try {
            Page<Admin> admins = adminService.getAllAdmins(pageable);
            return ResponseEntity.ok(admins);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/trainers")
    public ResponseEntity<?> getAllTrainers(Pageable pageable) {
        try {
            Page<Trainer> trainers = trainerService.getAllTrainers(pageable);
            return ResponseEntity.ok(trainers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(Pageable pageable) {
        try {
            Page<User> users = trainerService.getAllUsers(pageable);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

