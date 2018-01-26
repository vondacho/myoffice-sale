package edu.noia.myoffice.sale.domain.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceId {
    @NonNull
    UUID id;
}
