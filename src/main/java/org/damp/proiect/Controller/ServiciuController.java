package org.damp.proiect.Controller;

import org.damp.proiect.DTO.ServiciuDTO;
import org.damp.proiect.DTO.ServiciuMapper;
import org.damp.proiect.Model.Furnizor.Furnizor;
import org.damp.proiect.Model.Serviciu.Serviciu;
import org.damp.proiect.Service.interfete.IFurnizorService;
import org.damp.proiect.Service.interfete.IServiciuService;
import org.springframework.beans.factory.annotation.Autowired;
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
        model.addAttribute("furnizori", furnizorService.getAllFurnizori());
        return "servicii"; // Thymeleaf va căuta serviciile în șablonul `servicii.html`.
    }

    @PostMapping("/adauga")
    public String adaugaServiciu(
            @RequestParam String tipServiciu,
            @RequestParam Long furnizorId,
            Model model
    ) {
        // Obține furnizorul asociat
        Furnizor furnizor = furnizorService.getFurnizorById(furnizorId);
        if (furnizor == null) {
            model.addAttribute("error", "Furnizorul nu există.");
            return "servicii"; // Redirecționează către pagina serviciilor cu un mesaj de eroare
        }

        // Creează serviciul
        Serviciu serviciu = new Serviciu(furnizor, tipServiciu);
        serviciuService.adaugaServiciu(serviciu);

        return "redirect:/servicii"; // Redirecționează către lista serviciilor
    }

    @GetMapping("/{id}")
    public String vizualizareServiciu(@PathVariable Long id, Model model) {
        Serviciu serviciu = serviciuService.getServiciuById(id);
        model.addAttribute("serviciu", serviciu);
        return "vizualizare-serviciu"; // Thymeleaf va căuta șablonul `vizualizare-serviciu.html`.
    }

    @PostMapping("/{id}/sterge")
    public String stergeServiciu(@PathVariable Long id) {
        serviciuService.stergeServiciu(id);
        return "redirect:/servicii";
    }
}
