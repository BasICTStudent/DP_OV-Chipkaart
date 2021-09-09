package test;

import domain.Reiziger;
import domain.ReizigerDAO;

import java.sql.SQLException;
import java.util.List;

public class testReizigerDAO {
    public static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("Eerst " + reizigers.size() + " reizigers, voor ReizigerDAO.save() \n");

        try {
            // Maak een nieuwe reiziger aan en persisteer deze in de database
            Reiziger R2 = new Reiziger(99, "e", "e", "e", java.sql.Date.valueOf("1981-03-15"));
            rdao.save(R2);
        } catch (Exception e) {
            reizigers = rdao.findAll();
            System.out.println("NA: " + reizigers.size() + " reizigers");
        }

        int i = 77;
        try {
            // Update een bestaande reiziger in de database
            System.out.println("\n[Test] ReizigerDAO.update() geeft de volgende verandering:");
            Reiziger sietskeUP = new Reiziger(i, "B", "", "TEST", java.sql.Date.valueOf(gbdatum));
            System.out.print("Zie Sietske voor en na ReizigerDAO.update()\n");

            //dit is voor het terug zetten van sietske (op id=77) naar het oude formaat
            try {
                rdao.update(sietske);
            } catch (Exception ea) {
                //er is hier een cath omdat de database niks terug zend (dit wordt als een error gezien)
            }

            System.out.println("VOOR: " + rdao.findByid(i));

            try {
                rdao.update(sietskeUP);
            } catch (Exception ea) {
                //er is hier een cath omdat de database niks terug zend (dit wordt als een error gezien)
                System.out.println("NA: " + rdao.findByid(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            // vind een bestaande reiziger in de database op id
            System.out.println("\n[Test] ReizigerDAO.findByid() geeft het volgende (bij id=77):");
            System.out.println(rdao.findByid(i));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            // Delete een bestaande reiziger in de database
            System.out.println("\n[Test] ReizigerDAO.delete() geeft de volgende verandering:");
            String gbdatum1 = "1981-03-15";
            Reiziger sietskeUP = new Reiziger(99, "e", "e", "e", java.sql.Date.valueOf(gbdatum1));

            System.out.print("Zie gebruiker op id 99 voor en na ReizigerDAO.delete()\n");
            System.out.println("VOOR: " + rdao.findByid(99));

            try {
                rdao.delete(sietskeUP);
            } catch (Exception ea) {
                //er is hier een cath omdat de database niks terug zend (dit wordt als een error gezien)
            }

            System.out.println("NA: " + rdao.findByid(99));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            // vind bestaande reiziger(s) in de database op geboortedatum
            System.out.println("\n[Test] ReizigerDAO.findByGbdatum() geeft het volgende (bij 2002-12-03):");
            System.out.println(rdao.findByGbdatum("2002-12-03"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            // vind alle reiziger(s) in de database
            System.out.println("\n[Test] ReizigerDAO.findAll() geeft het volgende:");
            System.out.println(rdao.findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
