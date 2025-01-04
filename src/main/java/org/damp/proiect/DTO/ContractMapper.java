package org.damp.proiect.DTO;

import org.damp.proiect.DTO.ContractDTO;
import org.damp.proiect.Model.Contracte.Contract;

public class ContractMapper {

    // Conversie din Contract în ContractDTO
    public static ContractDTO toDTO(Contract contract) {
        return new ContractDTO(
                contract.getId(),
                contract.getBeneficiar() != null ? contract.getBeneficiar().getIdBeneficiar() : null,
                contract.getFurnizor() != null ? contract.getFurnizor().getId() : null,
                contract.getTipServiciu(),
                contract.getDataIncepere(),
                contract.getDataExpirare(),
                contract.getValoareContract(),
                contract.getDocumente()
        );
    }

    // Conversie din ContractDTO în Contract
    public static Contract toEntity(ContractDTO dto) {
        Contract contract = new Contract();
        contract.setId(dto.getId());
        contract.setTipServiciu(dto.getTipServiciu());
        contract.setDataIncepere(dto.getDataIncepere());
        contract.setDataExpirare(dto.getDataExpirare());
        contract.setValoareContract(dto.getValoareContract());
        contract.setDocumente(dto.getDocumente());
        return contract;
    }
}
