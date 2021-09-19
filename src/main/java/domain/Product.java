package domain;

import java.util.ArrayList;

public class Product {
    private int product_nummer;
    private String naam;
    private String beschrijving;
    private int prijs;

    private ArrayList<Ov_chipkaart_product> ov_chipkaart_producten = new ArrayList<>();

    public Product(int product_nummer, String naam, String beschrijving, int prijs) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public Product(int product_nummer, String naam, String beschrijving, int prijs, ArrayList<Ov_chipkaart_product> ov_chipkaart_producten) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.ov_chipkaart_producten = ov_chipkaart_producten;
    }

    public int getProduct_nummer() {
        return product_nummer;
    }

    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
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

    public ArrayList<Ov_chipkaart_product> getOv_chipkaart_producten() {
        return ov_chipkaart_producten;
    }

    public void setOv_chipkaart_producten(ArrayList<Ov_chipkaart_product> ov_chipkaart_producten) {
        this.ov_chipkaart_producten = ov_chipkaart_producten;
    }

    public void addOv_chipkaart_producten(Ov_chipkaart_product ov_chipkaart_product) {
        this.ov_chipkaart_producten.add(ov_chipkaart_product);
    }
}
