package org.damp.proiect.Model.Beneficiari;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.damp.proiect.Model.Contracte.Contract;
import org.damp.proiect.Model.Notificare.Notificare;

import java.util.List;

@Entity
@Table(name = "beneficiari")
public class Beneficiar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_beneficiar")
    private Long id;

    @NotBlank
    private String nume;

    @NotBlank
    private String prenume;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String telefon;

    private String adresa;

    @NotBlank
    private String cnp;

    @NotBlank
    private String parola;

    @OneToMany(mappedBy = "beneficiar", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contract> contracte;

    @OneToMany(mappedBy = "beneficiar", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notificare> notificare;

    // Constructori, Getteri și Setteri
    public Beneficiar() {
    }

    public Beneficiar(String nume, String prenume, String email, String telefon, String adresa, String cnp, String parola) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.telefon = telefon;
        this.adresa = adresa;
        this.cnp = cnp;
        this.parola = parola;
    }

    // Getteri și Setteri pentru toate atributele

    public Long getIdBeneficiar() {
        return id;
    }

    public void setIdBeneficiar(Long idBeneficiar) {
        this.id = idBeneficiar;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

}
