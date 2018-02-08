package edu.noia.myoffice.sale.command.handler;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.sale.domain.command.article.ReserveArticleCommand;
import edu.noia.myoffice.sale.domain.event.article.SystemReservedArticleEvent;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.CommandHandler;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventoryCommandHandler {

    @NonNull
    EventPublisher eventPublisher;

    @CommandHandler
    public void on(ReserveArticleCommand command) {
        eventPublisher.accept(SystemReservedArticleEvent.of(
                command.getCartId(), command.getArticleId(), command.getQuantity()));
    }
}
