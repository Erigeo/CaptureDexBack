package com.Erigeo.CaptureDex.assemblers;

import com.Erigeo.CaptureDex.controllers.PokemonController;
import com.Erigeo.CaptureDex.models.Pokemon;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PokemonModelAssembler implements RepresentationModelAssembler<Pokemon, EntityModel<Pokemon>> {
    @Override
    public EntityModel<Pokemon> toModel(Pokemon pokemon) {
        return EntityModel.of(pokemon,
                linkTo(methodOn(PokemonController.class).getPokemon(pokemon.getNumber()))
                        .withSelfRel());
    }
}
