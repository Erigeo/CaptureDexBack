package com.Erigeo.CaptureDex.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "Games")
public class Game {
    @Id
    private Long gameId;

    private String gameName;
    private String gameDescription;
    private String gameDate;
    private String gameConsole;
    private String gameImage;

}
