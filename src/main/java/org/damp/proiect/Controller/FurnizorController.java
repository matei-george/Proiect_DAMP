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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/furnizori")
public class FurnizorController {

    @Autowired
    private IFurnizorService furnizorService;

    @GetMapping
    public String furnizoriPage(Model model) {
        List<Furnizor> furnizori = furnizorService.getAllFurnizori();
        if (furnizori == null) {
            furnizori = List.of(); // Fallback pentru lista goală
        }
        model.addAttribute("furnizori", furnizori);
        return "furnizori"; // Thymeleaf va căuta furnizori.html în templates/
    }

    @GetMapping("/{id}")
    public String vizualizareFurnizor(@PathVariable Long id, Model model) {
        Furnizor furnizor = furnizorService.getFurnizorById(id);
        if (furnizor == null) {
            model.addAttribute("errorMessage", "Furnizorul nu a fost găsit.");
            return "error"; // Pagina de eroare
        }
        model.addAttribute("furnizor", FurnizorMapper.toDTO(furnizor));
        return "vizualizare-furnizor"; // Thymeleaf va căuta vizualizare-furnizor.html
    }

    @PostMapping("/creare")
    @ResponseBody
    public FurnizorDTO creareFurnizor(@RequestBody FurnizorDTO furnizorDTO) {
        Furnizor furnizor = FurnizorMapper.toEntity(furnizorDTO);
        Furnizor createdFurnizor = furnizorService.creareFurnizor(furnizor);
        return FurnizorMapper.toDTO(createdFurnizor);
    }

    @DeleteMapping("/{id}/sterge")
    @ResponseBody
    public void stergeFurnizor(@PathVariable Long id) {
        furnizorService.stergeFurnizor(id);
    }
}
