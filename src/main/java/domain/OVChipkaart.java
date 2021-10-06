package domain;

import daosql.OVChipkaartDAOPsql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class OVChipkaart {
    private int kaartNummer;
    private Date geldigTot;
    private int klasse;
    private double saldo;

    private int reizigerId;
    private Reiziger reiziger;
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

    public OVChipkaart(int kaartNummer, Date geldigTot, int klasse, double saldo, int reizigerId, Reiziger reiziger, ArrayList<Product> producten) {
        this.kaartNummer = kaartNummer;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reizigerId = reizigerId;
        this.reiziger = reiziger;
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

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public void addProduct(Product product, Connection conn) throws SQLException {
        producten.add(product);
        OVChipkaartDAOPsql ovcdao = new OVChipkaartDAOPsql(conn);

        if (ovcdao.findByOVChipkaart(kaartNummer, false) == null) {
            System.out.println(ovcdao.findByOVChipkaart(kaartNummer, false));
            ovcdao.save(this);
        } else {
            try {
                ovcdao.update(this);
            } catch (Exception e) {
                //update geeft standaard een error omdat er geen resultaat terug geeft.
            }
        }
    }

    public void updateProduct(Product product, Connection conn) throws SQLException {
        OVChipkaartDAOPsql ovcdao = new OVChipkaartDAOPsql(conn);

        for (Product product1 : producten) {
            if (product1.getProductNummer() == product.getProductNummer()) {
                producten.set(producten.indexOf(product1), product);
            }
        }
        try {
            ovcdao.update(this);
        } catch (Exception e) {
            //update geeft standaard een error omdat er geen resultaat terug geeft.
        }
    }

    public void deleteProduct(Product product, Connection conn) throws SQLException {
        OVChipkaartDAOPsql ovcdao = new OVChipkaartDAOPsql(conn);

        producten.remove(product);
        try {
            ovcdao.update(this);
        } catch (Exception e) {
            //update geeft standaard een error omdat er geen resultaat terug geeft.
        }
    }

    public String toString() {
        return "OVChipkaart{" +
                "kaart_nummer=" + kaartNummer +
                ", geldig_tot=" + geldigTot +
                ", klasse='" + klasse + '\'' +
                ", saldo='" + saldo + '\'' +
                ", reiziger_id=" + reizigerId +
                ", reiziger=" + reiziger +
                ", Producten=" + producten +
                '}';
    }
}
