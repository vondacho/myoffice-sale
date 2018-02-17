package edu.noia.myoffice.sale.domain.event.article;

import edu.noia.myoffice.common.domain.event.EventPayload;
import edu.noia.myoffice.sale.domain.vo.ArticleId;

public interface ArticleEventPayload extends EventPayload {

    ArticleId getArticleId();
}
