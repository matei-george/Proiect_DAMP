package org.damp.proiect;

import org.damp.proiect.Controller.BeneficiarController;
import org.damp.proiect.DTO.BeneficiarDTO;
import org.damp.proiect.Model.Beneficiari.Beneficiar;
import org.damp.proiect.Repository.BeneficiarRepository;
import org.damp.proiect.Service.implementari.BeneficiarService;
import org.damp.proiect.Service.interfete.IBeneficiarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Configurează contextul web
public class BeneficiarModuleTest {

    @Autowired
    private TestRestTemplate restTemplate; // Injectează automat TestRestTemplate
    @Autowired
    private BeneficiarRepository beneficiarRepository;

    @Autowired
    private BeneficiarService beneficiarService;

    @Autowired
    private BeneficiarController beneficiarController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(beneficiarController).build();
        beneficiarRepository.deleteAll(); // Curăță baza de date înainte de fiecare test
    }

    // Testare pentru Repository
    @Test
    public void testRepositorySaveAndFind() {
        Beneficiar beneficiar = new Beneficiar();
        beneficiar.setNume("Popescu");
        beneficiar.setPrenume("Ion");
        beneficiar.setEmail("ion.popescu@example.com");
        beneficiar.setTelefon("0712345678");
        beneficiar.setAdresa("Strada Florilor");
        beneficiar.setCnp("1234567890123");
        beneficiar.setParola("parola123");

        Beneficiar saved = beneficiarRepository.save(beneficiar);

        Optional<Beneficiar> found = beneficiarRepository.findByEmail("ion.popescu@example.com");
        assertTrue(found.isPresent());
        assertEquals("Popescu", found.get().getNume());
    }

    // Testare pentru Serviciu
    @Test
    public void testServiceVizualizeazaCont() {
        Beneficiar beneficiar = new Beneficiar();
        beneficiar.setNume("Vasilescu");
        beneficiar.setPrenume("Andrei");
        beneficiar.setEmail("andrei.vasilescu@example.com");
        beneficiar.setTelefon("0723456789");
        beneficiar.setAdresa("Strada Lalelelor");
        beneficiar.setCnp("9876543210123");
        beneficiar.setParola("parola123");

        Beneficiar saved = beneficiarRepository.save(beneficiar);

        Beneficiar rezultat = beneficiarService.vizualizeazaCont(saved.getIdBeneficiar());
        assertNotNull(rezultat);
        assertEquals("Vasilescu", rezultat.getNume());
        assertEquals("andrei.vasilescu@example.com", rezultat.getEmail());
    }

    // Testare pentru Controller
    @Test
    public void testControllerCreareBeneficiar() {
        BeneficiarDTO beneficiarDTO = new BeneficiarDTO();
        beneficiarDTO.setNume("Popescu");
        beneficiarDTO.setPrenume("Ion");
        beneficiarDTO.setEmail("ion.popescu@example.com");
        beneficiarDTO.setTelefon("0712345678");
        beneficiarDTO.setCnp("1234567890123");
        beneficiarDTO.setParola("parola123");
        beneficiarDTO.setAdresa("Strada Florilor");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<BeneficiarDTO> request = new HttpEntity<>(beneficiarDTO, headers);

        ResponseEntity<BeneficiarDTO> response = restTemplate.postForEntity("/api/beneficiari/creare-cont", request, BeneficiarDTO.class);

        // Asigură-te că răspunsul este 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verifică că răspunsul conține valorile așteptate
        BeneficiarDTO responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("Popescu", responseBody.getNume());
        assertEquals("Ion", responseBody.getPrenume());
    }

    @Test
    public void testControllerVizualizareBeneficiar() throws Exception {
        Beneficiar beneficiar = new Beneficiar();
        beneficiar.setNume("Radu");
        beneficiar.setPrenume("Elena");
        beneficiar.setEmail("elena.radu@example.com");
        beneficiar.setTelefon("0734567890");
        beneficiar.setAdresa("Strada Zambilelor");
        beneficiar.setCnp("6789012345678");
        beneficiar.setParola("parola789");

        Beneficiar saved = beneficiarRepository.save(beneficiar);

        mockMvc.perform(get("/api/beneficiari/" + saved.getIdBeneficiar()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nume").value("Radu"))
                .andExpect(jsonPath("$.email").value("elena.radu@example.com"));
    }

    @Test
    public void testControllerStergereBeneficiar() throws Exception {
        Beneficiar beneficiar = new Beneficiar();
        beneficiar.setNume("Radu");
        beneficiar.setPrenume("Elena");
        beneficiar.setEmail("elena.radu@example.com");
        beneficiar.setTelefon("0734567890");
        beneficiar.setAdresa("Strada Zambilelor");
        beneficiar.setCnp("6789012345678");
        beneficiar.setParola("parola789");

        Beneficiar saved = beneficiarRepository.save(beneficiar);

        mockMvc.perform(delete("/api/beneficiari/" + saved.getIdBeneficiar()))
                .andExpect(status().isNoContent());

        Optional<Beneficiar> found = beneficiarRepository.findById(saved.getIdBeneficiar());
        assertFalse(found.isPresent());
    }
}
