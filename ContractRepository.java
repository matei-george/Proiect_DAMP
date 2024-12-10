package com.Gestionare.Contracte.demo;
import org.springframework.lang.Contract;

import java.util.ArrayList;
import java.util.List;

public interface ContractRepository {
    void adaugaContract(Contract contract);
    List<Contract> getAllContracts();
    Contract getContractById(String id);
    void stergeContract(String id);
}
