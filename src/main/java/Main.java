import domain.Reiziger;
import domain.ReizigerDAO;
import domain.ReizigerDAOPsql;

import java.sql.*;
import java.util.List;

public class Main {
    private Connection connection;

    public static void main(String[] args) throws SQLException {
        //        extra.OpdrachtP1.OPP1();
        String dbUrl = "jdbc:postgresql://localhost:5432/ovchip";
        String user = "postgres";
        String pass = "B@s's@D@t@b@s3";

        Main main = new Main();
        main.connection = DriverManager.getConnection(dbUrl, user, pass);
        ReizigerDAO rdao = new ReizigerDAOPsql(main.getConnection());
        main.testReizigerDAO(rdao);
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {

    }

    /**
    * P2. Reiziger DAO: persistentie van een klasse
    *
    * Deze methode test de CRUD-functionaliteit van de Reiziger DAO
    *
    * @throws SQLException
    */
    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
    }
}
