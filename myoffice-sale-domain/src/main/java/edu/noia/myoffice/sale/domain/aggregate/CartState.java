package edu.noia.myoffice.sale.domain.aggregate;

import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.sale.domain.vo.CartItem;
import edu.noia.myoffice.sale.domain.vo.CartItemId;
import edu.noia.myoffice.sale.domain.vo.CartType;
import edu.noia.myoffice.sale.domain.vo.FolderId;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface CartState extends EntityState {

    @NonNull
    FolderId getFolderId();

    @NonNull
    CartType getType();

    String getTitle();

    String getNotes();

    List<CartItem> getItems();

    Optional<CartItem> getItem(CartItemId itemId);
}
