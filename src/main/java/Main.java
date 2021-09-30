import dao.AdresDAO;
import dao.OVChipkaartDAO;
import dao.ProductDAO;
import daosql.AdresDAOPsql;
import dao.ReizigerDAO;
import daosql.OVChipkaartDAOPsql;
//import daosql.ProductDAOPsql;
import daosql.ProductDAOPsql;
import daosql.ReizigerDAOPsql;
import test.*;

import java.sql.*;

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
        testReizigerDAO(rdao);

        AdresDAO adao = new AdresDAOPsql(main.getConnection());
        testAdresDAO(adao);

        testReizigerEnAdresDAO(rdao);

        OVChipkaartDAO ovcdo = new OVChipkaartDAOPsql(main.getConnection());
        testOv_chipkaartDAO(ovcdo);

        ProductDAO pdao = new ProductDAOPsql(main.getConnection());
        testProductDAO(pdao);

        main.closeConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        testReizigerDAO.testReizigerDAO(rdao);
    }

    private static void testAdresDAO(AdresDAO adao) throws SQLException {
        testAdresDAO.testAdresDAO(adao);
    }

    private static void testReizigerEnAdresDAO(ReizigerDAO rdao) throws SQLException {
        testReizigerEnAdresDAO.testAdresEnReizigerDAO(rdao);
    }

    private static void testOv_chipkaartDAO(OVChipkaartDAO odao) throws SQLException {
        testOv_chipkaartDAO.testOv_chipkaartDAO(odao);
    }

    private static void testProductDAO(ProductDAO pdao) throws SQLException {
        testProductDAO.testProductDAO(pdao);
    }
}
