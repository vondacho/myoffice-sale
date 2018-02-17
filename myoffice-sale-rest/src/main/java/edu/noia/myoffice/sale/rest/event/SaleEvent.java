package edu.noia.myoffice.sale.rest.event;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.sale.domain.vo.CartId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.Resource;

import java.time.Instant;

@Getter
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleEvent implements Event<Resource<CartId>> {

    @NonNull
    Instant timestamp;
    @NonNull
    Class cause;
    @NonNull
    Resource<CartId> payload;
}
