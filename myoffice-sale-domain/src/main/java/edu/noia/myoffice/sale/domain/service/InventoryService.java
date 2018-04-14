package edu.noia.myoffice.sale.domain.service;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.sale.domain.command.InventoryCommandHandler;
import edu.noia.myoffice.sale.domain.command.article.ReserveArticleCommand;
import edu.noia.myoffice.sale.domain.event.article.SystemReservedArticleEventPayload;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventoryService implements InventoryCommandHandler {

    @NonNull
    EventPublisher eventPublisher;

    @Override
    public void reserve(ReserveArticleCommand command) {
        eventPublisher.publish(SystemReservedArticleEventPayload.of(
                command.getCartId(), command.getArticleId(), command.getQuantity()));
    }
}
