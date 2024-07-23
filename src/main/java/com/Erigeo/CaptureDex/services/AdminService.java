package com.Erigeo.CaptureDex.services;

import com.Erigeo.CaptureDex.models.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    public static void registerAdmin(Admin admin) {
    }

    public static void deleteAdmin(Long id) {
    }

    public Page<Admin> getAdmin(Long adminId) {
    }

    public Object getTrainer(Long adminId) {
    }

    public Page<Admin> getAllAdmin(Pageable pageable) {
    }

    public Admin patchAdmin(Long id, Admin admin) {
    }

    public Page<Admin> getAllAdmins(Pageable pageable) {
    }
}
