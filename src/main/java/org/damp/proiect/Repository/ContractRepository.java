package org.damp.proiect.Repository;

import org.damp.proiect.Model.Contracte.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findAll();
    List<Contract> findByBeneficiarId(Long id);
    List<Contract> findByFurnizorId(Long idFurnizor);
    List<Contract> findByTipServiciu(String tipServiciu);

    // Corrected method for finding by id
    Optional<Contract> findById(Long id);

    List<Contract> findByBeneficiar_Id(Long id);
}
