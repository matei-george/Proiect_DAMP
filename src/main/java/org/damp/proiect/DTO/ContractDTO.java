package org.damp.proiect.DTO;

import java.util.Date;

public class ContractDTO {
    private Long id;
    private Long beneficiarId;
    private Long furnizorId;
    private String tipServiciu;
    private Date dataIncepere;
    private Date dataExpirare;
    private Double valoareContract;
    private String documente;

    // Constructori
    public ContractDTO() {}

    public ContractDTO(Long id, Long beneficiarId, Long furnizorId, String tipServiciu, Date dataIncepere, Date dataExpirare, Double valoareContract, String documente) {
        this.id = id;
        this.beneficiarId = beneficiarId;
        this.furnizorId = furnizorId;
        this.tipServiciu = tipServiciu;
        this.dataIncepere = dataIncepere;
        this.dataExpirare = dataExpirare;
        this.valoareContract = valoareContract;
        this.documente = documente;
    }

    // Getteri și setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBeneficiarId() {
        return beneficiarId;
    }

    public void setBeneficiarId(Long beneficiarId) {
        this.beneficiarId = beneficiarId;
    }

    public Long getFurnizorId() {
        return furnizorId;
    }

    public void setFurnizorId(Long furnizorId) {
        this.furnizorId = furnizorId;
    }

    public String getTipServiciu() {
        return tipServiciu;
    }

    public void setTipServiciu(String tipServiciu) {
        this.tipServiciu = tipServiciu;
    }

    public Date getDataIncepere() {
        return dataIncepere;
    }

    public void setDataIncepere(Date dataIncepere) {
        this.dataIncepere = dataIncepere;
    }

    public Date getDataExpirare() {
        return dataExpirare;
    }

    public void setDataExpirare(Date dataExpirare) {
        this.dataExpirare = dataExpirare;
    }

    public Double getValoareContract() {
        return valoareContract;
    }

    public void setValoareContract(Double valoareContract) {
        this.valoareContract = valoareContract;
    }

    public String getDocumente() {
        return documente;
    }

    public void setDocumente(String documente) {
        this.documente = documente;
    }
}
