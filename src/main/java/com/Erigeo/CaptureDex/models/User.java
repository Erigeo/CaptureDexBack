package com.Erigeo.CaptureDex.models;

import com.Erigeo.CaptureDex.enums.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "Users")
public class User {

    @Id
    private Long id;

    private String name;
    private String email;
    private String password;

    private LocalDateTime createdIn;

    @Field("ROLE")
    private Role role;

}
