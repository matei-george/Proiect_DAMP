package org.damp.proiect.DTO;

import org.damp.proiect.DTO.BeneficiarDTO;
import org.damp.proiect.Model.Beneficiari.Beneficiar;

public class BeneficiarMapper {

    public static BeneficiarDTO toDTO(Beneficiar beneficiar) {
        BeneficiarDTO dto = new BeneficiarDTO();
        dto.setNume(beneficiar.getNume());
        dto.setPrenume(beneficiar.getPrenume());
        dto.setEmail(beneficiar.getEmail());
        dto.setTelefon(beneficiar.getTelefon());
        dto.setCnp(beneficiar.getCnp());
        dto.setParola(beneficiar.getParola());
        dto.setAdresa(beneficiar.getAdresa());
        return dto;
    }

    public static Beneficiar toEntity(BeneficiarDTO dto) {
        Beneficiar beneficiar = new Beneficiar();
        beneficiar.setNume(dto.getNume());
        beneficiar.setPrenume(dto.getPrenume());
        beneficiar.setEmail(dto.getEmail());
        beneficiar.setTelefon(dto.getTelefon());
        beneficiar.setCnp(dto.getCnp());
        beneficiar.setParola(dto.getParola());
        beneficiar.setAdresa(dto.getAdresa());
        return beneficiar;
    }
}
