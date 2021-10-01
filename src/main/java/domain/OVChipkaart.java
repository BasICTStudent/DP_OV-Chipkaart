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
    private ArrayList<Integer> productNummers = new ArrayList<>();
    private ArrayList<Product> producten = new ArrayList<>();


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

    public OVChipkaart(int kaartNummer, Date geldigTot, int klasse, double saldo, int reizigerId, Reiziger reiziger, ArrayList<Integer> productNummers, ArrayList<Product> producten) {
        this.kaartNummer = kaartNummer;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reizigerId = reizigerId;
        this.reiziger = reiziger;
        this.productNummers = productNummers;
        this.producten = producten;
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

    public void setKaartNummer(int kaartNummer) {
        this.kaartNummer = kaartNummer;
    }

    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setReizigerId(int reizigerId) {
        this.reizigerId = reizigerId;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public String toString() {
        return "OVChipkaart{" +
                "kaart_nummer=" + kaartNummer +
                ", geldig_tot=" + geldigTot +
                ", klasse='" + klasse + '\'' +
                ", saldo='" + saldo + '\'' +
                ", reiziger_id=" + reizigerId +
                ", reiziger=" + reiziger +
                ", productNummer=" + productNummers +
                ", ovChipkaartProduct=" + producten +
                '}';
    }
}
