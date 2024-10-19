package org.example.noteCollector_V2.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.noteCollector_V2.dto.impl.UserDTO;
import org.example.noteCollector_V2.secure.JWTAuthResponse;
import org.example.noteCollector_V2.secure.SignIn;
import org.example.noteCollector_V2.service.AuthService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        return null;
    }

    @Override
    public JWTAuthResponse signUp(UserDTO userDTO) {
        return null;
    }

    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
        return null;
    }
}
