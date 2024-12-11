package org.damp.proiect.Repository;

import org.damp.proiect.Model.Serviciu.Serviciu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiciuRepository extends JpaRepository<Serviciu, Long> {
    List<Serviciu> findAll();
    List<Serviciu> findByFurnizorId(Long idFurnizor);
    Optional<Serviciu> findById(Long id);  // Use findById instead of findByIdServiciu
}

