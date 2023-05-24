package com.project.java.project.springboot.service.adminService;

import com.project.java.project.springboot.model.admin.AdminDTO;
import com.project.java.project.springboot.model.admin.AdminDTOResponse;
import com.project.java.project.springboot.model.admin.AdminEntity;
import com.project.java.project.springboot.model.roles.RolesEntity;
import com.project.java.project.springboot.repository.AdminRepository;
import com.project.java.project.springboot.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    public final AdminRepository adminRepository;
    private final RoleRepository roleRepository;

    public AdminServiceImpl(AdminRepository adminRepository, RoleRepository roleRepository) {
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
    }



    @Override
    public List<AdminDTOResponse> findAllUsers() {
        return adminRepository.findAll()
                .stream()
                .map(AdminDTOResponse::new).collect(Collectors.toList());
    }

    @Override
    public AdminEntity registerAdmin(AdminEntity admin) {
      return adminRepository.save(admin);
    }

    @Override
    public AdminDTOResponse registerAdminDTO(AdminDTO admin) {
        AdminEntity adminToSave = admin.toEntity();
        adminRepository.save(adminToSave);
        return new AdminDTOResponse(admin);

    }

    @Override
    public AdminDTOResponse registerUserDTODetails(AdminDTO admin) {
        AdminEntity adminToSave = admin.toEntityUserDetails();
        adminRepository.save(adminToSave);
        return new AdminDTOResponse(admin);
    }
}
