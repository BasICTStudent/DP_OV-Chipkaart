package test;

import domain.Reiziger;
import dao.ReizigerDAO;

import java.sql.SQLException;
import java.util.List;

public class testReizigerEnAdresDAO {
    /**
    * P3. Adres en Reiziger DAO: persistentie van 2 klassen
    *
    * Deze methode test de CRUD-functionaliteit van de Adres en Reiziger DAO
    *
    * @throws SQLException
    */
    public static void testAdresEnReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n\n\n\n---------- Test Adres+Reiziger DAO -------------");

        // Haal alle Reizigers + Adressen op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers + adressen:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();
    }
}
