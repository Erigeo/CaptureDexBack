package com.Erigeo.CaptureDex.models;

import com.Erigeo.CaptureDex.enums.Type;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "Pokemons")
public class Pokemon {


    @Id
    private int number;

    private String name;

    private List<Type> types;

    @Field("GAMES")
    private List<Game> games;

    private String image;

}
