package org.damp.proiect.DTO;

import java.time.LocalDate;

public class RaportDTO {
    private Long id;
    private String tipRaport;
    private String interval;
    private LocalDate dataGenerare;
    private byte[] document;

    // Constructori
    public RaportDTO() {}

    public RaportDTO(Long id, String tipRaport, String interval, LocalDate dataGenerare, byte[] document) {
        this.id = id;
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
}
