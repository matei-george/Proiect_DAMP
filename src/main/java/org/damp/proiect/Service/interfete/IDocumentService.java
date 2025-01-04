package org.damp.proiect.Service.interfete;

import org.damp.proiect.Model.Document.Document;

import java.util.List;

public interface IDocumentService {

    Document creareDocument(Document document);

    Document getDocumentById(Long id);

    List<Document> getAllDocuments();

    void stergeDocument(Long id);
}
