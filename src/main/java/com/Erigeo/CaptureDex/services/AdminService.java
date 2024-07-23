package com.Erigeo.CaptureDex.services;

import com.Erigeo.CaptureDex.models.Admin;
import com.Erigeo.CaptureDex.repositorys.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {


    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    public void registerAdmin(Admin admin) {
    }

    public void deleteAdmin(Long id) {
    }

    public Page<Admin> getAdmin(Long adminId) {
    }

    public Object getTrainer(Long adminId) {
    }

    public Page<Admin> getAllAdmin(Pageable pageable) {
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
                throw new RuntimeException("Trainer not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating trainer", e);
        }
    }

    public Page<Admin> getAllAdmins(Pageable pageable) {
    }
}
