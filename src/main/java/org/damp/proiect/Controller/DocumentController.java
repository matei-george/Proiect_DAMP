package org.damp.proiect.Controller;

import org.damp.proiect.DTO.DocumentDTO;
import org.damp.proiect.DTO.DocumentMapper;
import org.damp.proiect.Model.Beneficiari.Beneficiar;
import org.damp.proiect.Model.Document.Document;
import org.damp.proiect.Service.interfete.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller // Schimbat din @RestController în @Controller
@RequestMapping("/documente")
public class DocumentController {

    @Autowired
    private IDocumentService documentService;

    @GetMapping
    public String documentePage(Model model) {
        List<Document> documente = documentService.getAllDocuments();
        model.addAttribute("documente", documente);
        return "documente"; // Returnează documente.html
    }
    @PostMapping("/creare")
    public ResponseEntity<DocumentDTO> creareDocument(@RequestBody DocumentDTO documentDTO) {
        Document document = DocumentMapper.toEntity(documentDTO);
        Document createdDocument = documentService.creareDocument(document);
        return ResponseEntity.ok(DocumentMapper.toDTO(createdDocument));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> getDocumentById(@PathVariable Long id) {
        Document document = documentService.getDocumentById(id);
        return ResponseEntity.ok(DocumentMapper.toDTO(document));
    }

    @GetMapping("/list")
    public ResponseEntity<List<DocumentDTO>> getAllDocuments() {
        List<Document> documente = documentService.getAllDocuments();
        List<DocumentDTO> documenteDTO = documente.stream()
                .map(DocumentMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(documenteDTO);
    }

    @DeleteMapping("/{id}/sterge")
    public ResponseEntity<Void> stergeDocument(@PathVariable Long id) {
        documentService.stergeDocument(id);
        return ResponseEntity.noContent().build();
    }

}