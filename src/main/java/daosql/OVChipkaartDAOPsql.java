package daosql;

import dao.OVChipkaartDAO;
import domain.OVChipkaart;
import domain.Product;
import domain.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    private Connection conn;
    private ReizigerDAOPsql rdao;

    public OVChipkaartDAOPsql(Connection conn) {
        this.conn = conn;
        this.rdao = new ReizigerDAOPsql(conn);
    }

    public Connection getConn() {
        return conn;
    }

    public boolean save(OVChipkaart ovChipkaart) throws SQLException {
        String q = "INSERT INTO ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, ovChipkaart.getKaartNummer());
        pst.setDate(2, ovChipkaart.getGeldigTot());
        pst.setInt(3, ovChipkaart.getKlasse());
        pst.setDouble(4, ovChipkaart.getSaldo());
        pst.setInt(5, ovChipkaart.getReizigerId());

        pst.executeQuery();
        return true;
    }

    public boolean update(OVChipkaart ovChipkaart) throws SQLException {
        String q = "UPDATE ov_chipkaart SET kaart_nummer = ?, geldig_tot = ?, klasse = ?, saldo = ?, reiziger_id = ? WHERE kaart_nummer = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, ovChipkaart.getKaartNummer());
        pst.setDate(2, ovChipkaart.getGeldigTot());
        pst.setInt(3, ovChipkaart.getKlasse());
        pst.setDouble(4, ovChipkaart.getSaldo());
        pst.setInt(5, ovChipkaart.getReizigerId());
        pst.setInt(6, ovChipkaart.getKaartNummer());

        pst.executeQuery();
        return true;
    }

    public boolean delete(OVChipkaart ovChipkaart) throws SQLException {
        String q = "DELETE FROM ov_chipkaart WHERE kaart_nummer = ? AND geldig_tot = ? AND klasse = ? AND saldo = ? AND reiziger_id = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, ovChipkaart.getKaartNummer());
        pst.setDate(2, ovChipkaart.getGeldigTot());
        pst.setInt(3, ovChipkaart.getKlasse());
        pst.setDouble(4, ovChipkaart.getSaldo());
        pst.setInt(5, ovChipkaart.getReizigerId());

        pst.executeQuery();
        return true;
    }

    public OVChipkaart findByOVChipkaart(int kaartNummer, boolean vanPr) throws SQLException {
        String q = "SELECT * FROM ov_chipkaart WHERE kaart_nummer = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, kaartNummer);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            int kaart_nummer = myRs.getInt("kaart_nummer");
            Date geldig_tot = myRs.getDate("geldig_tot");
            int klasse = myRs.getInt("klasse");
            int saldo = myRs.getInt("saldo");
            int reiziger_id = myRs.getInt("reiziger_id");

            if (vanPr) {
                return new OVChipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id, rdao.findByid(reiziger_id));
            } else {
                OVChipkaart ovChipkaart = new OVChipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id, rdao.findByid(reiziger_id));
                ArrayList<Product> producten = (ArrayList<Product>) new ProductDAOPsql(conn).findByOVChipkaart(ovChipkaart, true);
                return new OVChipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id, rdao.findByid(reiziger_id), producten);
            }
        }
        return null;
    }
    
    public List<OVChipkaart> findByReiziger(Reiziger reiziger, boolean vanPr) throws SQLException {
        List<OVChipkaart> OvList = new ArrayList<OVChipkaart>();
        String q = "SELECT * FROM ov_chipkaart WHERE reiziger_id = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, reiziger.getReizigerId());
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            int kaart_nummer = myRs.getInt("kaart_nummer");
            Date geldig_tot = myRs.getDate("geldig_tot");
            int klasse = myRs.getInt("klasse");
            int saldo = myRs.getInt("saldo");
            int reiziger_id = myRs.getInt("reiziger_id");

            if (vanPr) {
                OvList.add(new OVChipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id, rdao.findByid(reiziger_id)));
            } else {
                OVChipkaart ovChipkaart = new OVChipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id, rdao.findByid(reiziger_id));
                ArrayList<Product> producten = (ArrayList<Product>) new ProductDAOPsql(conn).findByOVChipkaart(ovChipkaart, true);
                OvList.add(new OVChipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id, rdao.findByid(reiziger_id), producten));
            }
        }
        return OvList;
    }

    public ArrayList<OVChipkaart> findByProductNummer(int productNummer, boolean vanPr) throws SQLException {
        List<OVChipkaart> OvList = new ArrayList<OVChipkaart>();
        String q = "SELECT * FROM ov_chipkaart JOIN ov_chipkaart_product ON (ov_chipkaart.kaart_nummer = ov_chipkaart_product.kaart_nummer) JOIN product ON (product.product_nummer = ov_chipkaart_product.product_nummer) WHERE  product.product_nummer = ?;";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, productNummer);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            int kaart_nummer = myRs.getInt("kaart_nummer");
            Date geldig_tot = myRs.getDate("geldig_tot");
            int klasse = myRs.getInt("klasse");
            int saldo = myRs.getInt("saldo");
            int reiziger_id = myRs.getInt("reiziger_id");

            if (vanPr) {
                OvList.add(new OVChipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id, rdao.findByid(reiziger_id)));
            } else {
                OVChipkaart ovChipkaart = new OVChipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id, rdao.findByid(reiziger_id));
                ArrayList<Product> producten = (ArrayList<Product>) new ProductDAOPsql(conn).findByOVChipkaart(ovChipkaart, true);
                OvList.add(new OVChipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id, rdao.findByid(reiziger_id), producten));
            }
        }
        return (ArrayList<OVChipkaart>) OvList;
    }

    public List<OVChipkaart> findAll(boolean vanPr) throws SQLException {
        List<OVChipkaart> OvList = new ArrayList<>();
        String q = "SELECT * FROM ov_chipkaart";
        PreparedStatement pst = conn.prepareStatement(q);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            int kaart_nummer = myRs.getInt("kaart_nummer");
            Date geldig_tot = myRs.getDate("geldig_tot");
            int klasse = myRs.getInt("klasse");
            int saldo = myRs.getInt("saldo");
            int reiziger_id = myRs.getInt("reiziger_id");

            if (vanPr) {
                OvList.add(new OVChipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id, rdao.findByid(reiziger_id)));
            } else {
                OVChipkaart ovChipkaart = new OVChipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id, rdao.findByid(reiziger_id));
                ArrayList<Product> producten = (ArrayList<Product>) new ProductDAOPsql(conn).findByOVChipkaart(ovChipkaart, true);
                OvList.add(new OVChipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id, rdao.findByid(reiziger_id), producten));
            }
        }
        return OvList;
    }
}
