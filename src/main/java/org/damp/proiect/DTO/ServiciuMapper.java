package org.damp.proiect.DTO;

import org.damp.proiect.DTO.ServiciuDTO;
import org.damp.proiect.Model.Serviciu.Serviciu;

public class ServiciuMapper {

    // Conversie din Serviciu în ServiciuDTO
    public static ServiciuDTO toDTO(Serviciu serviciu) {
        return new ServiciuDTO(
                serviciu.getId(),
                serviciu.getFurnizor() != null ? serviciu.getFurnizor().getId() : null,
                serviciu.getTipServiciu()
        );
    }

    // Conversie din ServiciuDTO în Serviciu
    public static Serviciu toEntity(ServiciuDTO dto) {
        Serviciu serviciu = new Serviciu();
        serviciu.setId(dto.getId());
        serviciu.setTipServiciu(dto.getTipServiciu());
        return serviciu;
    }
}
