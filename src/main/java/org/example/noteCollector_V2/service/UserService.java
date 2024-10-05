package org.example.noteCollector_V2.service;


import org.example.noteCollector_V2.dto.UserStatus;
import org.example.noteCollector_V2.dto.impl.UserDTO;

import java.util.List;

public interface UserService {

    void saveUser(UserDTO userDTO);

    List<UserDTO> getAllUser();

    void deleteUser(String userId);

    void updateUser(String userId, UserDTO userDTO);

   UserStatus getUser(String userId);
}
