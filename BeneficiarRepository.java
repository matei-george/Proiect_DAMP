package com.Gestionare.Contracte.demo;

import java.util.List;

public interface BeneficiarRepository {
    void adaugaBeneficiar(Beneficiar beneficiar);
    List<Beneficiar> getAllBeneficiari();
    Beneficiar getBeneficiarById(String id);
}

