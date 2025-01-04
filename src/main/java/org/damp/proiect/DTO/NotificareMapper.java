package org.damp.proiect.DTO;

import org.damp.proiect.DTO.NotificareDTO;
import org.damp.proiect.Model.Beneficiari.Beneficiar;
import org.damp.proiect.Model.Contracte.Contract;
import org.damp.proiect.Model.Notificare.Notificare;

public class NotificareMapper {

    // Conversie din Notificare în NotificareDTO
    public static NotificareDTO toDTO(Notificare notificare) {
        return new NotificareDTO(
                notificare.getId(),
                notificare.getBeneficiar() != null ? notificare.getBeneficiar().getIdBeneficiar() : null,
                notificare.getContract() != null ? notificare.getContract().getId() : null,
                notificare.getMesaj(),
                notificare.getDataNotificare(),
                notificare.getStare()
        );
    }

    // Conversie din NotificareDTO în Notificare
    public static Notificare toEntity(NotificareDTO dto, Beneficiar beneficiar, Contract contract) {
        Notificare notificare = new Notificare();
        notificare.setId(dto.getId());
        notificare.setBeneficiar(beneficiar); // Beneficiar asociat
        notificare.setContract(contract); // Contract asociat
        notificare.setMesaj(dto.getMesaj());
        notificare.setDataNotificare(dto.getDataNotificare());
        notificare.setStare(dto.getStare());
        return notificare;
    }
}
