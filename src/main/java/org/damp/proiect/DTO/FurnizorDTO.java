package org.damp.proiect.DTO;

public class FurnizorDTO {
    private Long id;
    private String numeFurnizor;
    private String tipServiciu;
    private String contact;

    // Constructori
    public FurnizorDTO() {}

    public FurnizorDTO(Long id, String numeFurnizor, String tipServiciu, String contact) {
        this.id = id;
        this.numeFurnizor = numeFurnizor;
        this.tipServiciu = tipServiciu;
        this.contact = contact;
    }

    // Getteri și setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeFurnizor() {
        return numeFurnizor;
    }

    public void setNumeFurnizor(String numeFurnizor) {
        this.numeFurnizor = numeFurnizor;
    }

    public String getTipServiciu() {
        return tipServiciu;
    }

    public void setTipServiciu(String tipServiciu) {
        this.tipServiciu = tipServiciu;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
