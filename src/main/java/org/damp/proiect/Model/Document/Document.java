package org.damp.proiect.Model.Document;

import jakarta.persistence.*;
import org.damp.proiect.Model.Contracte.Contract;

import java.time.LocalDate;

@Entity
@Table(name = "documente")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_contract", nullable = false)
    private Contract contract;

    @Column(name = "nume_document", nullable = false)
    private String numeDocument;

    @Column(name = "data_incarcare", nullable = false)
    private LocalDate dataIncarcare;

    @Column(name = "tip")
    private String tip;

    // Constructor implicit
    public Document() {
    }

    // Constructor parametrizat
    public Document(Contract contract, String numeDocument, LocalDate dataIncarcare, String tip) {
        this.contract = contract;
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

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
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

    // Metode suplimentare (opțional)
    public boolean isDocumentRecent() {
        return dataIncarcare.isAfter(LocalDate.now().minusMonths(1)); // Verifică dacă documentul a fost încărcat în ultima lună
    }
}
