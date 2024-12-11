package org.damp.proiect.Model.Serviciu;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.damp.proiect.Model.Furnizor.Furnizor;

@Entity
@Table(name = "servicii")
public class Serviciu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_furnizor", nullable = false)
    private Furnizor furnizor;

    @NotBlank
    @Column(name = "tip_serviciu", nullable = false)
    private String tipServiciu;

    // Constructor implicit
    public Serviciu() {
    }

    // Constructor parametrizat
    public Serviciu(Furnizor furnizor, String tipServiciu) {
        this.furnizor = furnizor;
        this.tipServiciu = tipServiciu;
    }

    // Getteri și setteri

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Furnizor getFurnizor() {
        return furnizor;
    }

    public void setFurnizor(Furnizor furnizor) {
        this.furnizor = furnizor;
    }

    public String getTipServiciu() {
        return tipServiciu;
    }

    public void setTipServiciu(String tipServiciu) {
        this.tipServiciu = tipServiciu;
    }

    // Metodă suplimentară (opțională) pentru a obține detalii complete despre serviciu
    public String getDetaliiServiciu() {
        return "Serviciu: " + tipServiciu + ", Furnizor: " + furnizor.getNumeFurnizor();
    }
}
