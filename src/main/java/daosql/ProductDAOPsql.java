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
        String q = "INSERT INTO product (product_nummer, naam, beschrijving, prijs) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, product.getProductNummer());
        pst.setString(2, product.getNaam());
        pst.setString(3, product.getBeschrijving());
        pst.setDouble(4, product.getPrijs());

        pst.executeQuery();
        return true;
    }

    public boolean update(Product product) throws SQLException {
        String q = "UPDATE product SET product_nummer = ?, naam = ?, beschrijving = ?, prijs = ? WHERE product_nummer = ?";
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
        String q = "DELETE FROM product WHERE product_nummer = ? AND naam = ? AND beschrijving = ? AND prijs = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, product.getProductNummer());
        pst.setString(2, product.getNaam());
        pst.setString(3, product.getBeschrijving());
        pst.setDouble(4, product.getPrijs());

        pst.executeQuery();
        return true;
    }

    public Product findByProduct(int productNummer, boolean vanOVC) throws SQLException {
        String q = "SELECT * FROM product WHERE product_nummer = ?;";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, productNummer);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            int productnummer = myRs.getInt("product_nummer");
            String naam = myRs.getString("naam");
            String beschrijving = myRs.getString("beschrijving");
            int prijs = myRs.getInt("prijs");

            if (vanOVC) {
                return new Product(productnummer, naam, beschrijving, prijs);
            } else {
                ArrayList<OVChipkaart> ovChipkaarten = ovcdao.findByProductNummer(productnummer, true);
                return new Product(productnummer, naam, beschrijving, prijs, ovChipkaarten);
            }
        }
        return null;
    }

    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart, boolean vanOVC) throws SQLException {
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

            if (vanOVC) {
                productList.add(new Product(productnummer, naam, beschrijving, prijs));
            } else {
                ArrayList<OVChipkaart> ovChipkaarten = ovcdao.findByProductNummer(productnummer, true);
                productList.add(new Product(productnummer, naam, beschrijving, prijs, ovChipkaarten));
            }
        }
        return productList;
    }

    public List<Product> findAll(boolean vanOVC) throws SQLException {
        List<Product> productList = new ArrayList<Product>();
        String q = "SELECT * FROM product";
        PreparedStatement pst = conn.prepareStatement(q);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            int productnummer = myRs.getInt("product_nummer");
            String naam = myRs.getString("naam");
            String beschrijving = myRs.getString("beschrijving");
            int prijs = myRs.getInt("prijs");

            if (vanOVC) {
                productList.add(new Product(productnummer, naam, beschrijving, prijs));
            } else {
                ArrayList<OVChipkaart> ovChipkaarten = ovcdao.findByProductNummer(productnummer, true);
                productList.add(new Product(productnummer, naam, beschrijving, prijs, ovChipkaarten));
            }
        }
        return productList;
    }

    public Connection getConn() {
        return conn;
    }
}
