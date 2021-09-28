package domain;

import java.sql.Date;
import java.util.ArrayList;

public class OVChipkaart {
    private int kaartNummer;
    private Date geldigTot;
    private int klasse;
    private double saldo;
    private int reizigerId;

    private Reiziger reiziger;
    private OvChipkaartProduct ovChipkaartProduct;


    public OVChipkaart(int kaartNummer, Date geldigTot, int klasse, double saldo, int reizigerId) {
        this.kaartNummer = kaartNummer;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reizigerId = reizigerId;
    }

    public OVChipkaart(int kaartNummer, Date geldigTot, int klasse, double saldo, int reizigerId, Reiziger reiziger) {
        this.kaartNummer = kaartNummer;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reizigerId = reizigerId;
        this.reiziger = reiziger;
    }

    public OVChipkaart(int kaartNummer, Date geldigTot, int klasse, double saldo, int reizigerId, Reiziger reiziger, OvChipkaartProduct ovChipkaartProduct) {
        this.kaartNummer = kaartNummer;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reizigerId = reizigerId;
        this.reiziger = reiziger;
        this.ovChipkaartProduct = ovChipkaartProduct;
    }

    public int getKaartNummer() {
        return kaartNummer;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getReizigerId() {
        return reizigerId;
    }

    public String toString() {
        return "OVChipkaart{" +
                "kaart_nummer=" + kaartNummer +
                ", geldig_tot=" + geldigTot +
                ", klasse='" + klasse + '\'' +
                ", saldo='" + saldo + '\'' +
                ", reiziger_id=" + reizigerId +
                ", reiziger=" + reiziger +
                '}';
    }
}
