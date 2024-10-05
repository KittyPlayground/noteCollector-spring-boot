package org.example.noteCollector_V2.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.noteCollector_V2.entity.SuperEntity;


import java.util.List;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "user")

public class UserEntity implements SuperEntity {
    @Id
    private String UserID;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(columnDefinition = "LONGTEXT")
    private String profilePic;
    @OneToMany(mappedBy = "user")
    private List<NoteEntity> notes;
}
