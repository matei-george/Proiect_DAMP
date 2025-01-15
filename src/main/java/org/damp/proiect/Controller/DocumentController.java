package org.damp.proiect.Controller;

import org.damp.proiect.DTO.DocumentDTO;
import org.damp.proiect.DTO.DocumentMapper;
import org.damp.proiect.Model.Beneficiari.Beneficiar;
import org.damp.proiect.Model.Contracte.Contract;
import org.damp.proiect.Model.Document.Document;
import org.damp.proiect.Service.interfete.IContractService;
import org.damp.proiect.Service.interfete.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/documente")
public class DocumentController {

    @Autowired
    private IDocumentService documentService;

    @Autowired
    private IContractService contractService; // Injectarea serviciului IContractService

    @GetMapping
    public String documentePage(Model model) {
        List<Document> documente = documentService.getAllDocuments();
        model.addAttribute("documente", documente);
        return "documente"; // Returnează documente.html
    }

    @PostMapping("/creare")
    public String creareDocument(
            @RequestParam String numeDocument,
            @RequestParam String dataIncarcare,
            @RequestParam String tip,
            @RequestParam Long contractId, // ID-ul contractului
            Model model
    ) {
        // Validare contract
        Contract contract = contractService.getContractById(contractId);
        if (contract == null) {
            model.addAttribute("error", "Contract invalid!");
            return "documente";
        }

        // Creare obiect Document
        Document document = new Document();
        document.setNumeDocument(numeDocument);
        document.setDataIncarcare(Date.valueOf(dataIncarcare).toLocalDate());
        document.setTip(tip);
        document.setContract(contract);

        // Salvare document
        documentService.creareDocument(document);

        return "redirect:/documente"; // Redirecționează către pagina de documente
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

    @GetMapping("/{id}/sterge")
    public String stergeDocument(@PathVariable Long id) {
        documentService.stergeDocument(id); // Șterge documentul
        return "redirect:/documente"; // Redirecționează către pagina documentelor
    }
}