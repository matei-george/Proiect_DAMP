package org.damp.proiect.Controller;

import org.damp.proiect.DTO.FurnizorDTO;
import org.damp.proiect.DTO.FurnizorMapper;
import org.damp.proiect.Model.Furnizor.Furnizor;
import org.damp.proiect.Service.interfete.IFurnizorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/furnizori")
public class FurnizorController {

    @Autowired
    private IFurnizorService furnizorService;

    @GetMapping
    public String furnizoriPage(Model model) {
        List<Furnizor> furnizori = furnizorService.getAllFurnizori();
        System.out.println("Lista furnizorilor transmisă: " + furnizori);
        model.addAttribute("furnizori", furnizori);
        return "furnizori"; // Thymeleaf va căuta furnizori.html
    }

    @GetMapping("/{id}")
    public String vizualizareFurnizor(@PathVariable Long id, Model model) {
        try {
            Furnizor furnizor = furnizorService.getFurnizorById(id);
            System.out.println("Furnizor găsit: " + furnizor);
            model.addAttribute("furnizor", FurnizorMapper.toDTO(furnizor));
            return "vizualizare-furnizor";
        } catch (RuntimeException ex) {
            System.err.println("Eroare: " + ex.getMessage());
            model.addAttribute("errorMessage", ex.getMessage());
            return "error";
        }
    }

    @PostMapping("/creare")
    public String creareFurnizor(
            @RequestParam String numeFurnizor,
            @RequestParam String tipServiciu,
            @RequestParam(required = false) String contact,
            Model model
    ) {
        // Creează și salvează un nou furnizor
        Furnizor furnizor = new Furnizor(numeFurnizor, tipServiciu, contact);
        furnizorService.creareFurnizor(furnizor);

        // Redirecționează către lista furnizorilor
        return "redirect:/furnizori";
    }

    @PostMapping("/{id}/sterge")
    public String stergeFurnizor(@PathVariable Long id) {
        furnizorService.stergeFurnizor(id);
        return "redirect:/furnizori";
    }
}
