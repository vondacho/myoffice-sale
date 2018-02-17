package edu.noia.myoffice.sale.domain.event.article;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.sale.domain.vo.ArticleId;

import java.util.Map;

public interface SystemFailedToReserveArticleListEventPayload extends Event {

    Map<ArticleId, Long> getArticles();
}
