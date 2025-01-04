package org.damp.proiect.Service.interfete;

import org.damp.proiect.Model.Contracte.Contract;

import java.util.Date;
import java.util.List;

public interface IContractService {

    Contract adaugaContract(Long beneficiarId, Long furnizorId, Contract contract);

    Contract modificaContract(Long contractId, Contract newData);

    Contract anuleazaContract(Long contractId);

    List<Contract> getContracteActiveByBeneficiar(Long beneficiarId);

    List<Contract> cautaContracte(String tipServiciu, Long furnizorId, Date dataIncepere);

    void genereazaNotificariReinnoire();
    Contract getContractById(Long id);
}
