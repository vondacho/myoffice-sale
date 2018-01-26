package edu.noia.myoffice.sale.domain.vo;

import edu.noia.myoffice.sale.domain.aggregate.CartMutableState;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.*;

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartMutableSample implements CartMutableState {

    FolderId folderId;
    CartType type;
    String title;
    String notes;
    @Setter(value = AccessLevel.PRIVATE)
    Map<CartItemId, CartItem> items;
    OrderId orderId;
    InvoiceId invoiceId;

    public static CartMutableState of(CartState cartState) {
        return new CartMutableSample()
                .setFolderId(cartState.getFolderId())
                .setType(cartState.getType())
                .setTitle(cartState.getTitle())
                .setNotes(cartState.getNotes())
                .setItems(new HashMap<>())
                .addAll(cartState.getItems());
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
}
