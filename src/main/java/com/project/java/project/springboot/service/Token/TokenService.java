package com.project.java.project.springboot.service.Token;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {

    String generateToken(UserDetails userDetails);

    Boolean validateToken(String token, UserDetails user);

    String extractUsername(String token);


}
