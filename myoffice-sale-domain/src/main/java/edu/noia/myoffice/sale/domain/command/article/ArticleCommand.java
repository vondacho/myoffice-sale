package edu.noia.myoffice.sale.domain.command.article;

import edu.noia.myoffice.common.domain.command.Command;
import edu.noia.myoffice.sale.domain.vo.ArticleId;

public interface ArticleCommand extends Command {

    ArticleId getArticleId();
}
