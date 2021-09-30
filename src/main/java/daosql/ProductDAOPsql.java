package daosql;

import dao.ProductDAO;
import domain.OVChipkaart;
import domain.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOPsql implements ProductDAO {
    private Connection conn;
    private OVChipkaartDAOPsql ovcdao;

    public ProductDAOPsql(Connection conn) {
        this.conn = conn;
        this.ovcdao = new OVChipkaartDAOPsql(conn);
    }

    public boolean save(Product product) throws SQLException {
        String q = "INSERT INTO product (product_nummer, naam, beschrijving, prijs) VALUES (?, ?, ?, ?)"; //kaart_nummer
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, product.getProductNummer());
        pst.setString(2, product.getNaam());
        pst.setString(3, product.getBeschrijving());
        pst.setDouble(4, product.getPrijs());

        pst.executeQuery();
        return true;
    }

    public boolean update(Product product) throws SQLException {
        String q = "UPDATE product SET product_nummer = ?, naam = ?, beschrijving = ?, prijs = ? WHERE product_nummer = ?"; //, kaart_nummer = ?
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, product.getProductNummer());
        pst.setString(2, product.getNaam());
        pst.setString(3, product.getBeschrijving());
        pst.setDouble(4, product.getPrijs());
        pst.setInt(5, product.getProductNummer());

        pst.executeQuery();
        return true;
    }

    public boolean delete(Product product) throws SQLException {
        String q = "DELETE FROM product WHERE product_nummer = ? AND naam = ? AND beschrijving = ? AND prijs = ?"; //AND kaart_nummer = ?
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, product.getProductNummer());
        pst.setString(2, product.getNaam());
        pst.setString(3, product.getBeschrijving());
        pst.setDouble(4, product.getPrijs());

        pst.executeQuery();
        return true;
    }

    public Product findByProduct(int productNummer) throws SQLException {
        String q = "SELECT * FROM product WHERE product_nummer = ?;";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, productNummer);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            int productnummer = myRs.getInt("product_nummer");
            String naam = myRs.getString("naam");
            String beschrijving = myRs.getString("beschrijving");
            int prijs = myRs.getInt("prijs");

            ArrayList<Integer> kaartNummers = new ArrayList<>();
            ArrayList<OVChipkaart> ovChipkaarten = ovcdao.findByProductNummer(productnummer);
            for (OVChipkaart ovChipkaart : ovChipkaarten) {
                kaartNummers.add(ovChipkaart.getKaartNummer());
            }
            return new Product(productnummer, naam, beschrijving, prijs, kaartNummers, ovChipkaarten);
        }
        return null;
    }

    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) throws SQLException {
        List<Product> productList = new ArrayList<Product>();
        String q = "SELECT * FROM ov_chipkaart JOIN ov_chipkaart_product ON (ov_chipkaart.kaart_nummer = ov_chipkaart_product.kaart_nummer) JOIN product ON (product.product_nummer = ov_chipkaart_product.product_nummer) WHERE ov_chipkaart.kaart_nummer = ?;";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, ovChipkaart.getKaartNummer());
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            int productnummer = myRs.getInt("product_nummer");
            String naam = myRs.getString("naam");
            String beschrijving = myRs.getString("beschrijving");
            int prijs = myRs.getInt("prijs");

            ArrayList<Integer> kaartNummers = new ArrayList<>();
            ArrayList<OVChipkaart> ovChipkaarten = ovcdao.findByProductNummer(productnummer);
            for (OVChipkaart ovChipkaart1 : ovChipkaarten) {
                kaartNummers.add(ovChipkaart1.getKaartNummer());
            }
            productList.add(new Product(productnummer, naam, beschrijving, prijs, kaartNummers, ovChipkaarten)); //, ovChipkaartProducten
        }
        return productList;
    }

    public List<Product> findAll() throws SQLException {
        List<Product> productList = new ArrayList<Product>();
        String q = "SELECT * FROM product";
        PreparedStatement pst = conn.prepareStatement(q);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            int productnummer = myRs.getInt("product_nummer");
            String naam = myRs.getString("naam");
            String beschrijving = myRs.getString("beschrijving");
            int prijs = myRs.getInt("prijs");

            ArrayList<Integer> kaartNummers = new ArrayList<>();
            ArrayList<OVChipkaart> ovChipkaarten = ovcdao.findByProductNummer(productnummer);
            for (OVChipkaart ovChipkaart : ovChipkaarten) {
                kaartNummers.add(ovChipkaart.getKaartNummer());
            }
            productList.add(new Product(productnummer, naam, beschrijving, prijs, kaartNummers, ovChipkaarten));
        }
        return productList;
    }

    public Connection getConn() {
        return conn;
    }
}
