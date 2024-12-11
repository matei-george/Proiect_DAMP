package org.damp.proiect.Repository;

import org.damp.proiect.Model.Raport.Raport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaportRepository extends JpaRepository<Raport, Long> {
    List<Raport> findAll();
    List<Raport> findByTipRaport(String tipRaport);
    List<Raport> findByInterval(String interval);
}
