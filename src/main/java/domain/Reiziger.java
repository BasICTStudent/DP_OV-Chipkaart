package domain;

import daosql.OVChipkaartDAOPsql;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class Reiziger {
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;

    private Adres adres;
    private ArrayList<OVChipkaart> ovChipkaarten = new ArrayList<>();


    public Reiziger(int reiziger_id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.id = reiziger_id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public Reiziger(int reizigerId, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum, Adres adres) {
        this.id = reizigerId;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.adres = adres;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public int getReizigerId() {
        return id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public String toString() {
        return "Reiziger{" +
                "id=" + id +
                ", voorletters='" + voorletters + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum='" + geboortedatum + '\'' +
                ", adres=" + adres + '\'' +
                ", ovChipkaarten=" + ovChipkaarten +
                '}';
    }


    public void addOVChipkaart(OVChipkaart ovChipkaart, Connection conn) throws SQLException {
        this.ovChipkaarten.add(ovChipkaart);
        new OVChipkaartDAOPsql(conn).save(ovChipkaart);
    }

    public void updateOVChipkaart(OVChipkaart ovChipkaart, Connection conn) throws SQLException {
        new OVChipkaartDAOPsql(conn).update(ovChipkaart);
    }

    public void deleteOVChipkaart(OVChipkaart ovChipkaart, Connection conn) throws SQLException {
        new OVChipkaartDAOPsql(conn).delete(ovChipkaart);
        this.ovChipkaarten.remove(ovChipkaart);
    }

    public void addProduct(OVChipkaart ovChipkaart, Product product, Connection conn) throws SQLException {
        ovChipkaart.addProduct(product, conn);
    }

    public void updateProduct(OVChipkaart ovChipkaart, Product product1, Product product2, Connection conn) throws SQLException {
        ovChipkaart.updateProduct(product1, product2, conn);
    }

    public void deleteProduct(OVChipkaart ovChipkaart, Product product, Connection conn) throws SQLException {
        ovChipkaart.deleteProduct(product, conn);
    }

    public ArrayList<OVChipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }
}
