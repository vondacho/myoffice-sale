package edu.noia.myoffice.sale.domain.command.article;

import edu.noia.myoffice.sale.domain.command.cart.CartCommand;
import edu.noia.myoffice.sale.domain.vo.ArticleId;
import edu.noia.myoffice.sale.domain.vo.CartId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Getter
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReserveArticleListCommand implements ArticleListCommand<Long>, CartCommand {
    @NonNull
    CartId cartId;
    @NonNull
    Map<ArticleId, Long> articles;
}
