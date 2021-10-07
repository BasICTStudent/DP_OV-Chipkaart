package daosql;

import dao.OVChipkaartProductDAO;
import domain.OvChipkaartProduct;
import java.sql.*;

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
        String q = "UPDATE ov_chipkaart_product SET kaart_nummer = ?, product_nummer = ?, status = ?, last_update = ? WHERE kaart_nummer = ? AND product_nummer = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, ovChipkaartProduct.getKaartNummer());
        pst.setInt(2, ovChipkaartProduct.getProductNummer());
        pst.setString(3, ovChipkaartProduct.getStatus());
        pst.setDate(4, ovChipkaartProduct.getLast_update());
        pst.setInt(5, ovChipkaartProduct.getKaartNummer());
        pst.setInt(6, ovChipkaartProduct.getProductNummer());

        pst.executeQuery();
        return true;
    }

    public boolean delete(OvChipkaartProduct ovChipkaartProduct) throws SQLException {
        String q = "DELETE FROM ov_chipkaart_product WHERE kaart_nummer = ? AND product_nummer = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, ovChipkaartProduct.getKaartNummer());
        pst.setInt(2, ovChipkaartProduct.getProductNummer());

        pst.executeQuery();
        return true;
    }

    public OvChipkaartProduct findByKaartAndProduct(int kaartNummer, int productNummer) throws SQLException {
        String q = "SELECT * FROM ov_chipkaart_product WHERE kaart_nummer = ? AND product_nummer = ? ";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, kaartNummer);
        pst.setInt(2, productNummer);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            int kaartNummer1 = myRs.getInt("kaart_nummer");
            int productNummer1 = myRs.getInt("product_nummer");
            String status = myRs.getString("status");
            Date lastUpdate = myRs.getDate("last_update");
            return new OvChipkaartProduct(kaartNummer1, productNummer, status, lastUpdate, ovcdao.findByOVChipkaart(kaartNummer1, false), pdao.findByProduct(productNummer1, false));
        }
        return null;
    }
}
