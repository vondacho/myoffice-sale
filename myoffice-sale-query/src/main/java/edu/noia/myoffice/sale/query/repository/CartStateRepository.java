package edu.noia.myoffice.sale.query.repository;

import edu.noia.myoffice.sale.domain.aggregate.CartState;
import edu.noia.myoffice.sale.domain.aggregate.MutableCartState;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.FolderId;

import java.util.List;
import java.util.Optional;

public interface CartStateRepository {

    Optional<MutableCartState> findById(CartId id);

    List<MutableCartState> findByFolderId(FolderId folderId);

    MutableCartState save(CartId id, CartState state);
}
