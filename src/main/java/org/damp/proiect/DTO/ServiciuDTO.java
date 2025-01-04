package org.damp.proiect.DTO;

public class ServiciuDTO {
    private Long id;
    private Long furnizorId;
    private String tipServiciu;

    // Constructori
    public ServiciuDTO() {}

    public ServiciuDTO(Long id, Long furnizorId, String tipServiciu) {
        this.id = id;
        this.furnizorId = furnizorId;
        this.tipServiciu = tipServiciu;
    }

    // Getteri și setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
