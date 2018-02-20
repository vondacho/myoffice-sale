package edu.noia.myoffice.sale.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import edu.noia.myoffice.sale.query.data.jpa.JpaCartState;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.DefaultRevisionEntity;
import org.springframework.data.envers.repository.support.DefaultRevisionMetadata;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor(staticName = "of")
public class CartRevisionDto {

    @JsonIgnore
    @NonNull
    Revision<Integer, JpaCartState> revision;

    public static List<CartRevisionDto> from(Revisions revisions) {
        List<Revision<Integer, JpaCartState>> rl = revisions.getContent();
        return rl.stream().map(CartRevisionDto::of).collect(toList());
    }

    public Integer getRevisionNumber() {
        return revision.getRevisionNumber().orElse(null);
    }

    public CartState getEntity() {
        return revision.getEntity();
    }

    public LocalDateTime getRevisionDate() {
        DefaultRevisionMetadata revisionMetadata = (DefaultRevisionMetadata) (revision.getMetadata());
        DefaultRevisionEntity revisionEntity = revisionMetadata.getDelegate();
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(revisionEntity.getTimestamp()), ZoneOffset.UTC);
    }
}
