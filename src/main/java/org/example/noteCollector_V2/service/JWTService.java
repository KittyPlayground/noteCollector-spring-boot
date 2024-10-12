package org.example.noteCollector_V2.service;

import org.springframework.security.core.userdetails.UserDetails;



public interface JWTService {

    String extractUserName(String token);
    String generateToken(UserDetails user);
    boolean validateToken(String token,UserDetails userDetails);
    String refreshToken(String prevToken);
}
