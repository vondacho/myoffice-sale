package edu.noia.myoffice.sale.messaging.exception;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.common.util.exception.EntityNotFoundException;
import edu.noia.myoffice.sale.domain.command.cart.CartCommand;
import edu.noia.myoffice.sale.domain.event.cart.CartNotFoundEventPayload;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;

@Slf4j
@RequiredArgsConstructor
public class CartCommandCallback implements CommandCallback<CartCommand, Object> {

    @NonNull
    EventPublisher eventPublisher;

    @Override
    public void onSuccess(CommandMessage message, Object result) {
        LOG.debug("Command executed successfully: {}", message.getCommandName());
    }

    @Override
    public void onFailure(CommandMessage<? extends CartCommand> message, Throwable cause) {
        LOG.warn("Command resulted in exception: {}, {}", message.getCommandName(), cause.getMessage());
        if (cause instanceof EntityNotFoundException) {
            eventPublisher.accept(CartNotFoundEventPayload.of(message.getPayload().getCartId()));
        }
    }
}
