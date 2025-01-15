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

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/rapoarte")
public class RaportController {

    @Autowired
    private IRaportService raportService;

    @GetMapping
    public String rapoartePage(Model model) {
        List<Raport> rapoarte = raportService.getAllRapoarte();
        model.addAttribute("rapoarte", rapoarte);
        return "rapoarte"; // Returnează rapoarte.html
    }

    @PostMapping("/creare")
    public String creareRaport(
            @RequestParam String tipRaport,
            @RequestParam String interval,
            Model model
    ) {
        byte[] document = ("Raport generat pentru tipul: " + tipRaport + " și intervalul: " + interval).getBytes();

        Raport raport = new Raport();
        raport.setTipRaport(tipRaport);
        raport.setInterval(interval);
        raport.setDataGenerare(LocalDate.now());
        raport.setDocument(document);

        raportService.creareRaport(raport);

        return "redirect:/rapoarte"; // Redirecționează către pagina principală
    }


    @GetMapping("/{id}")
    public String vizualizareRaport(@PathVariable Long id, Model model) {
        Raport raport = raportService.getRaportById(id);
        RaportDTO raportDTO = RaportMapper.toDTO(raport);
        model.addAttribute("raport", raportDTO);
        return "vizualizare-raport"; // Returnează vizualizare-raport.html
    }

    @GetMapping("/list")
    public ResponseEntity<List<RaportDTO>> getAllRapoarte() {
        List<Raport> rapoarte = raportService.getAllRapoarte();
        List<RaportDTO> rapoarteDTO = rapoarte.stream()
                .map(RaportMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(rapoarteDTO);
    }

    @GetMapping("/{id}/sterge")
    public String stergeRaport(@PathVariable Long id) {
        raportService.stergeRaport(id); // Șterge raportul
        return "redirect:/rapoarte"; // Redirecționează către pagina principală
    }
}
