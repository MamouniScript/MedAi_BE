package ma.exovate.medaibe.services;

import ma.exovate.medaibe.dtos.AdminDto;
import ma.exovate.medaibe.dtos.AdminReq;
import ma.exovate.medaibe.entities.Admin;
import ma.exovate.medaibe.exceptions.ResourceNotFoundException;
import ma.exovate.medaibe.repositories.AdminRepo;
import ma.exovate.medaibe.services.interfaces.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepo adminRepo;

    public AdminServiceImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public AdminDto getAdminById(Long adminId) {
        Admin admin = adminRepo.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with ID: " + adminId));
        return mapToDto(admin);
    }

    @Override
    public List<AdminDto> getAllAdmins() {
        List<Admin> admins = adminRepo.findAll();
        return admins.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdminDto createAdmin(AdminReq adminReq) {
        Admin admin = mapToEntity(adminReq);
        Admin savedAdmin = adminRepo.save(admin);
        return mapToDto(savedAdmin);
    }

    @Override
    public AdminDto updateAdmin(Long adminId, AdminReq adminReq) {
        Admin admin = adminRepo.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with ID: " + adminId));

        admin.setFirst_Name(adminReq.getFirstName());
        admin.setLast_Name(adminReq.getLastName());
        admin.setEmail(adminReq.getEmail());
        // Update password logic can be added if needed
        Admin updatedAdmin = adminRepo.save(admin);
        return mapToDto(updatedAdmin);
    }

    @Override
    public void deleteAdmin(Long adminId) {
        if (!adminRepo.existsById(adminId)) {
            throw new ResourceNotFoundException("Admin not found with ID: " + adminId);
        }
        adminRepo.deleteById(adminId);
    }

    // Utility Methods
    private AdminDto mapToDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setAdminId(admin.getAdminId());
        adminDto.setFirstName(admin.getFirst_Name());
        adminDto.setLastName(admin.getLast_Name());
        adminDto.setEmail(admin.getEmail());
        return adminDto;
    }

    private Admin mapToEntity(AdminReq adminReq) {
        Admin admin = new Admin();
        admin.setFirst_Name(adminReq.getFirstName());
        admin.setLast_Name(adminReq.getLastName());
        admin.setEmail(adminReq.getEmail());
        admin.setPassword(adminReq.getPassword()); // Make sure to securely hash the password if needed
        return admin;
    }
}
