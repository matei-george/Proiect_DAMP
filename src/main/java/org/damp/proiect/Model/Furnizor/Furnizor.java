package org.damp.proiect.Model.Furnizor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "furnizori")
public class Furnizor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nume_furnizor", nullable = false)
    private String numeFurnizor;

    @NotBlank
    @Column(name = "tip_serviciu", nullable = false)
    private String tipServiciu;

    @Column(name = "contact")
    private String contact;

    // Constructor implicit
    public Furnizor() {
    }

    // Constructor parametrizat
    public Furnizor(String numeFurnizor, String tipServiciu, String contact) {
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

    // Metodă suplimentară (opțională) pentru a verifica dacă furnizorul oferă un anumit serviciu
    public boolean oferaServiciu(String serviciu) {
        return this.tipServiciu.equalsIgnoreCase(serviciu);
    }
}
