package org.damp.proiect;

import org.damp.proiect.Controller.ContractController;
import org.damp.proiect.DTO.ContractDTO;
import org.damp.proiect.Model.Beneficiari.Beneficiar;
import org.damp.proiect.Model.Contracte.Contract;
import org.damp.proiect.Model.Furnizor.Furnizor;
import org.damp.proiect.Repository.BeneficiarRepository;
import org.damp.proiect.Repository.ContractRepository;
import org.damp.proiect.Repository.FurnizorRepository;
import org.damp.proiect.Service.implementari.ContractService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContractModuleTest {

    @Autowired
    private FurnizorRepository furnizorRepository;
    @Autowired
    private BeneficiarRepository beneficiarRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractService contractService;

    @Autowired
    private ContractController contractController;

    @BeforeEach
    void setUp() {
        contractRepository.deleteAll(); // Șterge toate contractele
        beneficiarRepository.deleteAll(); // Șterge toți beneficiarii
        furnizorRepository.deleteAll();
    }

    @Test
    public void testAdaugaContract() {
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

        // Creează un DTO pentru contract
        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setBeneficiarId(savedBeneficiar.getIdBeneficiar());
        contractDTO.setFurnizorId(savedFurnizor.getId());
        contractDTO.setTipServiciu("electricitate");
        contractDTO.setDataIncepere(new Date());
        contractDTO.setDataExpirare(new Date());
        contractDTO.setValoareContract(1200.50);
        contractDTO.setDocumente("contract_electricitate1.pdf");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ContractDTO> request = new HttpEntity<>(contractDTO, headers);

        ResponseEntity<ContractDTO> response = restTemplate.postForEntity("/api/contracte/adauga", request, ContractDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        ContractDTO responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("electricitate", responseBody.getTipServiciu());
        assertEquals(savedBeneficiar.getIdBeneficiar(), responseBody.getBeneficiarId());
    }

    @Test
    public void testVizualizareContract() {
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

        // Creează și salvează un contract
        Contract contract = new Contract();
        contract.setBeneficiar(savedBeneficiar);
        contract.setFurnizor(savedFurnizor);
        contract.setTipServiciu("electricitate");
        contract.setDataIncepere(new Date());
        contract.setDataExpirare(new Date());
        contract.setValoareContract(1200.50);
        contract.setDocumente("contract_electricitate1.pdf");
        Contract savedContract = contractRepository.save(contract);

        // Vizualizează contractul
        ResponseEntity<ContractDTO> response = restTemplate.getForEntity("/api/contracte/" + savedContract.getId(), ContractDTO.class);

        // Asigură-te că răspunsul este 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifică detaliile contractului
        ContractDTO responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("electricitate", responseBody.getTipServiciu());
        assertEquals(savedBeneficiar.getIdBeneficiar(), responseBody.getBeneficiarId());
        assertEquals(savedFurnizor.getIdFurnizor(), responseBody.getFurnizorId());
    }

    @Test
    public void testModificaContract() {
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
        contract.setDataIncepere(new Date());
        contract.setDataExpirare(new Date());
        contract.setValoareContract(1200.50);
        contract.setDocumente("contract_electricitate1.pdf");
        contract.setBeneficiar(savedBeneficiar); // Asociază beneficiarul
        contract.setFurnizor(savedFurnizor);     // Asociază furnizorul

        // Salvează contractul
        Contract savedContract = contractRepository.save(contract);

        // Creează un furnizor actualizat
        Furnizor furnizorActualizat = new Furnizor();
        furnizorActualizat.setNumeFurnizor("Engie");
        furnizorActualizat.setTipServiciu("gaze");
        furnizorActualizat.setContact("contact@engie.ro");
        Furnizor savedFurnizorActualizat = furnizorRepository.save(furnizorActualizat);

        // Creează un DTO pentru contractul actualizat
        ContractDTO updatedContractDTO = new ContractDTO();
        updatedContractDTO.setTipServiciu("gaze");
        updatedContractDTO.setDataIncepere(new Date());
        updatedContractDTO.setDataExpirare(new Date());
        updatedContractDTO.setValoareContract(800.75);
        updatedContractDTO.setDocumente("contract_gaze1.pdf");
        updatedContractDTO.setBeneficiarId(savedBeneficiar.getIdBeneficiar());
        updatedContractDTO.setFurnizorId(savedFurnizorActualizat.getIdFurnizor());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ContractDTO> request = new HttpEntity<>(updatedContractDTO, headers);

        // Trimite cererea de modificare
        ResponseEntity<ContractDTO> response = restTemplate.exchange(
                "/api/contracte/" + savedContract.getId() + "/modifica",
                HttpMethod.PUT,
                request,
                ContractDTO.class
        );

        // Verifică răspunsul
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ContractDTO responseBody = response.getBody();
        assertNotNull(responseBody);

        // Loguri pentru depanare
        System.out.println("Expected BeneficiarId: " + savedBeneficiar.getIdBeneficiar());
        System.out.println("Actual BeneficiarId: " + responseBody.getBeneficiarId());
        System.out.println("Expected FurnizorId: " + savedFurnizorActualizat.getIdFurnizor());
        System.out.println("Actual FurnizorId: " + responseBody.getFurnizorId());

        assertEquals("gaze", responseBody.getTipServiciu());
        assertEquals(800.75, responseBody.getValoareContract());
        assertEquals(savedBeneficiar.getIdBeneficiar(), responseBody.getBeneficiarId());
    }



    @Test
    public void testStergeContract() {
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

        // Creează și asociază un contract
        Contract contract = new Contract();
        contract.setTipServiciu("electricitate");
        contract.setDataIncepere(new Date());
        contract.setDataExpirare(new Date());
        contract.setValoareContract(1200.50);
        contract.setDocumente("contract_electricitate1.pdf");
        contract.setBeneficiar(savedBeneficiar);
        contract.setFurnizor(savedFurnizor);

        // Salvează contractul
        Contract savedContract = contractRepository.save(contract);

        // Șterge contractul
        restTemplate.delete("/api/contracte/" + savedContract.getId() + "/anuleaza");

        // Verifică ștergerea
        assertFalse(contractRepository.findById(savedContract.getId()).isPresent(), "Contractul nu a fost șters.");
    }



    @Test
    public void testObtinereContracteActive() {
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

        // Creează un DTO pentru contract
        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setBeneficiarId(savedBeneficiar.getIdBeneficiar());
        contractDTO.setFurnizorId(savedFurnizor.getId());
        contractDTO.setTipServiciu("electricitate");
        contractDTO.setDataIncepere(new Date());
        contractDTO.setDataExpirare(new Date());
        contractDTO.setValoareContract(1200.50);
        contractDTO.setDocumente("contract_electricitate1.pdf");

        // Salvează contractul
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ContractDTO> request = new HttpEntity<>(contractDTO, headers);

        ResponseEntity<ContractDTO> response = restTemplate.postForEntity("/api/contracte/adauga", request, ContractDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        ContractDTO responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("electricitate", responseBody.getTipServiciu());
        assertEquals(savedBeneficiar.getIdBeneficiar(), responseBody.getBeneficiarId());
    }

    @Test
    public void testCautareContracte() {
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
        contract.setDataIncepere(new Date());
        contract.setDataExpirare(new Date());
        contract.setValoareContract(1200.50);
        contract.setDocumente("contract_electricitate1.pdf");
        contract.setBeneficiar(savedBeneficiar); // Asociază beneficiarul
        contract.setFurnizor(savedFurnizor);     // Asociază furnizorul

        // Salvează contractul
        contractRepository.save(contract);

        // Trimite cererea GET pentru a căuta contractele
        ResponseEntity<List> response = restTemplate.getForEntity("/api/contracte/cauta?tipServiciu=electricitate", List.class);

        // Verifică răspunsul
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());

        // Opțional: verifică dimensiunea și conținutul răspunsului
        assertEquals(1, response.getBody().size());
        Map<String, Object> contractMap = (Map<String, Object>) response.getBody().get(0);
        assertEquals("electricitate", contractMap.get("tipServiciu"));
        assertEquals(1200.50, contractMap.get("valoareContract"));
    }

}
