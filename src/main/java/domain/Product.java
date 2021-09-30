package domain;

import java.util.ArrayList;

public class Product {
    private int productNummer;
    private String naam;
    private String beschrijving;
    private double prijs;

    private ArrayList<Integer> kaartNummer = new ArrayList<>();
    private ArrayList<OVChipkaart> ovChipkaarten = new ArrayList<>();
//    private ArrayList<OvChipkaartProduct> ov_chipkaart_producten = new ArrayList<>();

    public Product(int productNummer, String naam, String beschrijving, double prijs) {
        this.productNummer = productNummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public Product(int productNummer, String naam, String beschrijving, double prijs, ArrayList<Integer> kaartNummer, ArrayList<OVChipkaart> ovChipkaarten) { //, ArrayList<OvChipkaartProduct> ov_chipkaart_producten       int kaartNummer,
        this.productNummer = productNummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.kaartNummer = kaartNummer;
        this.ovChipkaarten = ovChipkaarten;
    }

//    public Product(int productNummer, String naam, String beschrijving, double prijs, int kaartNummer, ArrayList<Product> producten) {        //int kaartNummer,
//        this.productNummer = productNummer;
//        this.naam = naam;
//        this.beschrijving = beschrijving;
//        this.prijs = prijs;
//        this.kaartNummer = kaartNummer;
//        this.producten = producten;
//    }

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

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }

    public ArrayList<Integer> getKaartNummer() {
        return kaartNummer;
    }

    public void setKaartNummer(ArrayList<Integer> kaartNummer) {
        this.kaartNummer = kaartNummer;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public ArrayList<OVChipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }

    public void setOvChipkaarten(ArrayList<OVChipkaart> ovChipkaarten) {
        this.ovChipkaarten = ovChipkaarten;
    }

    //    public ArrayList<OvChipkaartProduct> getOv_chipkaart_producten() {
//        return ov_chipkaart_producten;
//    }
//
//    public void setOv_chipkaart_producten(ArrayList<OvChipkaartProduct> ov_chipkaart_producten) {
//        this.ov_chipkaart_producten = ov_chipkaart_producten;
//    }


    @Override
    public String toString() {
        return "Product{" +
                "productNummer=" + productNummer +
                ", naam='" + naam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs +
                ", kaartNummer=" + kaartNummer +
                ", ovChipkaarten=" + ovChipkaarten +
                '}';
    }
}
