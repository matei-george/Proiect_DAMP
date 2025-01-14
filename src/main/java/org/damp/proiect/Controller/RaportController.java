package org.damp.proiect.Controller;

import org.damp.proiect.DTO.RaportDTO;
import org.damp.proiect.DTO.RaportMapper;
import org.damp.proiect.Model.Raport.Raport;
import org.damp.proiect.Service.interfete.IRaportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller // Schimbat din @RestController în @Controller
@RequestMapping("/rapoarte")
public class RaportController {

    @Autowired
    private IRaportService raportService;

    @GetMapping
    public String rapoartePage(Model model) {
        List<Raport> rapoarte = raportService.getAllRapoarte();
        model.addAttribute("rapoarte", rapoarte);
        return "rapoarte"; // Thymeleaf va căuta rapoarte.html în templates/
    }

    @PostMapping("/creare")
    public ResponseEntity<RaportDTO> creareRaport(@RequestBody RaportDTO raportDTO) {
        Raport raport = RaportMapper.toEntity(raportDTO);
        Raport createdRaport = raportService.creareRaport(raport);
        return ResponseEntity.ok(RaportMapper.toDTO(createdRaport));
    }

    @GetMapping("/{id}")
    public String vizualizareRaport(@PathVariable Long id, Model model) {
        Raport raport = raportService.getRaportById(id);
        RaportDTO raportDTO = RaportMapper.toDTO(raport);
        model.addAttribute("raport", raportDTO);
        return "vizualizare-raport"; // Thymeleaf va căuta vizualizare-raport.html în templates/
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

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadRaport(@PathVariable Long id) {
        Raport raport = raportService.getRaportById(id); // Obține raportul după ID
        byte[] document = raport.getDocument(); // Obține documentul sub formă de byte[]

        // Verifică dacă documentul există
        if (document == null) {
            return ResponseEntity.notFound().build();
        }

        // Creează răspunsul HTTP pentru descărcare
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=Raport-" + id + ".pdf")
                .body(document);
    }
}
