package org.damp.proiect.DTO;

import org.damp.proiect.DTO.FurnizorDTO;
import org.damp.proiect.Model.Furnizor.Furnizor;

public class FurnizorMapper {

    public static FurnizorDTO toDTO(Furnizor furnizor) {
        if (furnizor == null) {
            return null;
        }
        return new FurnizorDTO(
                furnizor.getId(),
                furnizor.getNumeFurnizor(),
                furnizor.getTipServiciu(),
                furnizor.getContact()
        );
    }

    // Conversie din FurnizorDTO în Furnizor
    public static Furnizor toEntity(FurnizorDTO dto) {
        Furnizor furnizor = new Furnizor();
        furnizor.setId(dto.getId());
        furnizor.setNumeFurnizor(dto.getNumeFurnizor());
        furnizor.setTipServiciu(dto.getTipServiciu());
        furnizor.setContact(dto.getContact());
        return furnizor;
    }
}
