package ma.exovate.medaibe.services.interfaces;

import ma.exovate.medaibe.dtos.AdminDto;
import ma.exovate.medaibe.dtos.AdminReq;

import java.util.List;

public interface AdminService {

    AdminDto getAdminById(Long adminId);

    List<AdminDto> getAllAdmins();

    AdminDto createAdmin(AdminReq adminReq);

    AdminDto updateAdmin(Long adminId, AdminReq adminReq);

    void deleteAdmin(Long adminId);
}


