package edu.noia.myoffice.sale.domain.vo;

import edu.noia.myoffice.sale.domain.aggregate.CartState;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(exclude = "items", callSuper = false, doNotUseGetters = true)
@Getter
@Builder(builderMethodName = "hiddenBuilder", toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartSample<T extends CartItem> implements CartState<T> {
    @NonNull
    FolderId folderId;
    @NonNull
    CartType type;
    String title;
    String notes;
    @Singular
    List<T> items;

    public static CartSample of(CartState state) {
        return CartSample.builder(state.getFolderId(), state.getType())
                .title(state.getTitle())
                .notes(state.getNotes())
                .items(state.getItems())
                .build();
    }

    public static CartSampleBuilder builder(@NonNull FolderId folderId, @NonNull CartType type) {
        return hiddenBuilder()
                .folderId(folderId)
                .type(type);
    }

}
