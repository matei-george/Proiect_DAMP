package org.damp.proiect.Repository;

import org.damp.proiect.Model.Notificare.Notificare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificareRepository extends JpaRepository<Notificare, Long> {
    List<Notificare> findAll();
    List<Notificare> findByContractId(Long idContract);
    List<Notificare> findByStare(String stare);
}
