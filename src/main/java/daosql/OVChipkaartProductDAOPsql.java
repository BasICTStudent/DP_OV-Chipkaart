package daosql;

import dao.OVChipkaartProductDAO;
import domain.OvChipkaartProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartProductDAOPsql implements OVChipkaartProductDAO {
    private Connection conn;
    private OVChipkaartDAOPsql ovcdao;
    private ProductDAOPsql pdao;

    public OVChipkaartProductDAOPsql(Connection conn) {
        this.conn = conn;
        this.ovcdao = new OVChipkaartDAOPsql(conn);
        this.pdao = new ProductDAOPsql(conn);
    }

    public boolean save(OvChipkaartProduct ovChipkaartProduct) throws SQLException {
        String q = "INSERT INTO ov_chipkaart_product (kaart_nummer, product_nummer, status, last_update) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, ovChipkaartProduct.getKaartNummer());
        pst.setInt(2, ovChipkaartProduct.getProductNummer());
        pst.setString(3, ovChipkaartProduct.getStatus());
        pst.setDate(4, ovChipkaartProduct.getLast_update());

        pst.executeQuery();
        return true;
    }

    public boolean update(OvChipkaartProduct ovChipkaartProduct) throws SQLException {
        String q = "UPDATE ov_chipkaart_product SET kaart_nummer = ?, product_nummer = ?, status = ?, last_update = ? WHERE kaart_nummer = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, ovChipkaartProduct.getKaartNummer());
        pst.setInt(2, ovChipkaartProduct.getProductNummer());
        pst.setString(3, ovChipkaartProduct.getStatus());
        pst.setDate(4, ovChipkaartProduct.getLast_update());
        pst.setInt(5, ovChipkaartProduct.getKaartNummer());

        pst.executeQuery();
        return true;
    }

    public boolean delete(OvChipkaartProduct ovChipkaartProduct) throws SQLException {
        String q = "DELETE FROM ov_chipkaart_product WHERE kaart_nummer = ? AND product_nummer = ? AND status = ? AND last_update = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, ovChipkaartProduct.getKaartNummer());
        pst.setInt(2, ovChipkaartProduct.getProductNummer());
        pst.setString(3, ovChipkaartProduct.getStatus());
        pst.setDate(4, ovChipkaartProduct.getLast_update());

        pst.executeQuery();
        return true;
    }

    public List<OvChipkaartProduct> findByKaartNummer(int kaartNummer) throws SQLException {
        List<OvChipkaartProduct> OvList = new ArrayList<OvChipkaartProduct>();
        String q = "SELECT * FROM ov_chipkaart_product WHERE kaart_nummer = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, kaartNummer);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            int kaartNummer1 = myRs.getInt("kaart_nummer");
            int productNummer = myRs.getInt("product_nummer");
            String status = myRs.getString("status");
            Date lastUpdate = myRs.getDate("last_update");
            OvList.add(new OvChipkaartProduct(kaartNummer1, productNummer, status, lastUpdate, ovcdao.findByOVChipkaart(kaartNummer1), pdao.findByProductNummer(productNummer)));
        }
        return null;
    }

    public List<OvChipkaartProduct> findByProductNummer(int productNummer) throws SQLException {
        List<OvChipkaartProduct> OvList = new ArrayList<OvChipkaartProduct>();
        String q = "SELECT * FROM ov_chipkaart_product WHERE product_nummer = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, productNummer);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            int kaartNummer = myRs.getInt("kaart_nummer");
            int productNummer1 = myRs.getInt("product_nummer");
            String status = myRs.getString("status");
            Date lastUpdate = myRs.getDate("last_update");
            OvList.add(new OvChipkaartProduct(kaartNummer, productNummer1, status, lastUpdate, ovcdao.findByOVChipkaart(kaartNummer), pdao.findByProductNummer(productNummer1)));
        }
        return OvList;
    }

    public List<OvChipkaartProduct> findAll() throws SQLException {
        List<OvChipkaartProduct> OvList = new ArrayList<OvChipkaartProduct>();
        String q = "SELECT * FROM ov_chipkaart_product";
        PreparedStatement pst = conn.prepareStatement(q);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            int kaartNummer = myRs.getInt("kaart_nummer");
            int productNummer = myRs.getInt("product_nummer");
            String status = myRs.getString("status");
            Date lastUpdate = myRs.getDate("last_update");
            OvList.add(new OvChipkaartProduct(kaartNummer, productNummer, status, lastUpdate, ovcdao.findByOVChipkaart(kaartNummer), pdao.findByProductNummer(productNummer)));
        }
        return OvList;
    }
}
