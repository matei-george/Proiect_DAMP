package org.damp.proiect.Service.interfete;

import org.damp.proiect.Model.Serviciu.Serviciu;

import java.util.List;

public interface IServiciuService {

    Serviciu adaugaServiciu(Serviciu serviciu);

    Serviciu modificaServiciu(Long serviciuId, Serviciu newData);

    void stergeServiciu(Long serviciuId);

    List<Serviciu> cautaServicii(String tip, Long furnizorId);

    List<Serviciu> getAllServicii();

    Serviciu getServiciuById(Long id);
}
