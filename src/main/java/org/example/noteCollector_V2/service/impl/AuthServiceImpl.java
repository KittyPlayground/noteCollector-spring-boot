package org.example.noteCollector_V2.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.noteCollector_V2.dao.UserDao;
import org.example.noteCollector_V2.dto.impl.UserDTO;
import org.example.noteCollector_V2.entity.impl.UserEntity;
import org.example.noteCollector_V2.exception.DataPersistException;
import org.example.noteCollector_V2.secure.JWTAuthResponse;
import org.example.noteCollector_V2.secure.SignIn;
import org.example.noteCollector_V2.service.AuthService;
import org.example.noteCollector_V2.service.JWTService;
import org.example.noteCollector_V2.util.Mapping;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserDao userDao;
    private final Mapping mapping;
    private final JWTService jwtService;
    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        return null;
    }

    @Override
    public JWTAuthResponse signUp(UserDTO userDTO) {
     //save user
        UserEntity savedUser = userDao.save(mapping.toUserEntity(userDTO));
        //Generate JWT and return it
        var generateToken = jwtService.generateToken(savedUser);
        return JWTAuthResponse.builder().token(generateToken).build();

    }

    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
        return null;
    }
}
