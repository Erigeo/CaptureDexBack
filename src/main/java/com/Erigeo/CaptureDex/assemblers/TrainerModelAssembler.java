package com.Erigeo.CaptureDex.assemblers;

import com.Erigeo.CaptureDex.controllers.TrainerController;
import com.Erigeo.CaptureDex.controllers.UserController;
import com.Erigeo.CaptureDex.models.Trainer;
import com.Erigeo.CaptureDex.services.TrainerService;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class TrainerModelAssembler implements RepresentationModelAssembler<Trainer, EntityModel<Trainer>> {

    @Override
    public EntityModel<Trainer> toModel(Trainer trainer) {

        return EntityModel.of(trainer,
                linkTo(methodOn(UserController.class).getTrainer(trainer.getId()))
                        .withSelfRel(),
                linkTo(methodOn(UserController.class).getAllTrainers(Pageable.unpaged())) // Use a default Pageable
                        .withRel("allTrainers"));

    }
}