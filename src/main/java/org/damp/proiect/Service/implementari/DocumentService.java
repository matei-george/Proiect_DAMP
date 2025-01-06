package org.damp.proiect.Service.implementari;

import org.damp.proiect.Model.Document.Document;
import org.damp.proiect.Repository.DocumentRepository;
import org.damp.proiect.Service.interfete.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentService implements IDocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    // Creare document
    @Transactional
    public Document creareDocument(Document document) {
        return documentRepository.save(document);
    }

    // Obținerea documentului după ID
    public Document getDocumentById(Long id) {
        return documentRepository.findById(id).orElseThrow(() -> new RuntimeException("Document inexistent!"));
    }

    // Obținerea tuturor documentelor
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    // Ștergere document
    @Transactional
    public void stergeDocument(Long id) {
        if (documentRepository.existsById(id)) {
            documentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Document inexistent!");
        }
    }
}
