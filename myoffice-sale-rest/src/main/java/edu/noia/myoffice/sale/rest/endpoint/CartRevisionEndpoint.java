package edu.noia.myoffice.sale.rest.endpoint;

import edu.noia.myoffice.common.rest.util.IdentifiantPropertyEditorSupport;
import edu.noia.myoffice.sale.domain.vo.CartId;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.history.Revisions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/sale/v1/carts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartRevisionEndpoint {

    @GetMapping(value = "/{id}/revisions")
    public ResponseEntity<Revisions> cartRevisions(@PathVariable(value = "id") Long primaryId) {
        return ok(Revisions.none());
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.registerCustomEditor(CartId.class,
                new IdentifiantPropertyEditorSupport<>(s -> CartId.of(UUID.fromString(s))));
    }
}
