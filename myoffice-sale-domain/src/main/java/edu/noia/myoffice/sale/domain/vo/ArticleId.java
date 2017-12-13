package edu.noia.myoffice.sale.domain.vo;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class ArticleId {
    @NonNull
    UUID id;
}
