package com.Erigeo.CaptureDex.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "Trainers")
public class Trainer extends User{

    private List<Pokemon> pokemonList;
    private List<Game> gameList;

}
