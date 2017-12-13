package edu.noia.myoffice.sale.data.jpa;

import edu.noia.myoffice.common.data.jpa.JpaAuditableEntity;
import edu.noia.myoffice.sale.domain.aggregate.CartMutableState;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import edu.noia.myoffice.sale.domain.vo.CartItem;
import edu.noia.myoffice.sale.domain.vo.CartType;
import edu.noia.myoffice.sale.domain.vo.FolderId;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Accessors(chain=true)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JpaCartState extends JpaAuditableEntity implements CartMutableState<CartItem> {

    UUID id;
    FolderId folderId;
    @Enumerated(EnumType.STRING)
    CartType type;
    String title;
    String notes;

    @Type(type = "edu.noia.myoffice.sale.data.jpa.hibernate.converter.CartItemConverter")
    @Columns(columns = {
            @Column(name="id"),
            @Column(name="articleId"),
            @Column(name="title"),
            @Column(name="tariff"),
            @Column(name="unit"),
            @Column(name="quantity")
    })
    @ElementCollection
    @CollectionTable(name = "offer_items", joinColumns = @JoinColumn(name = "offer_pk"))
    List<CartItem> items = new ArrayList<>();

    public static JpaCartState of(CartState state) {
        return (JpaCartState)new JpaCartState()
                .setFolderId(state.getFolderId())
                .setType(state.getType())
                .modify(state)
                .add(state.getItems());
    }
}
