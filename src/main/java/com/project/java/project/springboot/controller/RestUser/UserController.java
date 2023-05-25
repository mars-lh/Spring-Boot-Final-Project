package com.project.java.project.springboot.controller.RestUser;

import com.project.java.project.springboot.model.user.UserDTORequest;
import com.project.java.project.springboot.model.user.UserDTOResponse;
import com.project.java.project.springboot.service.userService.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//
//    @GetMapping("/users")
//    public ResponseEntity<List<UserDTOResponse>> laodAllUsers () {
//        return ResponseEntity.ok(userService.registerUserDTO());
//    }


    @PostMapping("/new")
    public ResponseEntity<UserDTOResponse> saveUser (@RequestBody UserDTORequest userdto) {
        return ResponseEntity.ok().body(userService.registerUserDTODetails(userdto));
    }


}
