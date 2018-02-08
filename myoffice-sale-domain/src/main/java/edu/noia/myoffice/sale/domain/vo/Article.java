package edu.noia.myoffice.sale.domain.vo;

import edu.noia.myoffice.common.domain.vo.Tariff;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@EqualsAndHashCode(of = "articleId", callSuper = false, doNotUseGetters = true)
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Article {
    @NonNull
    ArticleId articleId;
    @NonNull
    String name;
    @NonNull
    Tariff tariff;
}
