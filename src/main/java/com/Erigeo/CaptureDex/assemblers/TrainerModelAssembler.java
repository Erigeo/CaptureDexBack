package com.Erigeo.CaptureDex.assemblers;

import com.Erigeo.CaptureDex.controllers.UserController;
import com.Erigeo.CaptureDex.models.Trainer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TrainerModelAssembler implements RepresentationModelAssembler<Trainer, EntityModel<Trainer>> {

    private final PokemonModelAssembler pokemonModelAssembler;

    public TrainerModelAssembler(PokemonModelAssembler pokemonModelAssembler) {
        this.pokemonModelAssembler = pokemonModelAssembler;
    }

    @Override
    public EntityModel<Trainer> toModel(Trainer trainer) {
        // Create the Trainer EntityModel
        EntityModel<Trainer> trainerModel = EntityModel.of(trainer,
                linkTo(methodOn(UserController.class).getTrainer(trainer.getId()))
                        .withSelfRel(),
                linkTo(methodOn(UserController.class).getAllTrainers(null))
                        .withRel("allTrainers"));

        // Add links to each Pokémon in the list
        trainerModel.add(trainer.getPokemonList().stream()
                .map(pokemonModelAssembler::toModel)  // Convert each Pokemon to its EntityModel
                .flatMap(pokemonModel -> pokemonModel.getLinks().stream())  // Extract all links from each Pokemon model
                .collect(Collectors.toList()));  // Add all the links to the Trainer model

        return trainerModel;
    }
}
