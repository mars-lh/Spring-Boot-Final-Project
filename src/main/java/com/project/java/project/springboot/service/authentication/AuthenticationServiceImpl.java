package com.project.java.project.springboot.service.authentication;


import com.project.java.project.springboot.model.authenticationModel.AuthenticationRequest;
import com.project.java.project.springboot.model.authenticationModel.AuthenticationResponse;
import com.project.java.project.springboot.service.Token.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final TokenService tokenService;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenService = tokenService;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        final AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        try {
            LOGGER.info("Attempting to authenticate user: {}", authenticationRequest.getUsername());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            String token = tokenService.generateToken(userDetailsService.loadUserByUsername(authenticationRequest.getUsername()));
            LOGGER.info("Generated token for user: {}", authenticationRequest.getUsername());
            LOGGER.info(token);

            authenticationResponse.setToken(token);
        } catch (AuthenticationException  e) {
            LOGGER.error("Authentication failed for user: {}", authenticationRequest.getUsername(), e);

        }
        return authenticationResponse;
    }

}
