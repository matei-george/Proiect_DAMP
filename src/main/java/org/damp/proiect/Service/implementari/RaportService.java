package org.damp.proiect.Service.implementari;

import org.damp.proiect.Model.Raport.Raport;
import org.damp.proiect.Repository.RaportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RaportService {

    @Autowired
    private RaportRepository raportRepository;

    // Creare raport
    @Transactional
    public Raport creareRaport(Raport raport) {
        return raportRepository.save(raport);
    }

    // Obținerea rapoartelor după tip
    public List<Raport> getRapoarteByTip(String tipRaport) {
        return raportRepository.findByTipRaport(tipRaport);
    }

    // Obținerea tuturor rapoartelor
    public List<Raport> getAllRapoarte() {
        return raportRepository.findAll();
    }
    public Raport getRaportById(Long id) {
        return raportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raport cu ID-ul " + id + " nu a fost găsit!"));
    }
    // Ștergere raport
    @Transactional
    public void stergeRaport(Long id) {
        if (raportRepository.existsById(id)) {
            raportRepository.deleteById(id);
        } else {
            throw new RuntimeException("Raport inexistent!");
        }
    }
}
