package daosql;

import dao.AdresDAO;
import dao.ReizigerDAO;
import domain.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    private Connection conn;
    private AdresDAO adao;

    public ReizigerDAOPsql(Connection conn) {
        this.conn = conn;
        this.adao = new AdresDAOPsql(conn);
    }

    public Connection getConn() {
        return conn;
    }

    public boolean save(Reiziger reiziger) throws SQLException {
        String q = "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum, adres) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, reiziger.getReizigerId());
        pst.setString(2, reiziger.getVoorletters());
        pst.setString(3, reiziger.getTussenvoegsel());
        pst.setString(4, reiziger.getAchternaam());
        pst.setDate(5, reiziger.getGeboortedatum());
        pst.setInt(6, reiziger.getReizigerId());
        pst.executeQuery();
        return true;
    }

    public boolean update(Reiziger reiziger) throws SQLException {
        String q = "UPDATE reiziger SET reiziger_id = ?, voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ?, adres = ? WHERE reiziger_id = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, reiziger.getReizigerId());
        pst.setString(2, reiziger.getVoorletters());
        pst.setString(3, reiziger.getTussenvoegsel());
        pst.setString(4, reiziger.getAchternaam());
        pst.setDate(5, reiziger.getGeboortedatum());
        pst.setInt(6, reiziger.getReizigerId());
        pst.setInt(7, reiziger.getReizigerId());
        pst.executeQuery();
        return true;
    }

    public boolean delete(Reiziger reiziger) throws SQLException {
        String q = "DELETE FROM reiziger WHERE reiziger_id = ? AND voorletters = ? AND tussenvoegsel = ? AND achternaam = ? AND geboortedatum = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, reiziger.getReizigerId());
        pst.setString(2, reiziger.getVoorletters());
        pst.setString(3, reiziger.getTussenvoegsel());
        pst.setString(4, reiziger.getAchternaam());
        pst.setDate(5, reiziger.getGeboortedatum());
        pst.executeQuery();
        return true;
    }

    public Reiziger findByid(int id) throws SQLException {
        String q = "SELECT * FROM reiziger WHERE reiziger_id = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, id);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            String reiziger_id = myRs.getString("reiziger_id");
            String voorletters = myRs.getString("voorletters");
            String tussenvoegsel = myRs.getString("tussenvoegsel");
            String achternaam = myRs.getString("achternaam");
            String geboortedatum = myRs.getString("geboortedatum");
            int adres_id = myRs.getInt("adres");
            return new Reiziger(Integer.parseInt(reiziger_id), voorletters, tussenvoegsel, achternaam, Date.valueOf(geboortedatum), new AdresDAOPsql(conn).findByid(adres_id));
        }
        return null;
    }

    public List<Reiziger> findByGbdatum(String datum) throws SQLException {
        List<Reiziger> Rlist = new ArrayList<Reiziger>();
        String q = "SELECT * FROM reiziger WHERE geboortedatum = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setDate(1, Date.valueOf(datum));
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            String reiziger_id = myRs.getString("reiziger_id");
            String voorletters = myRs.getString("voorletters");
            String tussenvoegsel = myRs.getString("tussenvoegsel");
            String achternaam = myRs.getString("achternaam");
            String geboortedatum = myRs.getString("geboortedatum");
            int adres_id = myRs.getInt("adres");
            Rlist.add(new Reiziger(Integer.parseInt(reiziger_id), voorletters, tussenvoegsel, achternaam, Date.valueOf(geboortedatum), new AdresDAOPsql(conn).findByid(adres_id)));
        }
        return Rlist;
    }

    public List<Reiziger> findAll() throws SQLException {
        List<Reiziger> Rlist = new ArrayList<Reiziger>();
        String q = "SELECT * FROM reiziger";
        PreparedStatement pst = conn.prepareStatement(q);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            String reiziger_id = myRs.getString("reiziger_id");
            String voorletters = myRs.getString("voorletters");
            String tussenvoegsel = myRs.getString("tussenvoegsel");
            String achternaam = myRs.getString("achternaam");
            String geboortedatum = myRs.getString("geboortedatum");
            int adres_id = myRs.getInt("adres");
            Rlist.add(new Reiziger(Integer.parseInt(reiziger_id), voorletters, tussenvoegsel, achternaam, Date.valueOf(geboortedatum), adao.findByid(adres_id)));
        }
        return Rlist;
    }
}
