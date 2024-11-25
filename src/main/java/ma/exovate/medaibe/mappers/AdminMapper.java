package ma.exovate.medaibe.mappers;

import ma.exovate.medaibe.dtos.AdminDto;
import ma.exovate.medaibe.dtos.AdminReq;
import ma.exovate.medaibe.entities.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    // Convert AdminReq to Admin entity
    public Admin toEntity(AdminReq req) {
        if (req == null) {
            return null;
        }

        Admin admin = new Admin();
        admin.setFirst_Name(req.getFirstName());
        admin.setLast_Name(req.getLastName());
        admin.setEmail(req.getEmail());
        admin.setPassword(req.getPassword());
        return admin;
    }

    // Convert Admin entity to AdminDto
    public AdminDto toDto(Admin admin) {
        if (admin == null) {
            return null;
        }

        AdminDto dto = new AdminDto();
        dto.setAdminId(admin.getAdminId());
        dto.setFirstName(admin.getFirst_Name());
        dto.setLastName(admin.getLast_Name());
        dto.setEmail(admin.getEmail());
        return dto;
    }

    // Convert AdminDto to Admin entity (useful if needed)
    public Admin toEntity(AdminDto dto) {
        if (dto == null) {
            return null;
        }

        Admin admin = new Admin();
        admin.setAdminId(dto.getAdminId());
        admin.setFirst_Name(dto.getFirstName());
        admin.setLast_Name(dto.getLastName());
        admin.setEmail(dto.getEmail());
        return admin;
    }
}

