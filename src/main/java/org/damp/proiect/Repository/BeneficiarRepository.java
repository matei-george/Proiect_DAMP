package org.damp.proiect.Repository;

import org.damp.proiect.Model.Beneficiari.Beneficiar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeneficiarRepository extends JpaRepository<Beneficiar, Long> {

    // Metode de căutare personalizate
    Optional<Beneficiar> findByEmail(String email);
    Optional<Beneficiar> findByTelefon(String telefon);
    List<Beneficiar> findAll();
}
