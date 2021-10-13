package daosql;

import dao.AdresDAO;
import dao.ReizigerDAO;
import domain.Adres;
import domain.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {
    private Connection conn;

    public AdresDAOPsql(Connection conn) {
        this.conn = conn;
    }

    public boolean save(Adres adres) throws SQLException {
        String q = "INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, adres.getAdresId());
        pst.setString(2, adres.getPostcode());
        pst.setString(3, adres.getHuisnummer());
        pst.setString(4, adres.getStraat());
        pst.setString(5, adres.getWoonplaats());
        pst.setInt(6, adres.getReizigerId());

        pst.executeQuery();
        return true;
    }

    public boolean update(Adres adres) throws SQLException {
        String q = "UPDATE adres SET adres_id = ?, postcode = ?, huisnummer = ?, straat = ?, woonplaats = ?, reiziger_id = ? WHERE adres_id = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, adres.getAdresId());
        pst.setString(2, adres.getPostcode());
        pst.setString(3, adres.getHuisnummer());
        pst.setString(4, adres.getStraat());
        pst.setString(5, adres.getWoonplaats());
        pst.setInt(6, adres.getReizigerId());
        pst.setInt(7, adres.getAdresId());

        pst.executeQuery();
        return true;
    }

    public boolean delete(Adres adres) throws SQLException {
        String q = "DELETE FROM adres WHERE adres_id = ? AND postcode = ? AND huisnummer = ? AND straat = ? AND woonplaats = ? AND reiziger_id = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, adres.getAdresId());
        pst.setString(2, adres.getPostcode());
        pst.setString(3, adres.getHuisnummer());
        pst.setString(4, adres.getStraat());
        pst.setString(5, adres.getWoonplaats());
        pst.setInt(6, adres.getReizigerId());

        pst.executeQuery();
        return true;
    }

    public Adres findByid(int id) throws SQLException {
        String q = "SELECT * FROM adres WHERE adres_id = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, id);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            String adres_id = myRs.getString("adres_id");
            String postcode = myRs.getString("postcode");
            String huisnummer = myRs.getString("huisnummer");
            String straat = myRs.getString("straat");
            String woonplaats = myRs.getString("woonplaats");
            String reiziger_id = myRs.getString("reiziger_id");

            return new Adres(Integer.parseInt(adres_id), postcode, huisnummer, straat, woonplaats, Integer.parseInt(reiziger_id));
        }
        return null;
    }

    public Adres findByReiziger(Reiziger reiziger) throws SQLException {
        String q = "SELECT * FROM adres WHERE reiziger_id = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, reiziger.getReizigerId());
        ResultSet myRs = pst.executeQuery();
        while (myRs.next()) {
            String adres_id = myRs.getString("adres_id");
            String postcode = myRs.getString("postcode");
            String huisnummer = myRs.getString("huisnummer");
            String straat = myRs.getString("straat");
            String woonplaats = myRs.getString("woonplaats");
            String reiziger_id = myRs.getString("reiziger_id");

            return new Adres(Integer.parseInt(adres_id), postcode, huisnummer, straat, woonplaats, Integer.parseInt(reiziger_id));
        }
        return null;
    }

    public List<Adres> findAll() throws SQLException {
        List<Adres> Alist = new ArrayList<Adres>();
        String q = "SELECT * FROM adres";
        PreparedStatement pst = conn.prepareStatement(q);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            String adres_id = myRs.getString("adres_id");
            String postcode = myRs.getString("postcode");
            String huisnummer = myRs.getString("huisnummer");
            String straat = myRs.getString("straat");
            String woonplaats = myRs.getString("woonplaats");
            String reiziger_id = myRs.getString("reiziger_id");

            Adres a = new Adres(Integer.parseInt(adres_id), postcode, huisnummer, straat, woonplaats, Integer.parseInt(reiziger_id));
            Alist.add(a);
        }
        return Alist;
    }
}
