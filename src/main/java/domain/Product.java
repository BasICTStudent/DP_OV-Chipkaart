package domain;

import java.util.ArrayList;

public class Product {
    private int productNummer;
    private String naam;
    private String beschrijving;
    private int prijs;

//    private int kaartNummer;
    private ArrayList<OvChipkaartProduct> ov_chipkaart_producten = new ArrayList<>();

//    public Product(int productNummer, String naam, String beschrijving, int prijs, int kaartNummer) {
//        this.productNummer = productNummer;
//        this.naam = naam;
//        this.beschrijving = beschrijving;
//        this.prijs = prijs;
//        this.kaartNummer = kaartNummer;
//    }

    public Product(int productNummer, String naam, String beschrijving, int prijs, ArrayList<OvChipkaartProduct> ov_chipkaart_producten) { //int kaartNummer,
        this.productNummer = productNummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
//        this.kaartNummer = kaartNummer;
        this.ov_chipkaart_producten = ov_chipkaart_producten;
    }

    public int getProductNummer() {
        return productNummer;
    }

    public void setProductNummer(int productNummer) {
        this.productNummer = productNummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public int getPrijs() {
        return prijs;
    }

    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }

//    public int getKaartNummer() {
//        return kaartNummer;
//    }
//
//    public void setKaartNummer(int kaartNummer) {
//        this.kaartNummer = kaartNummer;
//    }

    public ArrayList<OvChipkaartProduct> getOv_chipkaart_producten() {
        return ov_chipkaart_producten;
    }

    public void setOv_chipkaart_producten(ArrayList<OvChipkaartProduct> ov_chipkaart_producten) {
        this.ov_chipkaart_producten = ov_chipkaart_producten;
    }
}
