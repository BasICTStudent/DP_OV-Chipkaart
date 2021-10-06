package test;

import dao.ProductDAO;
import daosql.OVChipkaartDAOPsql;
import daosql.ProductDAOPsql;
import daosql.ReizigerDAOPsql;
import domain.OVChipkaart;
import domain.Product;
import domain.Reiziger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class testReizger_OVChipkaar_Product {
    /**
    * P5. Product DAO: persistentie van 2 klasse in een meer op meer relatie
    *
    * Deze methode test de CRUD-functionaliteit van de Adres DAO
    *
    * @throws SQLException
    */
    public static void testReizger_OVChipkaar_Product(Connection connection) throws SQLException {
        // predifined values
        ProductDAOPsql pdao = new ProductDAOPsql(connection);
        ReizigerDAOPsql rdao = new ReizigerDAOPsql(connection);
        OVChipkaartDAOPsql ovcdao = new OVChipkaartDAOPsql(connection);

        String gbdatum = "2000-01-10";
        Product product1 = pdao.findByProduct(1, false);
        Product product2 = new Product(1, "test", "test", 12);
        Reiziger reiziger001 = rdao.findByid(77);
        OVChipkaart ovChipkaart = ovcdao.findByOVChipkaart(77, false);

        System.out.println("\n\n\n---------- Test Reizger_OVChipkaar_Product -------------");

        // save product in ovchipkaart
        System.out.println("[TEST]: Reiziger addProduct to ovchipkaart: ");
        System.out.println("VOOR: " + ovChipkaart);
        reiziger001.addProduct(ovChipkaart, product1, connection);
        System.out.println("NA: " + ovChipkaart);

        // update product in ovchipkaart
        System.out.println("\n[TEST]: Reiziger updateProduct from ovchipkaart: ");
        System.out.println("VOOR: " + ovChipkaart);
        reiziger001.updateProduct(ovChipkaart, product2, connection);
        System.out.println("NA: " + ovChipkaart);

        // delete product in ovchipkaart
        System.out.println("\n[TEST]: Reiziger deleteProduct from ovchipkaart: ");
        System.out.println("VOOR: " + ovChipkaart);
        reiziger001.deleteProduct(ovChipkaart, product2, connection);
        System.out.println("NA: " + ovChipkaart);
    }
}
