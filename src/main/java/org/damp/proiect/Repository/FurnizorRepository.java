package org.damp.proiect.Repository;

import org.damp.proiect.Model.Furnizor.Furnizor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FurnizorRepository extends JpaRepository<Furnizor, Long> {
    List<Furnizor> findAll();
    Optional<Furnizor> findById(Long id);  // Folosește 'id' în loc de 'idFurnizor'
    List<Furnizor> findByTipServiciu(String tipServiciu);
}
