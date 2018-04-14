package edu.noia.myoffice.sale.domain.command;

import edu.noia.myoffice.sale.domain.command.article.ReserveArticleCommand;

public interface InventoryCommandHandler {

    void reserve(ReserveArticleCommand command);
}