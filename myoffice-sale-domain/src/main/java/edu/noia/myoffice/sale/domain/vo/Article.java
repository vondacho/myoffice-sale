package edu.noia.myoffice.sale.domain.vo;

import edu.noia.myoffice.common.domain.vo.Tariff;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class Article {

    @NonNull
    ArticleId articleId;
    @NonNull
    String name;
    @NonNull
    Tariff tariff;
}
