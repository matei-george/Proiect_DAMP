package org.damp.proiect.Controller;

import org.damp.proiect.DTO.RaportDTO;
import org.damp.proiect.DTO.RaportMapper;
import org.damp.proiect.Model.Raport.Raport;
import org.damp.proiect.Service.interfete.IRaportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rapoarte")
public class RaportController {

    @Autowired
    private IRaportService raportService;

    @PostMapping("/creare")
    public ResponseEntity<RaportDTO> creareRaport(@RequestBody RaportDTO raportDTO) {
        Raport raport = RaportMapper.toEntity(raportDTO);
        Raport createdRaport = raportService.creareRaport(raport);
        return ResponseEntity.ok(RaportMapper.toDTO(createdRaport));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RaportDTO> getRaportById(@PathVariable Long id) {
        Raport raport = raportService.getRaportById(id); // Adaugă această metodă dacă nu există
        return ResponseEntity.ok(RaportMapper.toDTO(raport));
    }

    @GetMapping("/list")
    public ResponseEntity<List<RaportDTO>> getAllRapoarte() {
        List<Raport> rapoarte = raportService.getAllRapoarte();
        List<RaportDTO> rapoarteDTO = rapoarte.stream()
                .map(RaportMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(rapoarteDTO);
    }

    @DeleteMapping("/{id}/sterge")
    public ResponseEntity<Void> stergeRaport(@PathVariable Long id) {
        raportService.stergeRaport(id);
        return ResponseEntity.noContent().build();
    }
}
