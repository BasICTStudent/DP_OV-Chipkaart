package domain;

import daosql.OVChipkaartProductDAOPsql;
import daosql.ProductDAOPsql;
import org.postgresql.util.PSQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaart {
    private int kaartNummer;
    private Date geldigTot;
    private int klasse;
    private double saldo;
    private int reizigerId;
    private Reiziger reiziger;
    private List<Product> producten = new ArrayList<>();


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

    public List<Product> getProducten() {
        return producten;
    }

    public void addProduct(Product product, Connection conn) throws SQLException {
        OVChipkaartProductDAOPsql ovcpdao = new OVChipkaartProductDAOPsql(conn);
        try {
            ovcpdao.save(new OvChipkaartProduct(kaartNummer, product.getProductNummer(), "actief", Date.valueOf(LocalDate.now()), this, product));
        } catch (PSQLException exception1) {
            //save geeft standaard niks terug (en geeft dus een error)
        }
        //save geeft standaard niks terug (en geeft dus een error)
        this.producten.add(product);
    }

    public void updateProduct(Product product1, Product product2, Connection conn) throws SQLException {
        deleteProduct(product1, conn);
        addProduct(product2, conn);
    }

    public void deleteProduct(Product product, Connection conn) throws SQLException {
        OVChipkaartProductDAOPsql ovcpdao = new OVChipkaartProductDAOPsql(conn);
        try {
            ovcpdao.delete(new OvChipkaartProduct(kaartNummer, product.getProductNummer(), "actief", Date.valueOf(LocalDate.now()), this, product));
        } catch (PSQLException exception1) {
            //save geeft standaard niks terug (en geeft dus een error)
        }
        this.producten.remove(product);
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
