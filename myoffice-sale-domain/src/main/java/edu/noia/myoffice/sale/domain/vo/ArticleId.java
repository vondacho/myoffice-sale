package edu.noia.myoffice.sale.domain.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@ToString
@EqualsAndHashCode(of = "id", callSuper = false, doNotUseGetters = true)
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleId {
    @NonNull
    UUID id;
}
