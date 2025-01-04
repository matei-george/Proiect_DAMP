package org.damp.proiect.DTO;

import org.damp.proiect.DTO.BeneficiarDTO;
import org.damp.proiect.Model.Beneficiari.Beneficiar;

public class BeneficiarMapper {

    // Conversie din Beneficiar în BeneficiarDTO
    public static BeneficiarDTO toDTO(Beneficiar beneficiar) {
        return new BeneficiarDTO(
                beneficiar.getIdBeneficiar(),
                beneficiar.getNume(),
                beneficiar.getPrenume(),
                beneficiar.getEmail(),
                beneficiar.getTelefon(),
                beneficiar.getAdresa()
        );
    }

    // Conversie din BeneficiarDTO în Beneficiar
    public static Beneficiar toEntity(BeneficiarDTO dto) {
        Beneficiar beneficiar = new Beneficiar();
        beneficiar.setIdBeneficiar(dto.getId());
        beneficiar.setNume(dto.getNume());
        beneficiar.setPrenume(dto.getPrenume());
        beneficiar.setEmail(dto.getEmail());
        beneficiar.setTelefon(dto.getTelefon());
        beneficiar.setAdresa(dto.getAdresa());
        return beneficiar;
    }
}
