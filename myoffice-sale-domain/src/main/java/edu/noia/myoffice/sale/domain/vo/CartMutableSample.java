package edu.noia.myoffice.sale.domain.vo;

import edu.noia.myoffice.sale.domain.aggregate.CartMutableState;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import edu.noia.myoffice.sale.domain.vo.CartItem;
import edu.noia.myoffice.sale.domain.vo.CartType;
import edu.noia.myoffice.sale.domain.vo.FolderId;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
@Getter
@EqualsAndHashCode(exclude = "items", callSuper = false)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartMutableSample<T extends CartItem> implements CartMutableState<T> {
    @NonNull
    FolderId folderId;
    @NonNull
    CartType type;
    @Setter
    String title;
    @Setter
    String notes;
    List<T> items = new ArrayList();

    public static CartMutableState of(CartState state) {
        return new CartMutableSample(state.getFolderId(), state.getType()).modify(state).add(state.getItems());
    }

}
