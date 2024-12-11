package org.damp.proiect.Service.implementari;

import org.damp.proiect.Model.Beneficiari.Beneficiar;
import org.damp.proiect.Repository.BeneficiarRepository;
import org.damp.proiect.Service.interfete.IBeneficiarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiarService implements IBeneficiarService {

    @Autowired
    private BeneficiarRepository beneficiarRepository;

    // Creare cont beneficiar
    @Transactional
    @Override
    public Beneficiar creazaCont(String email, String telefon, String parola) {
        if (beneficiarRepository.findByEmail(email).isPresent() || beneficiarRepository.findByTelefon(telefon).isPresent()) {
            throw new RuntimeException("Email sau numar de telefon deja utilizat!");
        }

        Beneficiar beneficiar = new Beneficiar();
        beneficiar.setEmail(email);
        beneficiar.setTelefon(telefon);
        beneficiar.setParola(parola);
        beneficiarRepository.save(beneficiar);
        return beneficiar;
    }

    // Adăugare date beneficiar (după crearea contului)
    @Transactional
    @Override
    public Beneficiar adaugaDateBeneficiar(Long idBeneficiar, String nume, String prenume, String cnp, String adresa) {
        Optional<Beneficiar> optionalBeneficiar = beneficiarRepository.findById(idBeneficiar);
        if (optionalBeneficiar.isPresent()) {
            Beneficiar beneficiar = optionalBeneficiar.get();
            beneficiar.setNume(nume);
            beneficiar.setPrenume(prenume);
            beneficiar.setCnp(cnp);
            beneficiar.setAdresa(adresa);
            beneficiarRepository.save(beneficiar);
            return beneficiar;
        } else {
            throw new RuntimeException("Beneficiar inexistent!");
        }
    }

    // Modificare date beneficiar
    @Transactional
    @Override
    public Beneficiar modificaDateBeneficiar(Long idBeneficiar, String nume, String prenume, String telefon, String adresa) {
        Optional<Beneficiar> optionalBeneficiar = beneficiarRepository.findById(idBeneficiar);
        if (optionalBeneficiar.isPresent()) {
            Beneficiar beneficiar = optionalBeneficiar.get();
            beneficiar.setNume(nume);
            beneficiar.setPrenume(prenume);
            beneficiar.setTelefon(telefon);
            beneficiar.setAdresa(adresa);
            beneficiarRepository.save(beneficiar);
            return beneficiar;
        } else {
            throw new RuntimeException("Beneficiar inexistent!");
        }
    }

    // Ștergere cont beneficiar
    @Transactional
    @Override
    public void stergeContBeneficiar(Long idBeneficiar) {
        Optional<Beneficiar> optionalBeneficiar = beneficiarRepository.findById(idBeneficiar);
        if (optionalBeneficiar.isPresent()) {
            beneficiarRepository.delete(optionalBeneficiar.get());
        } else {
            throw new RuntimeException("Beneficiar inexistent!");
        }
    }

    // Vizualizare cont beneficiar
    @Override
    public Beneficiar vizualizeazaCont(Long idBeneficiar) {
        Optional<Beneficiar> optionalBeneficiar = beneficiarRepository.findById(idBeneficiar);
        if (optionalBeneficiar.isPresent()) {
            return optionalBeneficiar.get();
        } else {
            throw new RuntimeException("Beneficiar inexistent!");
        }
    }

    // Deconectare beneficiar
    @Override
    public void deconecteazaBeneficiar(Long idBeneficiar) {
        // Poți implementa logica pentru gestionarea sesiunii sau autentificării
        System.out.println("Beneficiarul cu ID-ul " + idBeneficiar + " s-a deconectat.");
    }

    // Obținerea tuturor beneficiarilor
    @Override
    public List<Beneficiar> getAllBeneficiari() {
        return beneficiarRepository.findAll();
    }
}
