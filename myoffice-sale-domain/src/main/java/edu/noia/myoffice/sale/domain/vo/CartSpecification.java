package edu.noia.myoffice.sale.domain.vo;

import edu.noia.myoffice.sale.domain.aggregate.CartState;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartSpecification {

    @NotNull
    FolderId folderId;
    @NotNull
    CartType type;
    String title;
    String notes;

    public static CartSpecification from(CartState state) {
        CartSpecification specification = new CartSpecification();
        specification.folderId = state.getFolderId();
        specification.type = state.getType();
        specification.notes = state.getNotes();
        specification.title = state.getTitle();
        return specification;
    }
}
