package edu.noia.myoffice.sale.domain.vo;

import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.sale.domain.aggregate.CartMutableState;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.*;

@Accessors(chain = true)
@Getter
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartMutableSample implements CartMutableState {

    @NonNull
    FolderId folderId;
    @NonNull
    CartType type;
    @Setter
    String title;
    @Setter
    String notes;
    @Setter
    OrderId orderId;
    @Setter
    InvoiceId invoiceId;
    @Setter(value = AccessLevel.PRIVATE)
    Map<CartItemId, CartItem> items;

    public static CartMutableState of(CartState state) {
        return CartMutableSample.of(state.getFolderId(), state.getType())
                .setTitle(state.getTitle())
                .setNotes(state.getNotes())
                .setItems(new HashMap<>())
                .addAll(state.getItems());
    }

    @Override
    public List<CartItem> getItems() {
        return Optional.ofNullable(items)
                .map(Map::values)
                .map(i -> Collections.unmodifiableList(new ArrayList<>(i)))
                .orElse(new ArrayList<>());
    }

    @Override
    public Optional<CartItem> getItem(CartItemId itemId) {
        return Optional.ofNullable(items).map(i -> i.get(itemId));
    }

    @Override
    public CartMutableState add(CartItem item) {
        if (items == null) {
            items = new HashMap<>();
        }
        items.put(item.getId(), item);
        return this;
    }

    @Override
    public Optional<CartItem> remove(CartItemId itemId) {
        return items != null ? Optional.ofNullable(items.remove(itemId)) : Optional.empty();
    }

    @Override
    public CartMutableState modify(EntityState modifier) {
        return this;
    }

    @Override
    public CartMutableState patch(EntityState modifier) {
        return this;
    }
}
