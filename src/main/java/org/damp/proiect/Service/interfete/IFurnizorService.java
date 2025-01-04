package org.damp.proiect.Service.interfete;

import org.damp.proiect.Model.Furnizor.Furnizor;

import java.util.List;

public interface IFurnizorService {

    Furnizor creareFurnizor(Furnizor furnizor);

    Furnizor getFurnizorById(Long id);

    List<Furnizor> getAllFurnizori();

    void stergeFurnizor(Long id);
}
