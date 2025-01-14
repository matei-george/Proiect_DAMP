package org.damp.proiect.Controller;

import org.damp.proiect.DTO.ServiciuDTO;
import org.damp.proiect.DTO.ServiciuMapper;
import org.damp.proiect.Model.Furnizor.Furnizor;
import org.damp.proiect.Model.Serviciu.Serviciu;
import org.damp.proiect.Service.interfete.IFurnizorService;
import org.damp.proiect.Service.interfete.IServiciuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/servicii")
public class ServiciuController {

    @Autowired
    private IServiciuService serviciuService;

    @Autowired
    private IFurnizorService furnizorService;

    @GetMapping
    public String serviciiPage(Model model) {
        List<Serviciu> servicii = serviciuService.getAllServicii();
        model.addAttribute("servicii", servicii);
        return "servicii";
    }

    @PostMapping("/adauga")
    public ResponseEntity<ServiciuDTO> adaugaServiciu(
            @RequestBody ServiciuDTO serviciuDTO,
            @RequestParam Long furnizorId
    ) {
        Furnizor furnizor = furnizorService.getFurnizorById(furnizorId);
        Serviciu serviciu = ServiciuMapper.toEntity(serviciuDTO);
        serviciu.setFurnizor(furnizor);
        Serviciu createdServiciu = serviciuService.adaugaServiciu(serviciu);
        return ResponseEntity.ok(ServiciuMapper.toDTO(createdServiciu));
    }

    @GetMapping("/{id}")
    public String vizualizareServiciu(@PathVariable Long id, Model model) {
        Serviciu serviciu = serviciuService.getServiciuById(id);
        ServiciuDTO serviciuDTO = ServiciuMapper.toDTO(serviciu);
        model.addAttribute("serviciu", serviciuDTO);
        return "vizualizare-serviciu";
    }

    @GetMapping("/list")
    public ResponseEntity<List<ServiciuDTO>> getAllServicii() {
        List<Serviciu> servicii = serviciuService.getAllServicii();
        List<ServiciuDTO> serviciiDTO = servicii.stream()
                .map(ServiciuMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(serviciiDTO);
    }

    @DeleteMapping("/{id}/sterge")
    public ResponseEntity<Void> stergeServiciu(@PathVariable Long id) {
        serviciuService.stergeServiciu(id);
        return ResponseEntity.noContent().build();
    }
}
