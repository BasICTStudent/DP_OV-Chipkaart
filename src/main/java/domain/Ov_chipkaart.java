package domain;

import java.sql.Date;
import java.util.ArrayList;

public class Ov_chipkaart {
    private int kaart_nummer;
    private Date geldig_tot;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;

    private Adres adres;
    private ArrayList<Ov_chipkaart> ov_chipkaarten = new ArrayList<>();

    public Ov_chipkaart(int kaart_nummer, Date geldig_tot, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

//    public Ov_chipkaart(int kaart_nummer, Date geldig_tot, String tussenvoegsel, String achternaam, Date geboortedatum, Adres adres) {
//        this.kaart_nummer = kaart_nummer;
//        this.geldig_tot = geldig_tot;
//        this.tussenvoegsel = tussenvoegsel;
//        this.achternaam = achternaam;
//        this.geboortedatum = geboortedatum;
//        this.adres = adres;
//    }

//    public Ov_chipkaart(int kaart_nummer, Date geldig_tot, String tussenvoegsel, String achternaam, Date geboortedatum, ArrayList<Ov_chipkaart> ov_chipkaarten) {
//        this.kaart_nummer = kaart_nummer;
//        this.geldig_tot = geldig_tot;
//        this.tussenvoegsel = tussenvoegsel;
//        this.achternaam = achternaam;
//        this.geboortedatum = geboortedatum;
//        this.ov_chipkaarten = ov_chipkaarten;
//    }

//    public Ov_chipkaart(int kaart_nummer, Date geldig_tot, String tussenvoegsel, String achternaam, Date geboortedatum, Adres adres, ArrayList<Ov_chipkaart> ov_chipkaarten) {
//        this.kaart_nummer = kaart_nummer;
//        this.geldig_tot = geldig_tot;
//        this.tussenvoegsel = tussenvoegsel;
//        this.achternaam = achternaam;
//        this.geboortedatum = geboortedatum;
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

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public ArrayList<Ov_chipkaart> getOv_chipkaarten() {
        return ov_chipkaarten;
    }

    public void setOv_chipkaarten(ArrayList<Ov_chipkaart> ov_chipkaarten) {
        this.ov_chipkaarten = ov_chipkaarten;
    }

    @Override
    public String toString() {
        return "Ov_chipkaart{" +
                "kaart_nummer=" + kaart_nummer +
                ", geldig_tot=" + geldig_tot +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum=" + geboortedatum +
                ", adres=" + adres +
                ", ov_chipkaarten=" + ov_chipkaarten +
                '}';
    }
}
