package org.damp.proiect.Controller;

import org.damp.proiect.DTO.NotificareDTO;
import org.damp.proiect.DTO.NotificareMapper;
import org.damp.proiect.Model.Notificare.Notificare;
import org.damp.proiect.Service.interfete.IBeneficiarService;
import org.damp.proiect.Service.interfete.IContractService;
import org.damp.proiect.Service.interfete.INotificareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/notificari")
public class NotificareController {

    @Autowired
    private INotificareService notificareService;

    @Autowired
    private IBeneficiarService beneficiarService;

    @Autowired
    private IContractService contractService;

    // Afișează pagina notificărilor
    @GetMapping
    public String notificariPage(Model model) {
        List<Notificare> notificari = notificareService.getAllNotificari();
        List<NotificareDTO> notificariDTO = notificari.stream()
                .map(NotificareMapper::toDTO)
                .collect(Collectors.toList());
        model.addAttribute("notificari", notificariDTO);
        return "notificari"; // Thymeleaf va căuta notificari.html în templates/
    }

    // Creare notificare
    @PostMapping("/creare")
    public String creareNotificare(
            @RequestParam String mesaj,
            @RequestParam String dataNotificare,
            @RequestParam String stare,
            Model model
    ) {
        Notificare notificare = new Notificare();
        notificare.setMesaj(mesaj);
        notificare.setDataNotificare(java.sql.Date.valueOf(dataNotificare));
        notificare.setStare(stare);

        notificareService.creareNotificare(notificare);

        return "redirect:/notificari"; // Reîncarcă pagina notificărilor
    }

    // Ștergere notificare
    @GetMapping("/{id}/sterge")
    public String stergeNotificare(@PathVariable Long id) {
        notificareService.stergeNotificare(id);
        return "redirect:/notificari"; // Reîncarcă pagina notificărilor
    }
}
