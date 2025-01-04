package org.damp.proiect.DTO;

import java.time.LocalDate;

public class DocumentDTO {
    private Long id;
    private Long contractId;
    private String numeDocument;
    private LocalDate dataIncarcare;
    private String tip;

    // Constructori
    public DocumentDTO() {}

    public DocumentDTO(Long id, Long contractId, String numeDocument, LocalDate dataIncarcare, String tip) {
        this.id = id;
        this.contractId = contractId;
        this.numeDocument = numeDocument;
        this.dataIncarcare = dataIncarcare;
        this.tip = tip;
    }

    // Getteri și setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getNumeDocument() {
        return numeDocument;
    }

    public void setNumeDocument(String numeDocument) {
        this.numeDocument = numeDocument;
    }

    public LocalDate getDataIncarcare() {
        return dataIncarcare;
    }

    public void setDataIncarcare(LocalDate dataIncarcare) {
        this.dataIncarcare = dataIncarcare;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
