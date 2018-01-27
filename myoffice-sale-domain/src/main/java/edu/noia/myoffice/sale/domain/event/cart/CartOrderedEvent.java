package edu.noia.myoffice.sale.domain.event.cart;

import edu.noia.myoffice.common.domain.event.BaseEvent;
import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.sale.domain.event.folder.FolderEvent;
import edu.noia.myoffice.sale.domain.vo.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartOrderedEvent extends BaseEvent implements CartEvent, FolderEvent {

    @NonNull
    CartId cartId;
    @NonNull
    CartType cartType;
    @NonNull
    FolderId folderId;
    @NonNull
    OrderId orderId;
    @NonNull
    Amount amount;
}
