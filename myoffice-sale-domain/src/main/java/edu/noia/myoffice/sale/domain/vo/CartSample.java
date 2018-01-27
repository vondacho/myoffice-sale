package edu.noia.myoffice.sale.domain.vo;

import edu.noia.myoffice.sale.domain.aggregate.CartState;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ToString(exclude = {"items"})
@Accessors(chain = true)
@Getter
@Setter(value = AccessLevel.PRIVATE)
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartSample implements CartState {

    @NonNull
    FolderId folderId;
    @NonNull
    CartType type;
    String title;
    String notes;
    List<CartItem> items;

    public static CartSample of(CartState state) {
        return CartSample.of(state.getFolderId(), state.getType())
                .setTitle(state.getTitle())
                .setNotes(state.getNotes())
                .setItems(state.getItems());
    }

    @Override
    public List<CartItem> getItems() {
        return Optional.ofNullable(items)
                .map(i -> Collections.unmodifiableList(new ArrayList<>(i)))
                .orElse(new ArrayList<>());
    }

    @Override
    public Optional<CartItem> getItem(CartItemId itemId) {
        return Optional.ofNullable(items)
                .flatMap(i -> i
                        .stream()
                        .filter(item -> item.getId().equals(itemId))
                        .findFirst());
    }
}
