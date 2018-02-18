package edu.noia.myoffice.sale.query.data.jpa;

import org.springframework.data.repository.history.RevisionRepository;

@JpaRevisionRepo
public interface JpaCartStateRevisionRepository extends RevisionRepository<JpaCartState, Long, Integer> {
}
