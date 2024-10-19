package org.example.noteCollector_V2.controller;

import lombok.RequiredArgsConstructor;
import org.example.noteCollector_V2.dto.impl.UserDTO;
import org.example.noteCollector_V2.entity.Role;
import org.example.noteCollector_V2.exception.DataPersistException;
import org.example.noteCollector_V2.secure.JWTAuthResponse;
import org.example.noteCollector_V2.secure.SignIn;
import org.example.noteCollector_V2.service.AuthService;
import org.example.noteCollector_V2.service.UserService;
import org.example.noteCollector_V2.util.AppUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("api/v1/auth/**")
@RestController
@RequiredArgsConstructor
public class AuthUserController {

    private final UserService userService;
    private final AuthService authService;


    @PostMapping(value = "signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<JWTAuthResponse> saveUser(
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("role") String role,
            @RequestPart("profilePic") MultipartFile profilePic

    ) {
        String userId = AppUtil.generateUserId();
        String base64ProPic = "";

        try {
            byte[] profileByte = profilePic.getBytes();
            base64ProPic = AppUtil.generateProfilePictoBase64(profileByte);
            var buildUserDTO = new UserDTO();
            buildUserDTO.setUserID(userId);
            buildUserDTO.setFirstName(firstName);
            buildUserDTO.setLastName(lastName);
            buildUserDTO.setEmail(email);
            buildUserDTO.setPassword(password);
            buildUserDTO.setRole(Role.valueOf(role));
            buildUserDTO.setProfilePic(base64ProPic);
            //ToDo:change with auth user service
            authService.signUp(buildUserDTO);
            //userService.saveUser(buildUserDTO);
            return ResponseEntity.ok(authService.signUp(buildUserDTO));
           // return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "signin",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignIn signIn){

        return ResponseEntity.ok(authService.signIn(signIn));
    }
    @PostMapping("refresh")
    public ResponseEntity<JWTAuthResponse> signIn(@RequestParam ("existingToken") String existingToken){ {

        return ResponseEntity.ok(authService.refreshToken(existingToken));

    }


}
