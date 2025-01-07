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
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contracte")
public class ContractController {

    private final BeneficiarRepository beneficiarRepository;
    private final ContractRepository contractRepository;
    private final FurnizorRepository furnizorRepository;

    @Autowired
    public ContractController(BeneficiarRepository beneficiarRepository,
                              ContractRepository contractRepository,
                              FurnizorRepository furnizorRepository) {
        this.beneficiarRepository = beneficiarRepository;
        this.contractRepository = contractRepository;
        this.furnizorRepository = furnizorRepository;
    }


    @Autowired
    private IContractService contractService;

    @PostMapping("/adauga")
    public ResponseEntity<ContractDTO> adaugaContract(@RequestBody ContractDTO contractDTO) {
        Beneficiar beneficiar = beneficiarRepository.findById(contractDTO.getBeneficiarId())
                .orElseThrow(() -> new RuntimeException("Beneficiar inexistent!"));
        Furnizor furnizor = furnizorRepository.findById(contractDTO.getFurnizorId())
                .orElseThrow(() -> new RuntimeException("Furnizor inexistent!"));

        Contract contract = ContractMapper.toEntity(contractDTO, beneficiar, furnizor); // Apel corect
        Contract savedContract = contractRepository.save(contract);

        return ResponseEntity.ok(ContractMapper.toDTO(savedContract));
    }

    @PutMapping("/{id}/modifica")
    public ResponseEntity<ContractDTO> modificaContract(@PathVariable Long id, @RequestBody ContractDTO contractDTO) {
        // Obține beneficiarul
        Beneficiar beneficiar = beneficiarRepository.findById(contractDTO.getBeneficiarId())
                .orElseThrow(() -> new RuntimeException("Beneficiar inexistent!"));

        // Obține furnizorul
        Furnizor furnizor = furnizorRepository.findById(contractDTO.getFurnizorId())
                .orElseThrow(() -> new RuntimeException("Furnizor inexistent!"));

        // Creează entitatea Contract din DTO, Beneficiar și Furnizor
        Contract contract = ContractMapper.toEntity(contractDTO, beneficiar, furnizor);

        // Modifică contractul existent
        Contract updatedContract = contractService.modificaContract(id, contract);

        // Returnează DTO-ul contractului modificat
        return ResponseEntity.ok(ContractMapper.toDTO(updatedContract));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContractDTO> vizualizeazaContract(@PathVariable Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract inexistent!"));
        return ResponseEntity.ok(ContractMapper.toDTO(contract));
    }
    @GetMapping("/active/{beneficiarId}")
    public ResponseEntity<List<ContractDTO>> getContracteActiveByBeneficiar(@PathVariable Long beneficiarId) {
        List<Contract> contracte = contractService.getContracteActiveByBeneficiar(beneficiarId);
        List<ContractDTO> contracteDTO = contracte.stream()
                .map(ContractMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(contracteDTO);
    }

    @GetMapping("/cauta")
    public ResponseEntity<List<ContractDTO>> cautaContracte(
            @RequestParam(required = false) String tipServiciu,
            @RequestParam(required = false) Long furnizorId,
            @RequestParam(required = false) String dataIncepere
    ) {
        List<Contract> contracte = contractService.cautaContracte(tipServiciu, furnizorId, null);
        List<ContractDTO> contracteDTO = contracte.stream()
                .map(ContractMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(contracteDTO);
    }

    @DeleteMapping("/{id}/anuleaza")
    public ResponseEntity<Void> anuleazaContract(@PathVariable Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract inexistent!"));

        contractRepository.delete(contract);
        return ResponseEntity.noContent().build();
    }
}
