package edu.noia.myoffice.sale.domain.vo;

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
}
