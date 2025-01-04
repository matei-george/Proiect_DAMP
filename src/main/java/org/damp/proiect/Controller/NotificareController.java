package org.damp.proiect.Controller;

import org.damp.proiect.DTO.NotificareDTO;
import org.damp.proiect.DTO.NotificareMapper;
import org.damp.proiect.Model.Beneficiari.Beneficiar;
import org.damp.proiect.Model.Contracte.Contract;
import org.damp.proiect.Model.Notificare.Notificare;
import org.damp.proiect.Service.interfete.IBeneficiarService;
import org.damp.proiect.Service.interfete.IContractService;
import org.damp.proiect.Service.interfete.INotificareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notificari")
public class NotificareController {

    @Autowired
    private INotificareService notificareService;

    @Autowired
    private IBeneficiarService beneficiarService;

    @Autowired
    private IContractService contractService;

    // Creare notificare
    @PostMapping("/creare")
    public ResponseEntity<NotificareDTO> creareNotificare(
            @RequestBody NotificareDTO notificareDTO,
            @RequestParam Long beneficiarId,
            @RequestParam Long contractId
    ) {
        // Obține beneficiarul și contractul asociate
        Beneficiar beneficiar = beneficiarService.vizualizeazaCont(beneficiarId);
        Contract contract = contractService.getContractById(contractId);

        // Conversie DTO în entitate Notificare
        Notificare notificare = NotificareMapper.toEntity(notificareDTO, beneficiar, contract);

        // Salvare notificare
        Notificare createdNotificare = notificareService.creareNotificare(notificare);

        // Conversie Notificare în DTO pentru răspuns
        return ResponseEntity.ok(NotificareMapper.toDTO(createdNotificare));
    }

    // Obține notificările asociate unui contract
    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<NotificareDTO>> getNotificariByContractId(@PathVariable Long contractId) {
        List<Notificare> notificari = notificareService.getNotificariByContractId(contractId);
        List<NotificareDTO> notificariDTO = notificari.stream()
                .map(NotificareMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(notificariDTO);
    }

    // Obține toate notificările
    @GetMapping("/list")
    public ResponseEntity<List<NotificareDTO>> getAllNotificari() {
        List<Notificare> notificari = notificareService.getAllNotificari();
        List<NotificareDTO> notificariDTO = notificari.stream()
                .map(NotificareMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(notificariDTO);
    }

    // Ștergere notificare
    @DeleteMapping("/{id}/sterge")
    public ResponseEntity<Void> stergeNotificare(@PathVariable Long id) {
        notificareService.stergeNotificare(id);
        return ResponseEntity.noContent().build();
    }
}
