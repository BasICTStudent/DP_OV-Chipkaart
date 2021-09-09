package extra;

import java.sql.*;

public class OpdrachtP1 {
    public static void printDT(ResultSet myRs) throws SQLException {
        System.out.println("Alle reizigers:");
        while (myRs.next()) {
            String RID = myRs.getString("reiziger_id");
            String VL = myRs.getString("voorletters");

            String TV = myRs.getString("tussenvoegsel");
            if (TV == "null" || TV == null) {
                TV = "";
            } else TV =  " " + TV;

            String AN = myRs.getString("achternaam");
            String GD = myRs.getString("geboortedatum");

            System.out.println(("     #%s: %s.%s %s (%s)").formatted(RID, VL, TV, AN, GD));
        }
    }

    public static void OPP1() throws SQLException {
//        String url = "jdbc:postgresql://localhost:5432/ovchip";
//        Properties props = new Properties();
//        props.setProperty("user","postgres");
//        props.setProperty("password","B@s's@D@t@b@s3");
//        Connection conn = DriverManager.getConnection(url, props);

//        String url = "jdbc:postgresql://localhost/ovchip?user=postgres&password=B@s's@D@t@b@s3";
//        Connection conn = DriverManager.getConnection(url);

        String dbUrl = "jdbc:postgresql://localhost:5432/ovchip";
        String user = "postgres";
        String pass = "B@s's@D@t@b@s3";

        Connection myConn = DriverManager.getConnection(dbUrl, user, pass);
        Statement myStmt = myConn.createStatement();
        ResultSet myRs = myStmt.executeQuery("select * from reiziger");
        printDT(myRs);


        String q = "SELECT * FROM reiziger WHERE geboortedatum='3 december 2002'";
        myStmt = myConn.createStatement();
        myRs = myStmt.executeQuery(q);
        printDT(myRs);

        try {
            String q1 = "INSERT into reiziger " +
                    "(reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum)" +
                    "values (6, 'B', null, 'Valkonet', '2003-06-14')";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(q1);
        } catch (Exception e) {
            System.out.println(e);
        }

        myRs.close();
        myStmt.close();
        myConn.close();
    }
}
