package edu.noia.myoffice.sale.query.data.jpa;

import edu.noia.myoffice.common.data.jpa.JpaBaseEntity;
import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.sale.domain.aggregate.CartMutableState;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import edu.noia.myoffice.sale.domain.vo.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Accessors(chain=true)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JpaCartState extends JpaBaseEntity implements CartMutableState {

    CartId id;
    FolderId folderId;
    @Enumerated(EnumType.STRING)
    CartType type;
    String title;
    String notes;
    OrderId orderId;
    InvoiceId invoiceId;

    public static JpaCartState of(CartId cartId, CartState state) {
        return new JpaCartState()
                .setId(cartId)
                .setFolderId(state.getFolderId())
                .setType(state.getType())
                .setTitle(state.getTitle())
                .setNotes(state.getNotes())
                .setItems(state.getItems());
    }

    @Type(type = "edu.noia.myoffice.sale.query.data.jpa.hibernate.converter.CartItemConverter")
    @Columns(columns = {
            @Column(name="id"),
            @Column(name="articleId"),
            @Column(name="title"),
            @Column(name="tariff"),
            @Column(name="unit"),
            @Column(name="quantity"),
            @Column(name="timestamp")
    })
    @ElementCollection
    @CollectionTable(name = "cart_items", joinColumns = @JoinColumn(name = "cart_pk"))
    List<CartItem> items = new ArrayList<>();

    @Override
    public Optional<CartItem> getItem(CartItemId itemId) {
        return items.stream().filter(item -> item.getId().equals(itemId)).findFirst();
    }

    @Override
    public CartMutableState add(CartItem item) {
        items.add(item);
        return this;
    }

    @Override
    public Optional<CartItem> remove(CartItemId itemId) {
        return getItem(itemId)
                .map(item -> {
                    items.remove(item);
                    return item;
                });
    }

    @Override
    public CartMutableState modify(EntityState modifier) {
        return null;
    }

    @Override
    public CartMutableState patch(EntityState modifier) {
        return null;
    }
}
