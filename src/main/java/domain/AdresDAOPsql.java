package domain;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO{
    private Connection conn;
    private ReizigerDAO rdao;

    public AdresDAOPsql(Connection conn) {
        this.conn = conn;
        this.rdao = new ReizigerDAOPsql(conn);
    }

    public boolean save(Adres adres) throws SQLException {
        Reiziger r = rdao.findByid(adres.getReiziger_id());
        String q = "INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, adres.getAdres_id());
        pst.setString(2, adres.getPostcode());
        pst.setString(3, adres.getHuisnummer());
        pst.setString(4, adres.getStraat());
        pst.setString(5, adres.getWoonplaats());
        pst.setInt(6, adres.getReiziger_id());

        try {
            pst.executeQuery();
        } catch (Exception e) {
            r.setAdres(adres.toString());
            rdao.save(r);
        }
        return true;
    }

    public boolean update(Adres adres) throws SQLException {
        Reiziger r = rdao.findByid(adres.getReiziger_id());
        String q = "UPDATE adres SET adres_id = ?, postcode = ?, huisnummer = ?, straat = ?, woonplaats = ?, reiziger_id = ? WHERE adres_id = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, adres.getAdres_id());
        pst.setString(2, adres.getPostcode());
        pst.setString(3, adres.getHuisnummer());
        pst.setString(4, adres.getStraat());
        pst.setString(5, adres.getWoonplaats());
        pst.setInt(6, adres.getReiziger_id());
        pst.setInt(7, adres.getAdres_id());

        try {
            pst.executeQuery();
        } catch (Exception e) {
            r.setAdres(adres.toString());
            rdao.update(r);
        }

        return true;
    }

    public boolean delete(Adres adres) throws SQLException {
        Reiziger r = rdao.findByid(adres.getReiziger_id());
        String q = "DELETE FROM adres WHERE adres_id = ? AND postcode = ? AND huisnummer = ? AND straat = ? AND woonplaats = ? AND reiziger_id = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, adres.getAdres_id());
        pst.setString(2, adres.getPostcode());
        pst.setString(3, adres.getHuisnummer());
        pst.setString(4, adres.getStraat());
        pst.setString(5, adres.getWoonplaats());
        pst.setInt(6, adres.getReiziger_id());

        try {
            pst.executeQuery();
        } catch (Exception e) {
            r.setAdres(null);
            rdao.update(r);
        }
        return true;
    }

    public Adres findByid(int id) throws SQLException {
        String q = "SELECT * FROM adres WHERE adres_id = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, id);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            String AID = myRs.getString("adres_id");
            String PC = myRs.getString("postcode");
            String HN = myRs.getString("huisnummer");
            String ST = myRs.getString("straat");
            String WP = myRs.getString("woonplaats");
            String RID = myRs.getString("reiziger_id");

            Reiziger r = rdao.findByid(Integer.parseInt(RID));
            return new Adres(Integer.parseInt(AID), PC, HN, ST, WP, Integer.parseInt(RID), r);
        }
        return null;
    }

    public Adres findByReiziger(Reiziger reiziger) throws SQLException {
        String q = "SELECT * FROM adres WHERE reiziger_id = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, reiziger.getReiziger_id());
        ResultSet myRs = pst.executeQuery();
        while (myRs.next()) {
            String AID = myRs.getString("adres_id");
            String PC = myRs.getString("postcode");
            String HN = myRs.getString("huisnummer");
            String ST = myRs.getString("straat");
            String WP = myRs.getString("woonplaats");
            String RID = myRs.getString("reiziger_id");

            Reiziger r = rdao.findByid(Integer.parseInt(RID));
            return new Adres(Integer.parseInt(AID), PC, HN, ST, WP, Integer.parseInt(RID), r);
        }
        return null;
    }

    public List<Adres> findAll() throws SQLException {
        List<Adres> Alist = new ArrayList<Adres>();
        String q = "SELECT * FROM adres";
        PreparedStatement pst = conn.prepareStatement(q);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            String AID = myRs.getString("adres_id");
            String PC = myRs.getString("postcode");
            String HN = myRs.getString("huisnummer");
            String ST = myRs.getString("straat");
            String WP = myRs.getString("woonplaats");
            String RID = myRs.getString("reiziger_id");

            Reiziger r = rdao.findByid(Integer.parseInt(RID));
            Adres a = new Adres(Integer.parseInt(AID), PC, HN, ST, WP, Integer.parseInt(RID), r);
            Alist.add(a);

            r.setAdres(a.toString());
            try {
                rdao.update(r);
            } catch (Exception e) {
                //er is hier een cath omdat de database niks terug zend (dit wordt als een error gezien)
            }
        }
        return Alist;
    }
}
