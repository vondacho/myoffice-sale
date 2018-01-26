package edu.noia.myoffice.sale.command.axon.saga.inventory;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.sale.domain.command.article.ReserveArticleCommand;
import edu.noia.myoffice.sale.domain.event.article.SystemReservedArticleEvent;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventoryCommandHandler {

    @Autowired
    EventPublisher eventPublisher;

    @CommandHandler
    public void on(ReserveArticleCommand command) {
        eventPublisher.accept(SystemReservedArticleEvent.of(
                command.getCartId(), command.getArticleId(), command.getQuantity()));
    }
}
