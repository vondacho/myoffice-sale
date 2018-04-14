package edu.noia.myoffice.sale.query.data.jpa;

import edu.noia.myoffice.common.data.jpa.JpaBaseEntity;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import edu.noia.myoffice.sale.domain.vo.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Table(name = "cart")
@Audited
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Accessors(chain=true)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JpaCartState extends JpaBaseEntity implements CartState {

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

    @Type(type = "edu.noia.myoffice.sale.query.data.jpa.hibernate.type.CartItemType")
    @Columns(columns = {
            @Column(name="id"),
            @Column(name="articleId"),
            @Column(name="title"),
            @Column(name = "tariff_price"),
            @Column(name = "tariff_target_quantity"),
            @Column(name = "tariff_target_unit"),
            @Column(name="quantity"),
            @Column(name = "quantity_unit"),
            @Column(name="timestamp")
    })
    @ElementCollection
    @CollectionTable(name = "t_cart_items", joinColumns = @JoinColumn(name = "fk_cart"))
    List<CartItem> items = new ArrayList<>();

    @Override
    public Optional<CartItem> getItem(CartItemId itemId) {
        return items.stream().filter(item -> item.getId().equals(itemId)).findFirst();
    }

    @Override
    public CartState add(CartItem... cartItems) {
        items.addAll(Arrays.asList(cartItems));
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
}
