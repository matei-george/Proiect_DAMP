package org.damp.proiect.Service.implementari;

import org.damp.proiect.Model.Furnizor.Furnizor;
import org.damp.proiect.Repository.FurnizorRepository;
import org.damp.proiect.Service.interfete.IFurnizorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FurnizorService implements IFurnizorService {

    @Autowired
    private FurnizorRepository furnizorRepository;

    @Transactional
    public Furnizor creareFurnizor(Furnizor furnizor) {
        return furnizorRepository.save(furnizor);
    }

    public Furnizor getFurnizorById(Long id) {
        return furnizorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Furnizorul cu ID-ul " + id + " nu există!"));
    }

    public List<Furnizor> getAllFurnizori() {
        List<Furnizor> furnizori = furnizorRepository.findAll();
        System.out.println("Furnizori găsiți: " + furnizori);
        return furnizori;
    }

    @Transactional
    public void stergeFurnizor(Long id) {
        if (furnizorRepository.existsById(id)) {
            furnizorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Furnizorul cu ID-ul " + id + " nu există!");
        }
    }
}
