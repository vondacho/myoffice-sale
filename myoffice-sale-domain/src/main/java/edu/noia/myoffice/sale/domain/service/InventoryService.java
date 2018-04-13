package edu.noia.myoffice.sale.domain.service;

import edu.noia.myoffice.sale.domain.command.article.ReserveArticleCommand;

public interface InventoryService {

    void reserve(ReserveArticleCommand command);
}