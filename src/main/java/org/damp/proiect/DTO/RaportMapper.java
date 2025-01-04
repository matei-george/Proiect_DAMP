package org.damp.proiect.DTO;

import org.damp.proiect.DTO.RaportDTO;
import org.damp.proiect.Model.Raport.Raport;

public class RaportMapper {

    // Conversie din Raport în RaportDTO
    public static RaportDTO toDTO(Raport raport) {
        return new RaportDTO(
                raport.getId(),
                raport.getTipRaport(),
                raport.getInterval(),
                raport.getDataGenerare(),
                raport.getDocument()
        );
    }

    // Conversie din RaportDTO în Raport
    public static Raport toEntity(RaportDTO dto) {
        Raport raport = new Raport();
        raport.setId(dto.getId());
        raport.setTipRaport(dto.getTipRaport());
        raport.setInterval(dto.getInterval());
        raport.setDataGenerare(dto.getDataGenerare());
        raport.setDocument(dto.getDocument());
        return raport;
    }
}
