package com.Erigeo.CaptureDex.services;

import com.Erigeo.CaptureDex.models.Admin;
import com.Erigeo.CaptureDex.repositorys.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;


@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void registerAdmin(Admin admin) {
        try {
            boolean adminExists = adminRepository.findById(admin.getId()).isPresent();
            if (adminExists) {
                throw new RuntimeException("Admin already exists");
            }
            adminRepository.save(admin);
        } catch (Exception e) {
            throw new RuntimeException("Error registering admin", e);
        }
    }

    public void deleteAdmin(Long id) {
        try {
            if (adminRepository.existsById(id)) {
                adminRepository.deleteById(id);
            } else {
                throw new RuntimeException("Admin not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting admin", e);
        }
    }


    public Page<Admin> getAdmin(Long adminId) {
        try {
            Admin admin = adminRepository.findById(adminId)
                    .orElseThrow(() -> new RuntimeException("Admin not found"));
            return new PageImpl<>(Collections.singletonList(admin));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving admin", e);
        }
    }


    public Page<Admin> getAllAdmin(Pageable pageable) {
        try {
            return adminRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all admins", e);
        }
    }

    public Admin patchAdmin(Long id, Admin admin) {
        try {
            Optional<Admin> existingAdmin = adminRepository.findById(id);
            if (existingAdmin.isPresent()) {
                Admin foundedAdmin = existingAdmin.get();
                foundedAdmin.setName(admin.getName());
                foundedAdmin.setEmail(admin.getEmail());
                foundedAdmin.setPassword(admin.getPassword());
                return adminRepository.save(foundedAdmin);
            } else {
                throw new RuntimeException("Admin not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating admin", e);
        }
    }

    public Page<Admin> getAllAdmins(Pageable pageable) {
        try {
            return adminRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all admins", e);
        }
    }
}
