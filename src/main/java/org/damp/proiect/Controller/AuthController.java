package org.damp.proiect.Controller;

import org.damp.proiect.Model.Beneficiari.Beneficiar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    // Afișează pagina de autentificare
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Thymeleaf va căuta login.html în `src/main/resources/templates/`
    }

    // Afișează pagina de înregistrare
    @GetMapping("/register")
    public String registerPage(Model model) {
        // Adaugă un obiect gol pentru formularul de înregistrare
        model.addAttribute("beneficiar", new Beneficiar());
        return "register"; // Thymeleaf va căuta register.html în `src/main/resources/templates/`
    }

    // Gestionează cererile POST de la formularul de înregistrare
    @PostMapping("/register")
    public String processRegister(Beneficiar beneficiar) {
        // Logica pentru a salva beneficiarul în baza de date (simulat aici)
        System.out.println("Nume: " + beneficiar.getNume());
        System.out.println("Prenume: " + beneficiar.getPrenume());
        System.out.println("Email: " + beneficiar.getEmail());
        System.out.println("Parolă: " + beneficiar.getParola());

        // Poți adăuga logica pentru a salva beneficiarul în baza de date folosind un serviciu
        // beneficiarService.save(beneficiar);

        // Redirecționează către pagina de login după înregistrare
        return "redirect:/login";
    }
}