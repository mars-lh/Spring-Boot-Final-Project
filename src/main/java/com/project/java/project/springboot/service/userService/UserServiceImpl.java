package com.project.java.project.springboot.service.userService;

import com.project.java.project.springboot.model.admin.AdminEntity;
import com.project.java.project.springboot.model.user.UserDTORequest;
import com.project.java.project.springboot.model.user.UserDTOResponse;
import com.project.java.project.springboot.model.user.UserEntity;
import com.project.java.project.springboot.repository.AdminRepository;
import com.project.java.project.springboot.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public UserServiceImpl(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;

    }

    @Override
    public UserDTOResponse registerUserDTO(UserDTORequest userDTO) {
        UserEntity userTosave = userDTO.toEntity();
       userRepository.save(userTosave);
       return new UserDTOResponse(userDTO);
    }

    @Override
    public UserDTOResponse registerUserDTODetails(UserDTORequest userDTO) {
        UserEntity user = userDTO.toEntityUserDetails();
        userRepository.save(user);
        return new UserDTOResponse(userDTO);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            return toUserDetails(userEntity);
        }

        Optional<AdminEntity> adminEntityOptional = adminRepository.findByUsername(username);
        if (adminEntityOptional.isPresent()) {
            AdminEntity adminEntity = adminEntityOptional.get();
            return toUserDetailsAdmin(adminEntity);
        }

        throw new UsernameNotFoundException("Username not found");
    }




    private UserDetails toUserDetails(UserEntity user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().toString())
                .build();
    }

    private UserDetails toUserDetailsAdmin(AdminEntity user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().toString())
                .build();
    }


}
