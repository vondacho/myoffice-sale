package edu.noia.myoffice.sale.domain.repository;

import edu.noia.myoffice.common.domain.repository.EntityRepository;
import edu.noia.myoffice.sale.domain.aggregate.Cart;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.CartType;
import edu.noia.myoffice.sale.domain.vo.FolderId;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CartRepository extends EntityRepository<Cart, CartId, CartState> {
    @Transactional(readOnly = true)
    Optional<Cart> findOne(FolderId folderId, CartType type);
}
