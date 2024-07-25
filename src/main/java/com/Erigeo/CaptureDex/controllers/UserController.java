package com.Erigeo.CaptureDex.controllers;

import com.Erigeo.CaptureDex.assemblers.TrainerModelAssembler;
import com.Erigeo.CaptureDex.models.Admin;
import com.Erigeo.CaptureDex.models.Trainer;
import com.Erigeo.CaptureDex.models.User;
import com.Erigeo.CaptureDex.services.AdminService;
import com.Erigeo.CaptureDex.services.TrainerService;
import com.Erigeo.CaptureDex.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AdminService adminService;
    private final TrainerService trainerService;
    private final UserService userService;
    private final PagedResourcesAssembler<Trainer> pagedResourcesAssembler;
    private final TrainerModelAssembler trainerModelAssembler;


    public UserController(AdminService adminService, TrainerService trainerService, UserService userService, TrainerModelAssembler trainerModelAssembler, PagedResourcesAssembler<Trainer> pagedResourcesAssembler) {
        this.adminService = adminService;
        this.trainerService = trainerService;
        this.userService = userService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.trainerModelAssembler = trainerModelAssembler;
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

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteAdminById(@PathVariable Long id) {
        try {
            adminService.deleteAdmin(id);
            return ResponseEntity.status(HttpStatus.GONE).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/trainer/{id}")
    public ResponseEntity<?> deleteTrainer(@PathVariable Long id) {
        try {
            trainerService.deleteTrainer(id);
            return ResponseEntity.status(HttpStatus.GONE).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<?> editAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        try {
            Admin updatedAdmin = adminService.patchAdmin(id, admin);
            return ResponseEntity.status(HttpStatus.OK).body(updatedAdmin);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/trainer/{id}")
    public ResponseEntity<?> editTrainer(@PathVariable Long id, @RequestBody Trainer trainer) {
        try {
            var updatedTrainer = trainerService.editTrainer(id, trainer);
            return ResponseEntity.status(HttpStatus.OK).body(updatedTrainer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<?> getAdmin(@PathVariable Long adminId) {
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

    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<?> getTrainer(@PathVariable Long trainerId) {
        try {
            Trainer trainer = trainerService.getTrainer(trainerId);
            if (trainer != null) {
                return ResponseEntity
                        .ok()
                        .contentType(MediaTypes.HAL_JSON)
                        .body(trainerModelAssembler.toModel(trainer));
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
            return ResponseEntity
                    .ok()
                    .contentType(MediaTypes.HAL_JSON)
                    .body(pagedResourcesAssembler.toModel(trainers, trainerModelAssembler));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(Pageable pageable) {
        try {
            Page<User> users = userService.getAllUsers(pageable);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

