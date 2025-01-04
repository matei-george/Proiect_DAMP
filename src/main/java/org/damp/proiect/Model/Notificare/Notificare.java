package org.damp.proiect.Model.Notificare;

import jakarta.persistence.*;
import org.damp.proiect.Model.Beneficiari.Beneficiar;
import org.damp.proiect.Model.Contracte.Contract;

import java.util.Date;

@Entity
@Table(name = "notificari")
public class Notificare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificare")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_beneficiar", nullable = false)
    private Beneficiar beneficiar;

    @ManyToOne
    @JoinColumn(name = "id_contract", nullable = false)
    private Contract contract;

    @Column(name = "mesaj", nullable = false)
    private String mesaj;

    @Column(name = "data_notificare", nullable = false)
    private Date dataNotificare;

    @Column(name = "stare", nullable = false)
    private String stare = "netrimis";

    // Constructor implicit
    public Notificare() {}

    // Constructor parametrizat
    public Notificare(Beneficiar beneficiar, Contract contract, String mesaj, Date dataNotificare) {
        this.beneficiar = beneficiar;
        this.contract = contract;
        this.mesaj = mesaj;
        this.dataNotificare = dataNotificare;
    }

    // Getteri și setteri
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

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public Date getDataNotificare() {
        return dataNotificare;
    }

    public void setDataNotificare(Date dataNotificare) {
        this.dataNotificare = dataNotificare;
    }

    public String getStare() {
        return stare;
    }

    public void setStare(String stare) {
        this.stare = stare;
    }
}
