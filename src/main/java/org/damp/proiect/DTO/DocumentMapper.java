package org.damp.proiect.DTO;

import org.damp.proiect.DTO.DocumentDTO;
import org.damp.proiect.Model.Document.Document;

public class DocumentMapper {

    // Conversie din Document în DocumentDTO
    public static DocumentDTO toDTO(Document document) {
        return new DocumentDTO(
                document.getId(),
                document.getContract() != null ? document.getContract().getId() : null,
                document.getNumeDocument(),
                document.getDataIncarcare(),
                document.getTip()
        );
    }

    // Conversie din DocumentDTO în Document
    public static Document toEntity(DocumentDTO dto) {
        Document document = new Document();
        document.setId(dto.getId());
        document.setNumeDocument(dto.getNumeDocument());
        document.setDataIncarcare(dto.getDataIncarcare());
        document.setTip(dto.getTip());
        return document;
    }
}
