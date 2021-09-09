package domain;

import java.sql.Date;

public class Reiziger {
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;


//    private ArrayList<Ov_chipkaart> ov_chipkaart = new ArrayList<Ov_chipkaart>;

    public Reiziger(int reiziger_id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.id = reiziger_id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public int getReiziger_id() {
        return id;
    }

    public void setReiziger_id(int reiziger_id) {
        this.id = reiziger_id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
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

    public String getNaam() {
        if (tussenvoegsel == "null" || tussenvoegsel == null || tussenvoegsel == "") {
            return voorletters + " " + achternaam;
        } else return voorletters + " " + tussenvoegsel + " " + achternaam;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    @Override
    public String toString() {
        return "Reiziger{" +
                "id=" + id +
                ", voorletters='" + voorletters + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum=" + geboortedatum +
                '}';
    }

    //    public Adres getAdres() {
//        return adres;
//    }
//
//    public void setAdres(Adres adres) {
//        this.adres = adres;
//    }
//
//    public ArrayList<Ov_chipkaart> getOv_chipkaart() {
//        return ov_chipkaart;
//    }
//
//    public void setOv_chipkaart(ArrayList<Ov_chipkaart> ov_chipkaart) {
//        this.ov_chipkaart = ov_chipkaart;
//    }
}
