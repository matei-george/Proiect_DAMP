package org.damp.proiect;

import org.damp.proiect.DTO.DocumentDTO;
import org.damp.proiect.Model.Contracte.Contract;
import org.damp.proiect.Model.Document.Document;
import org.damp.proiect.Model.Furnizor.Furnizor;
import org.damp.proiect.Repository.BeneficiarRepository;
import org.damp.proiect.Repository.ContractRepository;
import org.damp.proiect.Repository.DocumentRepository;
import org.damp.proiect.Repository.FurnizorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.damp.proiect.Model.Beneficiari.Beneficiar;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DocumentModuleTest {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private BeneficiarRepository beneficiarRepository;

    @Autowired
    private FurnizorRepository furnizorRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        documentRepository.deleteAll();
        contractRepository.deleteAll();
        furnizorRepository.deleteAll();
        beneficiarRepository.deleteAll();
    }

    @Test
    public void testCreareDocument() {
        // Creează și salvează un beneficiar
        Beneficiar beneficiar = new Beneficiar();
        beneficiar.setNume("Popescu");
        beneficiar.setPrenume("Ion");
        beneficiar.setEmail("ion.popescu@example.com");
        beneficiar.setTelefon("0712345678");
        beneficiar.setAdresa("Strada Florilor");
        beneficiar.setCnp("1234567890123");
        beneficiar.setParola("parola123");
        Beneficiar savedBeneficiar = beneficiarRepository.save(beneficiar);

        // Creează și salvează un furnizor
        Furnizor furnizor = new Furnizor();
        furnizor.setNumeFurnizor("Enel");
        furnizor.setTipServiciu("electricitate");
        furnizor.setContact("contact@enel.ro");
        Furnizor savedFurnizor = furnizorRepository.save(furnizor);

        // Creează și asociază un contract cu beneficiarul și furnizorul
        Contract contract = new Contract();
        contract.setTipServiciu("electricitate");
        contract.setDataIncepere(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())); // Conversie LocalDate -> Date
        contract.setDataExpirare(Date.from(LocalDate.now().plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant())); // Conversie LocalDate -> Date
        contract.setValoareContract(1200.50);
        contract.setDocumente("contract_electricitate1.pdf");
        contract.setBeneficiar(savedBeneficiar); // Asociază beneficiarul
        contract.setFurnizor(savedFurnizor);     // Asociază furnizorul
        Contract savedContract = contractRepository.save(contract);

        // Creează un DTO pentru document
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setContractId(savedContract.getId());
        documentDTO.setNumeDocument("document1.pdf");
        documentDTO.setDataIncarcare(LocalDate.now()); // Utilizăm LocalDate
        documentDTO.setTip("pdf");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DocumentDTO> request = new HttpEntity<>(documentDTO, headers);

        // Trimite cererea POST pentru a crea documentul
        ResponseEntity<DocumentDTO> response = restTemplate.postForEntity("/api/documente/creare", request, DocumentDTO.class);

    }



    @Test
    public void testGetDocumentById() {
        // Creează și salvează un beneficiar
        Beneficiar beneficiar = new Beneficiar();
        beneficiar.setNume("Popescu");
        beneficiar.setPrenume("Ion");
        beneficiar.setEmail("ion.popescu@example.com");
        beneficiar.setTelefon("0712345678");
        beneficiar.setAdresa("Strada Florilor");
        beneficiar.setCnp("1234567890123");
        beneficiar.setParola("parola123");
        Beneficiar savedBeneficiar = beneficiarRepository.save(beneficiar);

        // Creează și salvează un furnizor
        Furnizor furnizor = new Furnizor();
        furnizor.setNumeFurnizor("Enel");
        furnizor.setTipServiciu("electricitate");
        furnizor.setContact("contact@enel.ro");
        Furnizor savedFurnizor = furnizorRepository.save(furnizor);

        // Creează și asociază un contract cu beneficiarul și furnizorul
        Contract contract = new Contract();
        contract.setTipServiciu("electricitate");
        contract.setDataIncepere(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())); // Conversie LocalDate -> Date
        contract.setDataExpirare(Date.from(LocalDate.now().plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant())); // Conversie LocalDate -> Date
        contract.setValoareContract(1200.50);
        contract.setDocumente("contract_electricitate1.pdf");
        contract.setBeneficiar(savedBeneficiar); // Asociază beneficiarul
        contract.setFurnizor(savedFurnizor);     // Asociază furnizorul
        Contract savedContract = contractRepository.save(contract);

        // Creează și salvează un document asociat cu contractul
        Document document = new Document();
        document.setContract(savedContract);
        document.setNumeDocument("document1.pdf");
        document.setDataIncarcare(LocalDate.now()); // Folosește LocalDate
        document.setTip("pdf");

        Document savedDocument = documentRepository.save(document);

        // Trimite cererea GET pentru a obține documentul după ID
        ResponseEntity<DocumentDTO> response = restTemplate.getForEntity(
                "/api/documente/" + savedDocument.getId(),
                DocumentDTO.class
        );

        // Verifică răspunsul
        assertEquals(HttpStatus.OK, response.getStatusCode());
        DocumentDTO responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("document1.pdf", responseBody.getNumeDocument());
        assertEquals(savedContract.getId(), responseBody.getContractId());
    }

    @Test
    public void testGetAllDocuments() {
        // Creează și salvează un beneficiar
        Beneficiar beneficiar = new Beneficiar();
        beneficiar.setNume("Popescu");
        beneficiar.setPrenume("Ion");
        beneficiar.setEmail("ion.popescu@example.com");
        beneficiar.setTelefon("0712345678");
        beneficiar.setAdresa("Strada Florilor");
        beneficiar.setCnp("1234567890123");
        beneficiar.setParola("parola123");
        Beneficiar savedBeneficiar = beneficiarRepository.save(beneficiar);

        // Creează și salvează un furnizor
        Furnizor furnizor = new Furnizor();
        furnizor.setNumeFurnizor("Enel");
        furnizor.setTipServiciu("electricitate");
        furnizor.setContact("contact@enel.ro");
        Furnizor savedFurnizor = furnizorRepository.save(furnizor);

        // Creează și asociază un contract cu beneficiarul și furnizorul
        Contract contract = new Contract();
        contract.setTipServiciu("electricitate");
        contract.setDataIncepere(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())); // Conversie LocalDate -> Date
        contract.setDataExpirare(Date.from(LocalDate.now().plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant())); // Conversie LocalDate -> Date
        contract.setValoareContract(1200.50);
        contract.setDocumente("contract_electricitate1.pdf");
        contract.setBeneficiar(savedBeneficiar); // Asociază beneficiarul
        contract.setFurnizor(savedFurnizor);     // Asociază furnizorul
        Contract savedContract = contractRepository.save(contract);

        // Creează și salvează un document asociat cu contractul
        Document document = new Document();
        document.setContract(savedContract);
        document.setNumeDocument("document1.pdf");
        document.setDataIncarcare(LocalDate.now()); // Folosește LocalDate
        document.setTip("pdf");

        Document savedDocument = documentRepository.save(document);

        ResponseEntity<List> response = restTemplate.getForEntity("/api/documente/list", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testStergeDocument() {
        // Creează și salvează un beneficiar
        Beneficiar beneficiar = new Beneficiar();
        beneficiar.setNume("Popescu");
        beneficiar.setPrenume("Ion");
        beneficiar.setEmail("ion.popescu@example.com");
        beneficiar.setTelefon("0712345678");
        beneficiar.setAdresa("Strada Florilor");
        beneficiar.setCnp("1234567890123");
        beneficiar.setParola("parola123");
        Beneficiar savedBeneficiar = beneficiarRepository.save(beneficiar);

        // Creează și salvează un furnizor
        Furnizor furnizor = new Furnizor();
        furnizor.setNumeFurnizor("Enel");
        furnizor.setTipServiciu("electricitate");
        furnizor.setContact("contact@enel.ro");
        Furnizor savedFurnizor = furnizorRepository.save(furnizor);

        // Creează și asociază un contract cu beneficiarul și furnizorul
        Contract contract = new Contract();
        contract.setTipServiciu("electricitate");
        contract.setDataIncepere(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())); // Conversie LocalDate -> Date
        contract.setDataExpirare(Date.from(LocalDate.now().plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant())); // Conversie LocalDate -> Date
        contract.setValoareContract(1200.50);
        contract.setDocumente("contract_electricitate1.pdf");
        contract.setBeneficiar(savedBeneficiar); // Asociază beneficiarul
        contract.setFurnizor(savedFurnizor);     // Asociază furnizorul
        Contract savedContract = contractRepository.save(contract);

        // Creează și salvează un document asociat cu contractul
        Document document = new Document();
        document.setContract(savedContract);
        document.setNumeDocument("document1.pdf");
        document.setDataIncarcare(LocalDate.now()); // Folosește LocalDate
        document.setTip("pdf");

        Document savedDocument = documentRepository.save(document);

        restTemplate.delete("/api/documente/" + savedDocument.getId() + "/sterge");

        Optional<Document> deletedDocument = documentRepository.findById(savedDocument.getId());
        assertFalse(deletedDocument.isPresent());
    }
}
