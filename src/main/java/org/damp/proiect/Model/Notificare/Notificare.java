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
    @JoinColumn(name = "id_beneficiar", nullable = false) // Legătura cu Beneficiar
    private Beneficiar beneficiar; // Asigură-te că ai această proprietate

    @ManyToOne
    @JoinColumn(name = "id_contract", nullable = false)
    private Contract contract;

    @Column(name = "mesaj", nullable = false)
    private String mesaj;

    @Column(name = "data_notificare", nullable = false)
    private Date dataNotificare;

    @Column(name = "stare", nullable = false)
    private String stare = "netrimis";

    // Constructori, Getteri și Setteri
    public Notificare() {
    }

    public Notificare(Beneficiar beneficiar, Contract contract, String mesaj, Date dataNotificare) {
        this.beneficiar = beneficiar;
        this.contract = contract;
        this.mesaj = mesaj;
        this.dataNotificare = dataNotificare;
    }

    // Getteri și Setteri pentru toate atributele
}
