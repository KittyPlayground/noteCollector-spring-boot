package org.example.noteCollector_V2.util;

import org.example.noteCollector_V2.dto.impl.NoteDTO;
import org.example.noteCollector_V2.dto.impl.UserDTO;
import org.example.noteCollector_V2.entity.impl.NoteEntity;
import org.example.noteCollector_V2.entity.impl.UserEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;
    //for user mapping
    public UserEntity toUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
    public UserDTO toUserDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }
    public List<UserDTO> asUserDTOList(List<UserEntity> userEntity) {
       // return modelMapper.map(userEntity, List.class);
        return modelMapper.map(userEntity, new TypeToken<List<UserDTO>>() {}.getType());
    }
    public NoteEntity toNoteEntity(NoteDTO noteDTO) {
        return modelMapper.map(noteDTO, NoteEntity.class);
    }
    public NoteDTO toNoteDTO(NoteEntity noteEntity) {
        return modelMapper.map(noteEntity, NoteDTO.class);
    }
    public List<NoteDTO> asNoteDTOList(List<NoteEntity> noteEntity) {
        return modelMapper.map(noteEntity, new TypeToken<List<NoteDTO>>() {}.getType());
    }

}
