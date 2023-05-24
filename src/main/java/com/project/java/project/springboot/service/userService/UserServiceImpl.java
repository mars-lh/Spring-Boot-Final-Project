package com.project.java.project.springboot.service.userService;

import com.project.java.project.springboot.model.user.UserDTORequest;
import com.project.java.project.springboot.model.user.UserDTOResponse;
import com.project.java.project.springboot.model.user.UserEntity;
import com.project.java.project.springboot.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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


}
