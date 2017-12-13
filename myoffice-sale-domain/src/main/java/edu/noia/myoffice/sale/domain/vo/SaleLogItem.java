package edu.noia.myoffice.sale.domain.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@EqualsAndHashCode(of = {"folderId"}, callSuper = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleLogItem extends CartItem {

    @NonNull
    FolderId folderId;

    public static SaleLogItem of(UUID id, @NonNull FolderId folderId, @NonNull Long quantity, @NonNull Article article) {
        SaleLogItem item = new SaleLogItem(folderId);
        item.id = id;
        item.quantity = quantity;
        item.article = article;
        return item;
    }
}
