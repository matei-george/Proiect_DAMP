package org.damp.proiect.Service.interfete;

import org.damp.proiect.Model.Beneficiari.Beneficiar;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IBeneficiarService {

    // Creare cont beneficiar
    @Transactional
    Beneficiar creazaCont(String nume, String prenume, String email, String telefon, String cnp, String parola, String adresa);

    Beneficiar creazaCont(String email, String telefon, String parola);

    // Adăugare date beneficiar
    Beneficiar adaugaDateBeneficiar(Long idBeneficiar, String nume, String prenume, String cnp, String adresa);

    // Modificare date beneficiar
    Beneficiar modificaDateBeneficiar(Long idBeneficiar, String nume, String prenume, String telefon, String adresa);

    // Ștergere cont beneficiar
    void stergeContBeneficiar(Long idBeneficiar);

    // Vizualizare cont beneficiar
    Beneficiar vizualizeazaCont(Long idBeneficiar);

    // Deconectare beneficiar
    void deconecteazaBeneficiar(Long idBeneficiar);

    // Obținerea tuturor beneficiarilor
    List<Beneficiar> getAllBeneficiari();

    // Obținerea istoricului notificărilor
    String getIstoricNotificariById(Long id);

    // Adăugare notificare la istoric
    void adaugaNotificareLaIstoric(Long id, String notificare);

}
