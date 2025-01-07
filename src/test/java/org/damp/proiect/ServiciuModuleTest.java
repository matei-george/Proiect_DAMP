package org.damp.proiect;

import org.damp.proiect.DTO.ServiciuDTO;
import org.damp.proiect.Model.Furnizor.Furnizor;
import org.damp.proiect.Model.Serviciu.Serviciu;
import org.damp.proiect.Repository.FurnizorRepository;
import org.damp.proiect.Repository.ServiciuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiciuModuleTest {

    @Autowired
    private ServiciuRepository serviciuRepository;

    @Autowired
    private FurnizorRepository furnizorRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        serviciuRepository.deleteAll();
        furnizorRepository.deleteAll();
    }

    @Test
    public void testAdaugaServiciu() {
        // Creează și salvează un furnizor
        Furnizor furnizor = new Furnizor();
        furnizor.setNumeFurnizor("Enel");
        furnizor.setTipServiciu("electricitate");
        furnizor.setContact("contact@enel.ro");
        Furnizor savedFurnizor = furnizorRepository.save(furnizor);

        // Creează un DTO pentru serviciu
        ServiciuDTO serviciuDTO = new ServiciuDTO();
        serviciuDTO.setTipServiciu("electricitate");
        serviciuDTO.setFurnizorId(savedFurnizor.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ServiciuDTO> request = new HttpEntity<>(serviciuDTO, headers);

        ResponseEntity<ServiciuDTO> response = restTemplate.postForEntity(
                "/api/servicii/adauga?furnizorId=" + savedFurnizor.getId(),
                request,
                ServiciuDTO.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        ServiciuDTO responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("electricitate", responseBody.getTipServiciu());
        assertEquals(savedFurnizor.getId(), responseBody.getFurnizorId());
    }

    @Test
    public void testGetServiciuById() {
        // Creează și salvează un furnizor și un serviciu
        Furnizor furnizor = new Furnizor("Enel", "electricitate", "contact@enel.ro");
        Furnizor savedFurnizor = furnizorRepository.save(furnizor);

        Serviciu serviciu = new Serviciu(savedFurnizor, "electricitate");
        Serviciu savedServiciu = serviciuRepository.save(serviciu);

        ResponseEntity<ServiciuDTO> response = restTemplate.getForEntity(
                "/api/servicii/" + savedServiciu.getId(),
                ServiciuDTO.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        ServiciuDTO responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("electricitate", responseBody.getTipServiciu());
        assertEquals(savedFurnizor.getId(), responseBody.getFurnizorId());
    }

    @Test
    public void testGetAllServicii() {
        // Creează și salvează mai multe servicii
        Furnizor furnizor = new Furnizor("Enel", "electricitate", "contact@enel.ro");
        Furnizor savedFurnizor = furnizorRepository.save(furnizor);

        serviciuRepository.save(new Serviciu(savedFurnizor, "electricitate"));
        serviciuRepository.save(new Serviciu(savedFurnizor, "gaze"));

        ResponseEntity<List> response = restTemplate.getForEntity("/api/servicii/list", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testStergeServiciu() {
        // Creează și salvează un furnizor și un serviciu
        Furnizor furnizor = new Furnizor("Enel", "electricitate", "contact@enel.ro");
        Furnizor savedFurnizor = furnizorRepository.save(furnizor);

        Serviciu serviciu = new Serviciu(savedFurnizor, "electricitate");
        Serviciu savedServiciu = serviciuRepository.save(serviciu);

        restTemplate.delete("/api/servicii/" + savedServiciu.getId() + "/sterge");

        Optional<Serviciu> deletedServiciu = serviciuRepository.findById(savedServiciu.getId());
        assertFalse(deletedServiciu.isPresent());
    }
}
