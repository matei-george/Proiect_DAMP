package org.damp.proiect.Controller;

import org.damp.proiect.DTO.BeneficiarDTO;
import org.damp.proiect.DTO.BeneficiarMapper;
import org.damp.proiect.Model.Beneficiari.Beneficiar;
import org.damp.proiect.Service.interfete.IBeneficiarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/beneficiari")
public class BeneficiarController {

    @Autowired
    public IBeneficiarService beneficiarService;

    @GetMapping("/list")
    public ResponseEntity<List<BeneficiarDTO>> getAllBeneficiari() {
        List<Beneficiar> beneficiari = beneficiarService.getAllBeneficiari();
        List<BeneficiarDTO> beneficiariDTO = beneficiari.stream()
                .map(BeneficiarMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(beneficiariDTO);
    }

    @PostMapping("/creare-cont")
    public ResponseEntity<BeneficiarDTO> creareCont(@RequestBody BeneficiarDTO beneficiarDTO) {
        Beneficiar beneficiar = beneficiarService.creazaCont(
                beneficiarDTO.getNume(),
                beneficiarDTO.getPrenume(),
                beneficiarDTO.getEmail(),
                beneficiarDTO.getTelefon(),
                beneficiarDTO.getCnp(),
                beneficiarDTO.getParola(),
                beneficiarDTO.getAdresa()
        );
        return ResponseEntity.ok(BeneficiarMapper.toDTO(beneficiar));
    }


    @PutMapping("/{id}/adauga-date")
    public ResponseEntity<BeneficiarDTO> adaugaDateBeneficiar(@PathVariable Long id, @RequestBody BeneficiarDTO beneficiarDTO) {
        Beneficiar beneficiar = beneficiarService.adaugaDateBeneficiar(
                id,
                beneficiarDTO.getNume(),
                beneficiarDTO.getPrenume(),
                null, // CNP-ul nu este inclus în DTO
                beneficiarDTO.getAdresa()
        );
        return ResponseEntity.ok(BeneficiarMapper.toDTO(beneficiar));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeneficiarDTO> vizualizeazaCont(@PathVariable Long id) {
        Beneficiar beneficiar = beneficiarService.vizualizeazaCont(id);
        return ResponseEntity.ok(BeneficiarMapper.toDTO(beneficiar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> stergeCont(@PathVariable Long id) {
        beneficiarService.stergeContBeneficiar(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/istoric-notificari")
    public ResponseEntity<String> getIstoricNotificari(@PathVariable Long id) {
        String istoricNotificari = beneficiarService.getIstoricNotificariById(id);
        return ResponseEntity.ok(istoricNotificari);
    }

    @PostMapping("/{id}/adauga-notificare")
    public ResponseEntity<Void> adaugaNotificareLaIstoric(@PathVariable Long id, @RequestBody String notificare) {
        beneficiarService.adaugaNotificareLaIstoric(id, notificare);
        return ResponseEntity.ok().build();
    }
}
