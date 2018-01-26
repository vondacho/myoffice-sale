package edu.noia.myoffice.sale.domain.vo;

import edu.noia.myoffice.sale.domain.aggregate.CartState;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ToString(exclude = {"notes","items"})
@EqualsAndHashCode(callSuper = false)
@Getter
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartSample implements CartState {

    @NonNull
    FolderId folderId;
    @NonNull
    CartType type;
    @Setter(value = AccessLevel.PRIVATE)
    String title;
    @Setter(value = AccessLevel.PRIVATE)
    String notes;
    @Setter(value = AccessLevel.PRIVATE)
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
