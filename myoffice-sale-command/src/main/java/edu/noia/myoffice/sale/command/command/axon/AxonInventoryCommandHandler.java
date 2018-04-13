package edu.noia.myoffice.sale.command.command.axon;

import edu.noia.myoffice.sale.domain.command.article.ArticleCommand;
import edu.noia.myoffice.sale.domain.command.article.ReserveArticleCommand;
import edu.noia.myoffice.sale.domain.service.InventoryService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.CommandHandler;

/**
 * This class is a {@link ArticleCommand} listener which proxies a {@link InventoryService} instance
 * Command listener aspect provided by Axon
 * Proxy pattern applied
 */
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AxonInventoryCommandHandler implements InventoryService {

    @NonNull
    InventoryService inventoryService;

    @CommandHandler
    @Override
    public void reserve(ReserveArticleCommand command) {
        inventoryService.reserve(command);
    }
}
