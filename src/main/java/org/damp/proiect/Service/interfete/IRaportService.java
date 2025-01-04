package org.damp.proiect.Service.interfete;

import org.damp.proiect.Model.Raport.Raport;

import java.util.List;

public interface IRaportService {

    Raport creareRaport(Raport raport);

    List<Raport> getRapoarteByTip(String tipRaport);

    List<Raport> getAllRapoarte();

    void stergeRaport(Long id);

    Raport getRaportById(Long id);
}
