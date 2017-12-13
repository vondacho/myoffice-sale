package edu.noia.myoffice.sale.domain.event;

import edu.noia.myoffice.common.domain.event.BaseEvent;
import edu.noia.myoffice.sale.domain.vo.ArticleId;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class ArticleReserved extends BaseEvent {
    @NonNull
    ArticleId articleId;
    @NonNull
    Long quantity;
}
