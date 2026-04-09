package com.champsoft.vrms2432352.modules.order.api;

import com.champsoft.vrms2432352.modules.order.api.dto.OrderResponse;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class OrderRepresentationAssembler implements RepresentationModelAssembler<OrderResponse, EntityModel<OrderResponse>> {
    @Override
    public EntityModel<OrderResponse> toModel(OrderResponse entity) {
        return EntityModel.of(
            entity,
            linkTo(methodOn(OrderController.class).getById(entity.id())).withSelfRel(),
            linkTo(methodOn(OrderController.class).list()).withRel("collection"),
            linkTo(methodOn(OrderController.class).update(entity.id(), null)).withRel("update-status")
        );
    }

    public CollectionModel<EntityModel<OrderResponse>> toCollection(List<OrderResponse> orders) {
        return CollectionModel.of(orders.stream().map(this::toModel).toList(),
            linkTo(methodOn(OrderController.class).list()).withSelfRel(),
            linkTo(methodOn(OrderController.class).create(null)).withRel("create-order"));
    }
}
