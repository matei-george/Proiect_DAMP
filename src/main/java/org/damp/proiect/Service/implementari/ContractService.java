package org.damp.proiect.Service.implementari;

import org.damp.proiect.Model.Beneficiari.Beneficiar;
import org.damp.proiect.Model.Contracte.Contract;
import org.damp.proiect.Model.Furnizor.Furnizor;
import org.damp.proiect.Model.Notificare.Notificare;
import org.damp.proiect.Repository.BeneficiarRepository;
import org.damp.proiect.Repository.ContractRepository;
import org.damp.proiect.Repository.FurnizorRepository;
import org.damp.proiect.Repository.NotificareRepository;
import org.damp.proiect.Service.interfete.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ContractService implements IContractService {

    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private BeneficiarRepository beneficiarRepository;

    @Autowired
    private FurnizorRepository furnizorRepository;

    @Autowired
    private NotificareRepository notificareRepository;
    // Creare contract
    @Transactional
    public Contract creareContract(Contract contract) {
        return contractRepository.save(contract);
    }
    // Alte metode

    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract cu ID-ul " + id + " nu a fost găsit!"));
    }

    @Transactional
    public Contract creareContract(Contract contract, Long beneficiarId, Long furnizorId) {
        Beneficiar beneficiar = beneficiarRepository.findById(beneficiarId)
                .orElseThrow(() -> new RuntimeException("Beneficiar inexistent!"));
        Furnizor furnizor = furnizorRepository.findById(furnizorId)
                .orElseThrow(() -> new RuntimeException("Furnizor inexistent!"));

        contract.setBeneficiar(beneficiar); // Asociază beneficiarul
        contract.setFurnizor(furnizor);    // Asociază furnizorul
        return contractRepository.save(contract);
    }

    // Obținerea tuturor contractelor
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    // Actualizare contract
    @Transactional
    public Contract actualizeazaContract(Long id, Contract newContractData) {
        Contract existingContract = getContractById(id);
        existingContract.setTipServiciu(newContractData.getTipServiciu());
        existingContract.setDataIncepere(newContractData.getDataIncepere());
        existingContract.setDataExpirare(newContractData.getDataExpirare());
        existingContract.setValoareContract(newContractData.getValoareContract());
        existingContract.setDocumente(newContractData.getDocumente());
        return contractRepository.save(existingContract);
    }

    @Transactional
    public Contract adaugaContract(Long beneficiarId, Long furnizorId, Contract contract) {
        Beneficiar beneficiar = beneficiarRepository.findById(beneficiarId)
                .orElseThrow(() -> new RuntimeException("Beneficiar inexistent!"));
        Furnizor furnizor = furnizorRepository.findById(furnizorId)
                .orElseThrow(() -> new RuntimeException("Furnizor inexistent!"));

        contract.setBeneficiar(beneficiar);
        contract.setFurnizor(furnizor);
        contract.setStare(Contract.StareContract.ACTIV);
        return contractRepository.save(contract);
    }

    public List<Contract> getContracteActiveByBeneficiar(Long beneficiarId) {
        return contractRepository.findByBeneficiar_Id(beneficiarId).stream()
                .filter(Contract::esteActiv)
                .toList();
    }

    @Transactional
    public Contract modificaContract(Long contractId, Contract newData) {
        Contract existingContract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract inexistent!"));

        existingContract.setTipServiciu(newData.getTipServiciu());
        existingContract.setDataExpirare(newData.getDataExpirare());
        existingContract.setValoareContract(newData.getValoareContract());
        return contractRepository.save(existingContract);
    }
    @Scheduled(cron = "0 0 12 * * ?") // Zilnic la ora 12
    public void genereazaNotificariReinnoire() {
        List<Contract> contracte = contractRepository.findAll();
        for (Contract contract : contracte) {
            if (contract.expiraIn(30) && contract.esteActiv()) {
                Notificare notificare = new Notificare(contract.getBeneficiar(), contract,
                        "Contractul pentru serviciul " + contract.getTipServiciu() +
                                " expiră în mai puțin de 30 de zile.", new Date());
                notificareRepository.save(notificare);
            }
        }
    }
    public List<Contract> cautaContracte(String tipServiciu, Long furnizorId, Date dataIncepere) {
        return contractRepository.findAll().stream()
                .filter(c -> (tipServiciu == null || c.getTipServiciu().equalsIgnoreCase(tipServiciu)) &&
                        (furnizorId == null || c.getFurnizor().getId().equals(furnizorId)) &&
                        (dataIncepere == null || c.getDataIncepere().equals(dataIncepere)))
                .toList();
    }
    @Transactional
    public Contract anuleazaContract(Long contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract inexistent!"));

        contract.setStare(Contract.StareContract.ANULAT);
        return contractRepository.save(contract);
    }

    // Ștergere contract
    @Transactional
    public void stergeContract(Long id) {
        if (contractRepository.existsById(id)) {
            contractRepository.deleteById(id);
        } else {
            throw new RuntimeException("Contract inexistent!");
        }
    }
}
