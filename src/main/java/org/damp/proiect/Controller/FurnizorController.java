package org.damp.proiect.Controller;

import org.damp.proiect.DTO.FurnizorDTO;
import org.damp.proiect.DTO.FurnizorMapper;
import org.damp.proiect.Model.Furnizor.Furnizor;
import org.damp.proiect.Service.interfete.IFurnizorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/furnizori")
public class FurnizorController {

    @Autowired
    private IFurnizorService furnizorService;

    @PostMapping("/creare")
    public ResponseEntity<FurnizorDTO> creareFurnizor(@RequestBody FurnizorDTO furnizorDTO) {
        Furnizor furnizor = FurnizorMapper.toEntity(furnizorDTO);
        Furnizor createdFurnizor = furnizorService.creareFurnizor(furnizor);
        return ResponseEntity.ok(FurnizorMapper.toDTO(createdFurnizor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FurnizorDTO> getFurnizorById(@PathVariable Long id) {
        Furnizor furnizor = furnizorService.getFurnizorById(id);
        return ResponseEntity.ok(FurnizorMapper.toDTO(furnizor));
    }

    @GetMapping("/list")
    public ResponseEntity<List<FurnizorDTO>> getAllFurnizori() {
        List<Furnizor> furnizori = furnizorService.getAllFurnizori();
        List<FurnizorDTO> furnizoriDTO = furnizori.stream()
                .map(FurnizorMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(furnizoriDTO);
    }

    @DeleteMapping("/{id}/sterge")
    public ResponseEntity<Void> stergeFurnizor(@PathVariable Long id) {
        furnizorService.stergeFurnizor(id);
        return ResponseEntity.noContent().build();
    }
}
