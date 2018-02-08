package edu.noia.myoffice.sale.query.data.jpa;

import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.FolderId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface JpaCartStateRepository extends CrudRepository<JpaCartState, Long> {

    @RestResource
    Optional<JpaCartState> findById(CartId id);

    @RestResource(path = "folder", rel = "findByFolder")
    List<JpaCartState> findByFolderId(@Param("folder") FolderId folderId);

    @Override
    @RestResource(exported = false)
    JpaCartState save(JpaCartState entity);

    @Override
    @RestResource(exported = false)
    void delete(JpaCartState entity);
}
