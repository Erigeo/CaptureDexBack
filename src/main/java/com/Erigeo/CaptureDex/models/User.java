package com.Erigeo.CaptureDex.models;

import com.Erigeo.CaptureDex.enums.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private Long id;

    private String name;
    private String email;
    private String password;

    @Field("ROLE")
    private Role role;

}
