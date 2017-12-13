package edu.noia.myoffice.sale.data.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;
import java.util.UUID;

public interface JpaCartStateRepository extends PagingAndSortingRepository<JpaCartState, Long> {

    @RestResource(exported = false)
    Optional<JpaCartState> findById(UUID id);

    @RestResource(path = "folder", rel = "findByFolder")
    Page<JpaCartState> findByFolderId(@Param("folder") UUID folderId, Pageable pageable);

    @RestResource(path = "name", rel = "findByTitle")
    Page<JpaCartState> findByTitleContainingIgnoreCase(@Param("term") String term, Pageable pageable);
}
