package org.damp.proiect.Service.implementari;

import org.damp.proiect.Model.Serviciu.Serviciu;
import org.damp.proiect.Repository.ServiciuRepository;
import org.damp.proiect.Service.interfete.IServiciuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiciuService implements IServiciuService {

    @Autowired
    private ServiciuRepository serviciuRepository;

    // Creare serviciu
    @Transactional
    public Serviciu creareServiciu(Serviciu serviciu) {
        return serviciuRepository.save(serviciu);
    }

    // Obținerea serviciilor după ID furnizor
    public List<Serviciu> getServiciiByFurnizorId(Long furnizorId) {
        return serviciuRepository.findByFurnizorId(furnizorId);
    }

    // Obținerea tuturor serviciilor
    public List<Serviciu> getAllServicii() {
        return serviciuRepository.findAll();
    }
    public Serviciu getServiciuById(Long id) {
        return serviciuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviciu cu ID-ul " + id + " nu a fost găsit!"));
    }
    // Ștergere serviciu
    @Transactional
    public void stergeServiciu(Long id) {
        if (serviciuRepository.existsById(id)) {
            serviciuRepository.deleteById(id);
        } else {
            throw new RuntimeException("Serviciu inexistent!");
        }
    }
    @Transactional
    public Serviciu adaugaServiciu(Serviciu serviciu) {
        if (serviciuRepository.findByFurnizorId(serviciu.getFurnizor().getId())
                .stream()
                .anyMatch(s -> s.getTipServiciu().equalsIgnoreCase(serviciu.getTipServiciu()))) {
            throw new RuntimeException("Serviciul există deja pentru acest furnizor!");
        }
        return serviciuRepository.save(serviciu);
    }
    @Transactional
    public Serviciu modificaServiciu(Long serviciuId, Serviciu newData) {
        Serviciu serviciu = serviciuRepository.findById(serviciuId)
                .orElseThrow(() -> new RuntimeException("Serviciu inexistent!"));

        serviciu.setTipServiciu(newData.getTipServiciu());
        serviciu.setFurnizor(newData.getFurnizor());
        return serviciuRepository.save(serviciu);
    }
    public List<Serviciu> cautaServicii(String tip, Long furnizorId) {
        return serviciuRepository.findAll().stream()
                .filter(s -> (tip == null || s.getTipServiciu().equalsIgnoreCase(tip)) &&
                        (furnizorId == null || s.getFurnizor().getId().equals(furnizorId)))
                .toList();
    }

}
