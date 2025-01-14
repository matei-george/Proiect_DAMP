package org.damp.proiect.Controller;

import org.damp.proiect.DTO.BeneficiarDTO;
import org.damp.proiect.DTO.BeneficiarMapper;
import org.damp.proiect.Model.Beneficiari.Beneficiar;
import org.damp.proiect.Service.interfete.IBeneficiarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/beneficiari")
public class BeneficiarController {

    @Autowired
    public IBeneficiarService beneficiarService;

    // Afișează pagina de Beneficiari cu Thymeleaf
    @GetMapping
    public String beneficiariPage(Model model) {
        List<Beneficiar> beneficiari = beneficiarService.getAllBeneficiari();
        model.addAttribute("beneficiari", beneficiari != null ? beneficiari : List.of());
        return "beneficiari"; // Returnează beneficiari.html
    }
    // Endpoint pentru crearea unui beneficiar (folosit de formularul HTML)
    @PostMapping("/creare-cont")
    public String creareBeneficiar(
            @RequestParam String nume,
            @RequestParam String prenume,
            @RequestParam String email,
            @RequestParam String telefon,
            @RequestParam String cnp,
            @RequestParam String parola,
            @RequestParam String adresa) {
        beneficiarService.creazaCont(nume, prenume, email, telefon, cnp, parola, adresa);
        return "redirect:/beneficiari"; // Redirecționează către lista beneficiarilor după crearea cu succes
    }

    @PostMapping("/adauga-date")
    public String adaugaDateBeneficiar(
            @RequestParam Long id,
            @RequestParam(required = false) String nume,
            @RequestParam(required = false) String prenume,
            @RequestParam(required = false) String telefon,
            @RequestParam(required = false) String adresa) {
        try {
            beneficiarService.modificaDateBeneficiar(id, nume, prenume, telefon, adresa);
        } catch (RuntimeException e) {
            return "redirect:/beneficiari?error=BeneficiarInexistent";
        }
        return "redirect:/beneficiari";
    }

    // Endpoint pentru vizualizarea unui beneficiar (HTML)
    @GetMapping("/{id}")
    public String vizualizeazaBeneficiar(@PathVariable Long id, Model model) {
        Beneficiar beneficiar = beneficiarService.vizualizeazaCont(id);
        model.addAttribute("beneficiar", beneficiar);
        return "vizualizare-beneficiar"; // Returnează o pagină separată pentru vizualizare
    }

    // Endpoint pentru ștergerea unui beneficiar (acționat din link HTML)
    @GetMapping("/{id}/sterge")
    public String stergeCont(@PathVariable Long id) {
        beneficiarService.stergeContBeneficiar(id);
        return "redirect:/beneficiari"; // Redirecționează către lista beneficiarilor după ștergere
    }
}
