package com.rental.rental.service;

import com.rental.rental.dto.AdminDTO;
import com.rental.rental.model.Admin;
import com.rental.rental.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    public AdminService( AdminRepository adminRepository ){
        this.adminRepository = adminRepository;
    }

    public AdminDTO saveAdmin(AdminDTO adminDTO){
        Admin admin = convertToEntity(adminDTO);
        Admin savedAdmin = adminRepository.save(admin);
        return convertToDTO(savedAdmin);
    }

    public AdminDTO getAdminById( int adminId ){
        return adminRepository
                .findById(adminId)
                .map(this::convertToDTO)
                .orElseThrow(()->new RuntimeException("Admin not found"));
    }

    public List<AdminDTO> getAllAdmins(){
        return adminRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteAdmin( int adminId ){
        adminRepository.deleteById(adminId);
    }

    private Admin convertToEntity(AdminDTO dto) {
        Admin admin = new Admin();
        admin.setAdminId(dto.getAdminId());
        admin.setAdminName(dto.getAdminName());
        admin.setPhoneNumber(dto.getPhoneNumber());
        return admin;
    }

    private AdminDTO convertToDTO(Admin admin) {
        AdminDTO dto = new AdminDTO();
        dto.setAdminId(admin.getAdminId());
        dto.setAdminName(admin.getAdminName());
        dto.setPhoneNumber(admin.getPhoneNumber());
        return dto;
    }


}
