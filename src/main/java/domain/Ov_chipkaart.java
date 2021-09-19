package domain;

import java.sql.Date;

public class Ov_chipkaart {
    private int kaart_nummer;
    private Date geldig_tot;
    private int klasse;
    private double saldo;
    private int reiziger_id;
    private Reiziger reiziger;

    public Ov_chipkaart(int kaart_nummer, Date geldig_tot, int klasse, double saldo, int reiziger_id) {
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger_id = reiziger_id;
    }

    public Ov_chipkaart(int kaart_nummer, Date geldig_tot, int klasse, double saldo, int reiziger_id, Reiziger reiziger) {
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger_id = reiziger_id;
        this.reiziger = reiziger;
    }

//    public Ov_chipkaart(int kaart_nummer, Date geldig_tot, String tussenvoegsel, String achternaam, int reiziger_id, ArrayList<Ov_chipkaart> ov_chipkaarten) {
//        this.kaart_nummer = kaart_nummer;
//        this.geldig_tot = geldig_tot;
//        this.klasse = klasse;
//        this.saldo = saldo;
//        this.reiziger_id = reiziger_id;
//        this.ov_chipkaarten = ov_chipkaarten;
//    }

//    public Ov_chipkaart(int kaart_nummer, Date geldig_tot, int klasse, int saldo, int reiziger_id, Adres adres, ArrayList<Ov_chipkaart> ov_chipkaarten) {
//        this.kaart_nummer = kaart_nummer;
//        this.geldig_tot = geldig_tot;
//        this.klasse = klasse;
//        this.saldo = saldo;
//        this.reiziger_id = reiziger_id;
//        this.adres = adres;
//        this.ov_chipkaarten = ov_chipkaarten;
//    }

    public int getKaart_nummer() {
        return kaart_nummer;
    }

    public void setKaart_nummer(int kaart_nummer) {
        this.kaart_nummer = kaart_nummer;
    }

    public Date getGeldig_tot() {
        return geldig_tot;
    }

    public void setGeldig_tot(Date geldig_tot) {
        this.geldig_tot = geldig_tot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getReiziger_id() {
        return reiziger_id;
    }

    public void setReiziger_id(int reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    public Reiziger getAdres() {
        return reiziger;
    }

    public void setAdres(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    @Override
    public String toString() {
        return "Ov_chipkaart{" +
                "kaart_nummer=" + kaart_nummer +
                ", geldig_tot=" + geldig_tot +
                ", klasse='" + klasse + '\'' +
                ", saldo='" + saldo + '\'' +
                ", reiziger_id=" + reiziger_id +
                ", reiziger=" + reiziger +
                '}';
    }
}
