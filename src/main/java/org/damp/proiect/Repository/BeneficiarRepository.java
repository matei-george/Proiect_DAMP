package org.damp.proiect.Repository;

import org.damp.proiect.Model.Beneficiari.Beneficiar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeneficiarRepository extends JpaRepository<Beneficiar, Long> {

    // Metode de căutare personalizate
    Optional<Beneficiar> findByEmail(String email);
    Optional<Beneficiar> findByTelefon(String telefon);
    List<Beneficiar> findAll();

    @Query("SELECT b.istoricNotificari FROM Beneficiar b WHERE b.id = :id")
    String findIstoricNotificariById(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Beneficiar b SET b.istoricNotificari = :istoricNotificari WHERE b.id = :id")
    void updateIstoricNotificari(@Param("id") Long id, @Param("istoricNotificari") String istoricNotificari);
}
