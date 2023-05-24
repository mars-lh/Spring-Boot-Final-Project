package com.project.java.project.springboot.service.userService;


import com.project.java.project.springboot.model.user.UserDTORequest;
import com.project.java.project.springboot.model.user.UserDTOResponse;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDTOResponse registerUserDTO (UserDTORequest userDTO);

    UserDTOResponse registerUserDTODetails (UserDTORequest userDTO);




}
