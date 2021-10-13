package test;

import dao.AdresDAO;
import domain.Reiziger;
import domain.Adres;

import java.sql.SQLException;
import java.util.List;

public class testAdresDAO {
    /**
    * P3. Adres DAO: persistentie van 2 klasse
    *
    * Deze methode test de CRUD-functionaliteit van de Adres DAO
    *
    * @throws SQLException
    */
    public static void testAdresDAO(AdresDAO adao) throws SQLException {
        // predifined values
        String gbdatum = "2000-01-10";
        Reiziger reiziger = new Reiziger(100, "T0000", "T0000", "T0000", java.sql.Date.valueOf(gbdatum));
        Adres adres = new Adres(100, "1234AB", "T0000", "T0000", "T0000", 100);
        reiziger.setAdres(adres);

        System.out.println("\n\n\n\n---------- Test AdresDAO -------------");

        // Haal alle Adressen op uit de database
        List<Adres> adressen = adao.findAll();
        System.out.println("[Test] AdresDAO.findAll() geeft de volgende adressen:");
        for (Adres a : adressen) {
            System.out.println(a);
        }
        System.out.println();

        try {
            // Maak een nieuwe adres aan en persisteer deze in de database
            System.out.print("Eerst " + adressen.size() + " adressen, voor AdresDAO.save() \n");
            adao.save(adres);
        } catch (Exception e) {
            //er is hier een cath omdat de database niks terug zend (dit wordt als een error gezien)
            adressen = adao.findAll();
            System.out.println("NA: " + adressen.size() + " adressen");
        }

        int i = 100;
        Adres adresUP = new Adres(100, "2", "2", "2", "2", 100);

        try {
            // Update een bestaande adres in de database
            System.out.println("\n[Test] AdresDAO.update() geeft de volgende verandering:");
            System.out.print("Zie Adres voor en na AdresDAO.update()\n");

            //dit is voor het terug zetten van adress (op id=100) naar het oude formaat
            try {
                adao.update(adres);
            } catch (Exception ea) {
                //er is hier een cath omdat de database niks terug zend (dit wordt als een error gezien)
            }

            System.out.println("VOOR: " + adao.findByid(i));

            try {
                adao.update(adresUP);
            } catch (Exception ea) {
                //er is hier een cath omdat de database niks terug zend (dit wordt als een error gezien)
                System.out.println("NA: " + adao.findByid(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            // vind een bestaande adres in de database op id
            System.out.println("\n[Test] AdresDAO.findByid() geeft het volgende (bij id=100):");
            System.out.println(adao.findByid(i));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            // Delete een bestaande adres in de database
            System.out.println("\n[Test] AdresDAO.delete() geeft de volgende verandering:");
            System.out.print("Zie adres op id 100 voor en na AdresDAO.delete()\n");
            System.out.println("VOOR: " + adao.findByid(i));

            try {
                adao.delete(adresUP);
            } catch (Exception ea) {
                //er is hier een cath omdat de database niks terug zend (dit wordt als een error gezien)
            }

            System.out.println("NA: " + adao.findByid(i));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));

        try {
            // vind bestaande adres in de database op reiziger
            System.out.println("\n[Test] AdresDAO.findByReiziger() geeft het volgende (bij id=77):");
            System.out.println(adao.findByReiziger(sietske));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            // vind alle adres(sen) in de database
            System.out.println("\n[Test] AdresDAO.findAll() geeft het volgende:");
            System.out.println(adao.findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
