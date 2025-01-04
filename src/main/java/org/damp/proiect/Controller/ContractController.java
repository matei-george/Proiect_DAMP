package org.damp.proiect.Controller;

import org.damp.proiect.DTO.ContractDTO;
import org.damp.proiect.DTO.ContractMapper;
import org.damp.proiect.Model.Contracte.Contract;
import org.damp.proiect.Service.interfete.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contracte")
public class ContractController {

    @Autowired
    private IContractService contractService;

    @PostMapping("/adauga")
    public ResponseEntity<ContractDTO> adaugaContract(@RequestParam Long beneficiarId, @RequestParam Long furnizorId, @RequestBody ContractDTO contractDTO) {
        Contract contract = ContractMapper.toEntity(contractDTO);
        Contract createdContract = contractService.adaugaContract(beneficiarId, furnizorId, contract);
        return ResponseEntity.ok(ContractMapper.toDTO(createdContract));
    }

    @PutMapping("/{id}/modifica")
    public ResponseEntity<ContractDTO> modificaContract(@PathVariable Long id, @RequestBody ContractDTO contractDTO) {
        Contract contract = ContractMapper.toEntity(contractDTO);
        Contract updatedContract = contractService.modificaContract(id, contract);
        return ResponseEntity.ok(ContractMapper.toDTO(updatedContract));
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
        contractService.anuleazaContract(id);
        return ResponseEntity.noContent().build();
    }
}
