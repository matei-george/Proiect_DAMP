package org.damp.proiect.Repository;

import org.damp.proiect.Model.Document.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findAll();
    List<Document> findByContractId(Long idContract);
    // Remove the incorrect method, findByIdDocument
    // Document findByIdDocument(Long id);
    Optional<Document> findById(Long id);  // This is the correct method
}
