package edu.noia.myoffice.sale.domain.vo;

import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.*;
import java.util.stream.Stream;

@ToString(exclude = {"items"})
@Accessors(chain = true)
@Getter
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartSample implements CartState {

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

    public static CartSample of(CartState state) {
        return CartSample.of(state.getFolderId(), state.getType())
                .setTitle(state.getTitle())
                .setNotes(state.getNotes())
                .setItems(new HashMap<>())
                .add(state.getItems().toArray(new CartItem[0]));
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
    public CartSample add(CartItem... cartItems) {
        if (items == null) {
            items = new HashMap<>();
        }
        Stream.of(cartItems).forEach(i -> items.put(i.getId(), i));
        return this;
    }

    @Override
    public Optional<CartItem> remove(CartItemId itemId) {
        return items != null ? Optional.ofNullable(items.remove(itemId)) : Optional.empty();
    }

    @Override
    public CartState modify(EntityState modifier) {
        return this;
    }

    @Override
    public CartState patch(EntityState modifier) {
        return this;
    }
}
