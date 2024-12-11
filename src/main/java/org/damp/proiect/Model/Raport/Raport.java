package org.damp.proiect.Model.Raport;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rapoarte")
public class Raport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tip_raport", nullable = false)
    private String tipRaport;

    @Column(name = "interval")
    private String interval;

    @Column(name = "data_generare", nullable = false)
    private LocalDate dataGenerare;

    @Lob
    @Column(name = "document")
    private byte[] document;

    // Constructor implicit
    public Raport() {
    }

    // Constructor parametrizat
    public Raport(String tipRaport, String interval, LocalDate dataGenerare, byte[] document) {
        this.tipRaport = tipRaport;
        this.interval = interval;
        this.dataGenerare = dataGenerare;
        this.document = document;
    }

    // Getteri și setteri

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipRaport() {
        return tipRaport;
    }

    public void setTipRaport(String tipRaport) {
        this.tipRaport = tipRaport;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public LocalDate getDataGenerare() {
        return dataGenerare;
    }

    public void setDataGenerare(LocalDate dataGenerare) {
        this.dataGenerare = dataGenerare;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    // Metodă suplimentară (opțională) pentru a verifica dacă raportul conține un document
    public boolean areDocument() {
        return document != null && document.length > 0;
    }
}
