package com.Gestionare.Contracte.demo;

import java.util.List;

public interface ServiciiRepository {
    void adaugaserviciu(Contract contract);
    List<Contract> getAllContracts();
    Contract getContractById(String id);
    void stergeContract(String id);
}

