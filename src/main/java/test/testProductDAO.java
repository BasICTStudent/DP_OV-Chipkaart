package test;

import dao.ProductDAO;
import daosql.OVChipkaartDAOPsql;
import domain.OVChipkaart;
import domain.Product;
import java.sql.SQLException;
import java.util.List;

public class testProductDAO {
    /**
    * P5. Product DAO: persistentie van 2 klasse in een meer op meer relatie
    *
    * Deze methode test de CRUD-functionaliteit van de Adres DAO
    *
    * @throws SQLException
    */
    public static void testProductDAO(ProductDAO pdao) throws SQLException {
        // predifined values
        Product product1 = pdao.findByProduct(1, false);
        Product product1New = new Product(1, "Dal Voordeel 40%", "40% korting buiten de spits en in het weekeind.", 26.00);
        Product product2 = new Product(77, "Railrunner", "Voordelig reizen voor kinderen.", 2.5);

        System.out.println("\n\n\n---------- Test ProductDAO -------------");

        // Haal alle producten op uit de database
        List<Product> productList = pdao.findAll(false);
        System.out.println("[Test] ProductDAO.findAll() geeft de volgende producten:");
        for (Product product : productList) {
            System.out.println(product);
        }
        System.out.println();

        try {
            // Maak een nieuwe product aan en persisteer deze in de database
            System.out.print("Eerst " + productList.size() + " producten, voor ProductDAO.save() \n");
            pdao.save(product2);
        } catch (Exception e) {
            productList = pdao.findAll(false);
            System.out.println("NA: " + productList.size() + " producten");
        }

        int i = 1;
        System.out.println("\n[Test] Product.findByProduct() geeft het volgende product op id= :1");
        System.out.println("Product op id = 1: " + pdao.findByProduct(i, false));

        try {
            // Update een bestaande product in de database
            System.out.println("\n[Test] Product.update() geeft de volgende verandering:");
            System.out.print("Zie product voor en na ProductDAO.update()\n");

            //dit is voor het terug zetten van product (op productnummer=100) naar het oude formaat
            try {
                pdao.update(product1);
            } catch (Exception ea) {
                //er is hier een cath omdat de database niks terug zend (dit wordt als een error gezien)
            }

            System.out.println("VOOR: " + pdao.findByProduct(i, false));

            try {
                pdao.update(product1New);
            } catch (Exception ea) {
                //er is hier een cath omdat de database niks terug zend (dit wordt als een error gezien)
                System.out.println("NA: " + pdao.findByProduct(i, false));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            // Delete een bestaande product in de database
            System.out.println("\n[Test] ProductDAO.delete() geeft de volgende verandering:");
            System.out.print("Zie Product op productnummer 77 voor en na ProductDAO.delete()\n");
            System.out.println("VOOR: " + pdao.findByProduct(product2.getProductNummer(), false));

            try {
                pdao.delete(product2);
            } catch (Exception ea) {
                //er is hier een cath omdat de database niks terug zend (dit wordt als een error gezien)
            }

            System.out.println("NA: " + pdao.findByProduct(product2.getProductNummer(), false));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            // vind bestaande producten in de database op kaartnummer
            System.out.println("\n[Test] ProductDAO.findByOVChipkaart() geeft het volgende (bij kaartnummer=35283):");
            OVChipkaart ovChipkaart = new OVChipkaartDAOPsql(pdao.getConn()).findByOVChipkaart(35283, false);
            System.out.println(pdao.findByOVChipkaart(ovChipkaart, false));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            // vind alle producten(en) in de database
            System.out.println("\n[Test] ProductDAO.findAll() geeft het volgende:");
            System.out.println(pdao.findAll(false));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
