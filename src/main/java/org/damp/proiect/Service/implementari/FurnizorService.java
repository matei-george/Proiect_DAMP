package org.damp.proiect.Service.implementari;

import org.damp.proiect.Model.Furnizor.Furnizor;
import org.damp.proiect.Repository.FurnizorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FurnizorService {

    @Autowired
    private FurnizorRepository furnizorRepository;

    // Creare furnizor
    @Transactional
    public Furnizor creareFurnizor(Furnizor furnizor) {
        return furnizorRepository.save(furnizor);
    }

    // Obținerea furnizorului după ID
    public Furnizor getFurnizorById(Long id) {
        return furnizorRepository.findById(id).orElseThrow(() -> new RuntimeException("Furnizor inexistent!"));
    }

    // Obținerea tuturor furnizorilor
    public List<Furnizor> getAllFurnizori() {
        return furnizorRepository.findAll();
    }

    // Ștergere furnizor
    @Transactional
    public void stergeFurnizor(Long id) {
        if (furnizorRepository.existsById(id)) {
            furnizorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Furnizor inexistent!");
        }
    }
}
