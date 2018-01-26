package edu.noia.myoffice.sale.command.rest;

import edu.noia.myoffice.common.rest.util.IdentifiantPropertyEditorSupport;
import edu.noia.myoffice.sale.domain.command.cart.CreateCartCommand;
import edu.noia.myoffice.sale.domain.command.cart.OrderCartCommand;
import edu.noia.myoffice.sale.domain.command.item.AddItemToCartCommand;
import edu.noia.myoffice.sale.domain.command.item.RemoveItemFromCartCommand;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.CartItem;
import edu.noia.myoffice.sale.domain.vo.CartItemId;
import edu.noia.myoffice.sale.domain.vo.CartSample;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/sale/v1/carts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartEndpoint {

    @NonNull
    CommandGateway commandGateway;

    @PostMapping
    public void create(@RequestBody CartSample state) {
        commandGateway.send(CreateCartCommand.of(state));
    }

    @PutMapping("/{id}/items")
    public void addItem(
            @PathVariable("id") CartId cartId,
            @RequestBody CartItem cartItem) {
        commandGateway.send(AddItemToCartCommand.of(cartId, cartItem));
    }

    @DeleteMapping("{id}/items/{itemId}")
    public void removeItem(
            @PathVariable("id") CartId cartId,
            @PathVariable("itemId") CartItemId cartItemId) {
        commandGateway.send(RemoveItemFromCartCommand.of(cartId, cartItemId));
    }

    @PutMapping("{id}/ordering")
    public void order(@PathVariable("id") CartId cartId) {
        commandGateway.send(OrderCartCommand.of(cartId));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") CartId cartId) {
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.registerCustomEditor(CartId.class,
                new IdentifiantPropertyEditorSupport<>(s-> CartId.of(UUID.fromString(s))));
    }
}
