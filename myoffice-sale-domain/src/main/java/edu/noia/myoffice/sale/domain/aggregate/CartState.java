package edu.noia.myoffice.sale.domain.aggregate;

import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.sale.domain.vo.CartItem;
import edu.noia.myoffice.sale.domain.vo.CartType;
import edu.noia.myoffice.sale.domain.vo.FolderId;
import edu.noia.myoffice.sale.domain.vo.InvoiceId;
import lombok.NonNull;

import javax.validation.Valid;
import java.util.List;

public interface CartState<T extends CartItem> extends EntityState {

    @NonNull
    FolderId getFolderId();
    @NonNull
    CartType getType();
    String getTitle();
    String getNotes();
    @Valid
    List<T> getItems();
    InvoiceId getInvoiceId();
}
