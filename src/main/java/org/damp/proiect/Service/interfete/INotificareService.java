package org.damp.proiect.Service.interfete;

import org.damp.proiect.Model.Notificare.Notificare;

import java.util.List;

public interface INotificareService {

    Notificare creareNotificare(Notificare notificare);

    List<Notificare> getNotificariByContractId(Long contractId);

    List<Notificare> getAllNotificari();

    void stergeNotificare(Long id);
}
