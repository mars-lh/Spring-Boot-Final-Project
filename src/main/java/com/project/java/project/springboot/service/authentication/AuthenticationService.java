package com.project.java.project.springboot.service.authentication;

import com.project.java.project.springboot.model.authenticationModel.AuthenticationRequest;
import com.project.java.project.springboot.model.authenticationModel.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse authenticate (AuthenticationRequest authenticationRequest);
}
