package edu.noia.myoffice.sale.query.data.jpa;

import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.FolderId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

@JpaRepo
@RepositoryRestResource(path = "carts", collectionResourceRel = "carts", itemResourceRel = "cart")
public interface JpaCartStateRepository extends CrudRepository<JpaCartState, Long> {

    @RestResource(path = "byId", rel = "findById")
    Optional<JpaCartState> findById(CartId id);

    @RestResource(path = "byFolder", rel = "findByFolder")
    List<JpaCartState> findByFolderId(FolderId id);

    @Override
    @RestResource(exported = false)
    JpaCartState save(JpaCartState entity);

    @Override
    @RestResource(exported = false)
    void delete(JpaCartState entity);
}
