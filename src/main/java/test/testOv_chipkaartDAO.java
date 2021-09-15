package test;

import dao.OVChipkaartDAO;
import domain.Ov_chipkaart;
import domain.Reiziger;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class testOv_chipkaartDAO {
    /**
    * P4. Ov_chipkaart DAO: persistentie van 2 klasse in een 1 op meer relatie
    *
    * Deze methode test de CRUD-functionaliteit van de Adres DAO
    *
    * @throws SQLException
    */
    public static void testOv_chipkaartDAO(OVChipkaartDAO odao) throws SQLException {
        // predifined values
        String gbdatum = "2000-01-10";
        Ov_chipkaart ov_chipkaart001 = new Ov_chipkaart(100100, Date.valueOf("1999-10-10"), 1, 123.34, 100);
        Reiziger reiziger001 = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));

        System.out.println("\n\n\n---------- Test Ov_chipkaartDAO -------------");

        // Haal alle ov_chipkaarten op uit de database
        List<Ov_chipkaart> ov_chipkaarten = odao.findAll();
        System.out.println("[Test] Ov_chipkaartDAO.findAll() geeft de volgende ov_chipkaarten:");
        for (Ov_chipkaart ov_chipkaart : ov_chipkaarten) {
            System.out.println(ov_chipkaart);
        }
        System.out.println();

        try {
            // Maak een nieuwe ov_chipkaarten aan en persisteer deze in de database
            System.out.print("Eerst " + ov_chipkaarten.size() + " ov_chipkaarten, voor Ov_chipkaartDAO.save() \n");
            odao.save(ov_chipkaart001);
        } catch (Exception e) {
            ov_chipkaarten = odao.findAll();
            System.out.println("NA: " + ov_chipkaarten.size() + " ov_chipkaarten");
        }

        int i = 100100;
        Ov_chipkaart ov_chipkaartUP = new Ov_chipkaart(100100, Date.valueOf("1987-12-21"), 2, 240.51, 1);

        try {
            // Update een bestaande ov_chipkaart in de database
            System.out.println("\n[Test] Ov_chipkaartDAO.update() geeft de volgende verandering:");
            System.out.print("Zie ov_chipkaart voor en na Ov_chipkaartDAO.update()\n");

            //dit is voor het terug zetten van ov_chipkaart001 (op kaartnummer=100100) naar het oude formaat
            try {
                odao.update(ov_chipkaart001);
            } catch (Exception ea) {
                //er is hier een cath omdat de database niks terug zend (dit wordt als een error gezien)
            }

            System.out.println("VOOR: " + odao.findByKaart_nummer(i));

            try {
                odao.update(ov_chipkaartUP);
            } catch (Exception ea) {
                //er is hier een cath omdat de database niks terug zend (dit wordt als een error gezien)
                System.out.println("NA: " + odao.findByKaart_nummer(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // vind een bestaande ov_chipkaart in de database op kaartnummer
        System.out.println("\n[Test] Ov_chipkaartDAO.findByid() geeft het volgende (bij kaartnummer=100100):");
        System.out.println(odao.findByKaart_nummer(i));

        try {
            // Delete een bestaande ov_chipkaart in de database
            System.out.println("\n[Test] Ov_chipkaartDAO.delete() geeft de volgende verandering:");
            System.out.print("Zie ov_chipkaart op kaartnummer 100100 voor en na Ov_chipkaartDAO.delete()\n");
            System.out.println("VOOR: " + odao.findByKaart_nummer(i));

            try {
                odao.delete(ov_chipkaartUP);
            } catch (Exception ea) {
                //er is hier een cath omdat de database niks terug zend (dit wordt als een error gezien)
            }

            System.out.println("NA: " + odao.findByKaart_nummer(i));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            // vind bestaande ov_chipkaart(en) in de database op reiziger
            System.out.println("\n[Test] AdresDAO.findByReiziger() geeft het volgende (bij een reiziger met het id=77):");
            System.out.println(odao.findByReiziger(reiziger001));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            // vind alle ov_chipkaart(en) in de database
            System.out.println("\n[Test] Ov_chipkaartDAO.findAll() geeft het volgende:");
            System.out.println(odao.findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
