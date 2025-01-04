package org.damp.proiect.DTO;

import java.util.Date;

public class NotificareDTO {
    private Long id;
    private Long beneficiarId;
    private Long contractId;
    private String mesaj;
    private Date dataNotificare;
    private String stare;

    // Constructori
    public NotificareDTO() {}

    public NotificareDTO(Long id, Long beneficiarId, Long contractId, String mesaj, Date dataNotificare, String stare) {
        this.id = id;
        this.beneficiarId = beneficiarId;
        this.contractId = contractId;
        this.mesaj = mesaj;
        this.dataNotificare = dataNotificare;
        this.stare = stare;
    }

    // Getteri și Setteri
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

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public Date getDataNotificare() {
        return dataNotificare;
    }

    public void setDataNotificare(Date dataNotificare) {
        this.dataNotificare = dataNotificare;
    }

    public String getStare() {
        return stare;
    }

    public void setStare(String stare) {
        this.stare = stare;
    }
}
