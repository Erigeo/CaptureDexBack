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

@RestController("/users")
public class UserController {

    private final AdminService adminService;

    private final TrainerService trainerService;

    public UserController(AdminService adminService, TrainerService trainerService) {
        this.adminService = adminService;
        this.trainerService = trainerService;
    }


    @PostMapping("/createAdmin")
    public ResponseEntity<?> createAdmin(@RequestBody @Valid Admin admin){
        AdminService.registerAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping("/createTrainer")
    public ResponseEntity<?> createTrainer(@RequestBody @Valid Trainer trainer){
        TrainerService.registerTrainer(trainer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAdminById(@RequestParam Long id){
        AdminService.deleteAdmin(id);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTrainer(@RequestParam Long id){
        TrainerService.deleteTrainer(id);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    @PutMapping
    public ResponseEntity<?> editAdmin(@RequestParam Long id, @RequestBody Admin admin){
        var updatedAdmin =  TrainerService.patchAdmin(id, admin);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAdmin);
    }

    @PutMapping
    public ResponseEntity<?> editTrainer(@RequestParam Long id, @RequestBody Trainer trainer){
        var updatedTrainer =  TrainerService.patchTrainer(id, trainer);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTrainer);
    }

    @GetMapping
    public ResponseEntity<?> getAdmin(@RequestParam Long adminId ){
        Page<Admin> admins = adminService.getAdmin(adminId);
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getTrainer(@RequestParam Long adminId){
        var admin =  adminService.getTrainer(adminId);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllAdmins( Pageable pageable){
        Page<Admin> admins =  adminService.getAllAdmin(pageable);
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllTrainers( Pageable pageable){
        Page<Trainer> trainers =  trainerService.getAllTrainers(pageable);
        return new ResponseEntity<>(trainers, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<?> getAllUsers( Pageable pageable){
        Page<User> users =  trainerService.getAllUsers(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

}
