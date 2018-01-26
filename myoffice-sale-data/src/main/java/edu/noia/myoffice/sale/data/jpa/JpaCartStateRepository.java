package edu.noia.myoffice.sale.data.jpa;

import edu.noia.myoffice.sale.domain.vo.CartId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

public interface JpaCartStateRepository extends
        PagingAndSortingRepository<JpaCartState, Long>,
        JpaSpecificationExecutor<JpaCartState> {

    @RestResource(exported = false)
    Optional<JpaCartState> findById(CartId id);

    @RestResource(path = "folder", rel = "findByFolder")
    List<JpaCartState> findByFolderId(@Param("folder") CartId folderId);

    List<JpaCartState> findAll(Specification specification);

    Page<JpaCartState> findAll(Specification specification, Pageable pageable);

}
