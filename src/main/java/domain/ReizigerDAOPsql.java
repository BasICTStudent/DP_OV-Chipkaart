package domain;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO{
    private Connection conn;

    public ReizigerDAOPsql(Connection conn) {
        this.conn = conn;
    }

    public boolean save(Reiziger reiziger) throws SQLException {
        String q = "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(q);
        Statement myStmt = conn.createStatement();
        pst.setInt(1, reiziger.getReiziger_id());
        pst.setString(2, reiziger.getVoorletters());
        pst.setString(3, reiziger.getTussenvoegsel());
        pst.setString(4, reiziger.getAchternaam());
        pst.setDate(5, reiziger.getGeboortedatum());
        ResultSet myRs = pst.executeQuery();
        return true;
    }

    public boolean update(Reiziger reiziger) throws SQLException {
        String q = "UPDATE reiziger SET reiziger_id = ?, voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ? WHERE reiziger_id = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        Statement myStmt = conn.createStatement();
        pst.setInt(1, reiziger.getReiziger_id());
        pst.setString(2, reiziger.getVoorletters());
        pst.setString(3, reiziger.getTussenvoegsel());
        pst.setString(4, reiziger.getAchternaam());
        pst.setDate(5, reiziger.getGeboortedatum());
        pst.setInt(6, reiziger.getReiziger_id());
        ResultSet myRs = pst.executeQuery();
        return true;
    }

    public boolean delete(Reiziger reiziger) throws SQLException {
        String q = "DELETE FROM reiziger WHERE reiziger_id = ? AND voorletters = ? AND tussenvoegsel = ? AND achternaam = ? AND geboortedatum = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        Statement myStmt = conn.createStatement();
        pst.setInt(1, reiziger.getReiziger_id());
        pst.setString(2, reiziger.getVoorletters());
        pst.setString(3, reiziger.getTussenvoegsel());
        pst.setString(4, reiziger.getAchternaam());
        pst.setDate(5, reiziger.getGeboortedatum());
        ResultSet myRs = pst.executeQuery();
        return true;
    }

    public Reiziger findByid(int id) throws SQLException {
        String q = "SELECT * FROM reiziger WHERE reiziger_id = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        Statement myStmt = conn.createStatement();
        pst.setInt(1, id);
        ResultSet myRs = pst.executeQuery();

        String RID = myRs.getString("reiziger_id");
        String VL = myRs.getString("voorletters");
        String TV = myRs.getString("tussenvoegsel");
        String AN = myRs.getString("achternaam");
        String GD = myRs.getString("geboortedatum");
        return new Reiziger(Integer.parseInt(RID), VL, TV, AN, Date.valueOf(GD));
    }

    public List<Reiziger> findByGbdatum(String datum) throws SQLException {
        List<Reiziger> Rlist = new ArrayList<Reiziger>();
        String q = "SELECT * FROM reiziger WHERE geboortedatum = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        Statement myStmt = conn.createStatement();
        pst.setString(1, datum);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            String RID = myRs.getString("reiziger_id");
            String VL = myRs.getString("voorletters");
            String TV = myRs.getString("tussenvoegsel");
            String AN = myRs.getString("achternaam");
            String GD = myRs.getString("geboortedatum");
            Rlist.add(new Reiziger(Integer.parseInt(RID), VL, TV, AN, Date.valueOf(GD)));
        }
        return Rlist;
    }

    public List<Reiziger> findAll() throws SQLException {
        List<Reiziger> Rlist = new ArrayList<Reiziger>();
        String q = "SELECT * FROM reiziger";
        PreparedStatement pst = conn.prepareStatement(q);
        Statement myStmt = conn.createStatement();
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            String RID = myRs.getString("reiziger_id");
            String VL = myRs.getString("voorletters");
            String TV = myRs.getString("tussenvoegsel");
            String AN = myRs.getString("achternaam");
            String GD = myRs.getString("geboortedatum");
            Rlist.add(new Reiziger(Integer.parseInt(RID), VL, TV, AN, Date.valueOf(GD)));
        }
        return Rlist;
    }
}
