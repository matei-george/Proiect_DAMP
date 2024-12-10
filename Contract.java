package com.Gestionare.Contracte.demo;
import com.Gestionare.Contracte.demo.Beneficiar;
import jakarta.persistence.*;

public class Contract {
    private String id;
    private String denumire;
    private Beneficiar beneficiar;
    private String dataSemnarii;
    private String durata;

    public Contract(String id, String denumire, Beneficiar beneficiar, String dataSemnarii, String durata) {
        this.id = id;
        this.denumire = denumire;
        this.beneficiar = beneficiar;
        this.dataSemnarii = dataSemnarii;
        this.durata = durata;
    }

    public String getId() {
        return id;
    }

    public String getDenumire() {
        return denumire;
    }

    public Beneficiar getBeneficiar() {
        return beneficiar;
    }

    public String getDataSemnarii() {
        return dataSemnarii;
    }

    public String getDurata() {
        return durata;
    }

    @Override
    public String toString() {
        return "Contract{id='" + id + "', denumire='" + denumire + "', beneficiar=" + beneficiar.getClass() +
                ", dataSemnarii='" + dataSemnarii + "', durata='" + durata + "'}";
    }
}
