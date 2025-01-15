package org.damp.proiect.Controller;

import org.damp.proiect.DTO.ContractDTO;
import org.damp.proiect.DTO.ContractMapper;
import org.damp.proiect.Model.Beneficiari.Beneficiar;
import org.damp.proiect.Model.Contracte.Contract;
import org.damp.proiect.Model.Furnizor.Furnizor;
import org.damp.proiect.Repository.BeneficiarRepository;
import org.damp.proiect.Repository.ContractRepository;
import org.damp.proiect.Repository.FurnizorRepository;
import org.damp.proiect.Service.interfete.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contracte")
public class ContractController {

    private final BeneficiarRepository beneficiarRepository;
    private final ContractRepository contractRepository;
    private final FurnizorRepository furnizorRepository;

    @Autowired
    private IContractService contractService;

    @Autowired
    public ContractController(BeneficiarRepository beneficiarRepository,
                              ContractRepository contractRepository,
                              FurnizorRepository furnizorRepository) {
        this.beneficiarRepository = beneficiarRepository;
        this.contractRepository = contractRepository;
        this.furnizorRepository = furnizorRepository;
    }

    @PostMapping("/adauga")
    public String adaugaContract(@RequestParam Long beneficiarId,
                                 @RequestParam Long furnizorId,
                                 @RequestParam String tipServiciu,
                                 @RequestParam String dataIncepere,
                                 @RequestParam String dataExpirare,
                                 @RequestParam Double valoareContract,
                                 @RequestParam String documente,
                                 Model model) {
        try {
            Beneficiar beneficiar = beneficiarRepository.findById(beneficiarId)
                    .orElseThrow(() -> new RuntimeException("Beneficiar inexistent!"));
            Furnizor furnizor = furnizorRepository.findById(furnizorId)
                    .orElseThrow(() -> new RuntimeException("Furnizor inexistent!"));

            ContractDTO contractDTO = new ContractDTO(null, beneficiarId, furnizorId, tipServiciu,
                    java.sql.Date.valueOf(dataIncepere), java.sql.Date.valueOf(dataExpirare),
                    valoareContract, documente);
            Contract contract = ContractMapper.toEntity(contractDTO, beneficiar, furnizor);
            contractRepository.save(contract);

            model.addAttribute("successMessage", "Contractul a fost adăugat cu succes!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Eroare la adăugarea contractului: " + e.getMessage());
        }
        return "redirect:/contracte";
    }

    @PostMapping("/{id}/modifica")
    public String modificaContract(@PathVariable Long id,
                                   @RequestParam Long beneficiarId,
                                   @RequestParam Long furnizorId,
                                   @RequestParam String tipServiciu,
                                   @RequestParam String dataIncepere,
                                   @RequestParam String dataExpirare,
                                   @RequestParam Double valoareContract,
                                   @RequestParam String documente,
                                   Model model) {
        try {
            Beneficiar beneficiar = beneficiarRepository.findById(beneficiarId)
                    .orElseThrow(() -> new RuntimeException("Beneficiar inexistent!"));
            Furnizor furnizor = furnizorRepository.findById(furnizorId)
                    .orElseThrow(() -> new RuntimeException("Furnizor inexistent!"));

            Contract contract = contractRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Contract inexistent!"));
            contract.setTipServiciu(tipServiciu);
            contract.setDataIncepere(java.sql.Date.valueOf(dataIncepere));
            contract.setDataExpirare(java.sql.Date.valueOf(dataExpirare));
            contract.setValoareContract(valoareContract);
            contract.setDocumente(documente);
            contract.setBeneficiar(beneficiar);
            contract.setFurnizor(furnizor);

            contractRepository.save(contract);
            model.addAttribute("successMessage", "Contractul a fost modificat cu succes!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Eroare la modificarea contractului: " + e.getMessage());
        }
        return "redirect:/contracte";
    }

    @GetMapping("/{id}")
    public String vizualizeazaContract(@PathVariable Long id, Model model) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract inexistent!"));
        model.addAttribute("contract", contract);
        return "vizualizare-contract"; // Thymeleaf va căuta vizualizare-contract.html
    }
    @GetMapping("/{id}/modifica")
    public String preiaContractPentruModificare(@PathVariable Long id, Model model) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contractul cu ID-ul " + id + " nu există!"));
        System.out.println("Contract returnat: " + contract);
        model.addAttribute("contract", contract);
        return "contracte"; // Thymeleaf va încărca templates/contracte.html
    }

    @PostMapping("/{id}/anuleaza")
    public String anuleazaContract(@PathVariable Long id, Model model) {
        try {
            Contract contract = contractRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Contract inexistent!"));
            contractRepository.delete(contract);

            model.addAttribute("successMessage", "Contractul a fost anulat cu succes!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Eroare la anularea contractului: " + e.getMessage());
        }
        return "redirect:/contracte";
    }
    @GetMapping
    public String contractePage(Model model) {
        List<Contract> contracte = contractService.getAllContracts();
        if (contracte == null || contracte.isEmpty()) {
            model.addAttribute("message", "Nu există contracte disponibile.");
        }
        model.addAttribute("contracte", contracte);
        return "contracte"; // Thymeleaf va căuta templates/contracte.html
    }
}
