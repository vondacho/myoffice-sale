package edu.noia.myoffice.sale.domain.event.article;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.sale.domain.vo.ArticleId;

public interface ArticleEvent extends Event {

    ArticleId getArticleId();
}
