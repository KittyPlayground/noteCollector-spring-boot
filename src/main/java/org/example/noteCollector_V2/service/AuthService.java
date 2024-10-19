package org.example.noteCollector_V2.service;

import org.example.noteCollector_V2.dto.impl.UserDTO;
import org.example.noteCollector_V2.secure.JWTAuthResponse;
import org.example.noteCollector_V2.secure.SignIn;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse signUp(UserDTO userDTO);
    JWTAuthResponse refreshToken(String accessToken);




}
