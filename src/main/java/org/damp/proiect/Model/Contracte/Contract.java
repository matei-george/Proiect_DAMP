package org.damp.proiect.Model.Contracte;

import jakarta.persistence.*;
import org.damp.proiect.Model.Beneficiari.Beneficiar;
import org.damp.proiect.Model.Furnizor.Furnizor;
import org.damp.proiect.Model.Notificare.Notificare;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "contracte")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contract")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_beneficiar", nullable = false)
    private Beneficiar beneficiar;

    @ManyToOne
    @JoinColumn(name = "id_furnizor", nullable = false)
    private Furnizor furnizor;

    @Column(name = "tip_serviciu", nullable = false)
    private String tipServiciu;

    @Column(name = "data_incepere", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataIncepere;

    @Column(name = "data_expirare", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataExpirare;

    @Column(name = "valoare_contract")
    private Double valoareContract;

    @Column(name = "documente")
    private String documente;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notificare> notificari;

    @Column(name = "stare", nullable = false)
    @Enumerated(EnumType.STRING)
    private StareContract stare;

    public enum StareContract {
        ACTIV, INACTIV, ANULAT
    }

    // Constructor implicit
    public Contract() {
    }

    // Constructor parametrizat
    public Contract(Beneficiar beneficiar, Furnizor furnizor, String tipServiciu, Date dataIncepere, Date dataExpirare, Double valoareContract, String documente) {
        this.beneficiar = beneficiar;
        this.furnizor = furnizor;
        this.tipServiciu = tipServiciu;
        this.dataIncepere = dataIncepere;
        this.dataExpirare = dataExpirare;
        this.valoareContract = valoareContract;
        this.documente = documente;
        this.stare = StareContract.ACTIV; // Stare implicită
    }

    // Getteri și Setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Beneficiar getBeneficiar() {
        return beneficiar;
    }

    public void setBeneficiar(Beneficiar beneficiar) {
        this.beneficiar = beneficiar;
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

    public Date getDataIncepere() {
        return dataIncepere;
    }

    public void setDataIncepere(Date dataIncepere) {
        this.dataIncepere = dataIncepere;
    }

    public Date getDataExpirare() {
        return dataExpirare;
    }

    public void setDataExpirare(Date dataExpirare) {
        this.dataExpirare = dataExpirare;
    }

    public Double getValoareContract() {
        return valoareContract;
    }

    public void setValoareContract(Double valoareContract) {
        this.valoareContract = valoareContract;
    }

    public String getDocumente() {
        return documente;
    }

    public void setDocumente(String documente) {
        this.documente = documente;
    }

    public List<Notificare> getNotificari() {
        return notificari;
    }

    public void setNotificari(List<Notificare> notificari) {
        this.notificari = notificari;
    }

    public StareContract getStare() {
        return stare;
    }

    public void setStare(StareContract stare) {
        this.stare = stare;
    }

    // Metode suplimentare
    public boolean esteActiv() {
        return this.stare == StareContract.ACTIV;
    }

    public void anuleazaContract() {
        this.stare = StareContract.ANULAT;
    }

    public boolean expiraIn(int zile) {
        long diferenta = dataExpirare.getTime() - new Date().getTime();
        long zileRamase = diferenta / (1000 * 60 * 60 * 24);
        return zileRamase <= zile;
    }

    public void adaugaDocument(String document) {
        if (this.documente == null || this.documente.isEmpty()) {
            this.documente = document;
        } else {
            this.documente += ", " + document;
        }
    }
}
