package org.damp.proiect.Service.implementari;

import org.damp.proiect.Model.Notificare.Notificare;
import org.damp.proiect.Repository.NotificareRepository;
import org.damp.proiect.Service.interfete.INotificareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificareService implements INotificareService {

    @Autowired
    private NotificareRepository notificareRepository;

    // Creare notificare
    @Transactional
    public Notificare creareNotificare(Notificare notificare) {
        return notificareRepository.save(notificare);
    }

    // Obținerea notificărilor după ID contract
    public List<Notificare> getNotificariByContractId(Long contractId) {
        return notificareRepository.findByContractId(contractId);
    }

    // Obținerea tuturor notificărilor
    public List<Notificare> getAllNotificari() {
        return notificareRepository.findAll();
    }

    // Ștergere notificare
    @Transactional
    public void stergeNotificare(Long id) {
        if (notificareRepository.existsById(id)) {
            notificareRepository.deleteById(id);
        } else {
            throw new RuntimeException("Notificare inexistentă!");
        }
    }
}
