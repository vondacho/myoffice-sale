package edu.noia.myoffice.sale.domain.command.article;

import edu.noia.myoffice.common.domain.command.Command;
import edu.noia.myoffice.sale.domain.vo.ArticleId;

import java.util.Map;

public interface ArticleListCommand<T> extends Command {

    Map<ArticleId, T> getArticles();
}
