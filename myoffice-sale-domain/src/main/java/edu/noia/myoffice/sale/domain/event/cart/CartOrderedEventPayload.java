package edu.noia.myoffice.sale.domain.event.cart;

import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.sale.domain.event.folder.FolderEventPayload;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.CartType;
import edu.noia.myoffice.sale.domain.vo.FolderId;
import edu.noia.myoffice.sale.domain.vo.OrderId;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartOrderedEventPayload implements CartEventPayload, FolderEventPayload {

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
