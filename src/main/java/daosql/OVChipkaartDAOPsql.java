package daosql;

import dao.OVChipkaartDAO;
import domain.Ov_chipkaart;
import domain.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    private Connection conn;
    private ReizigerDAOPsql rdao;

    public OVChipkaartDAOPsql(Connection conn) {
        this.conn = conn;
        this.rdao = new ReizigerDAOPsql(conn);
    }

    public boolean save(Ov_chipkaart ov_chipkaart) throws SQLException {
        String q = "INSERT INTO ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, ov_chipkaart.getKaart_nummer());
        pst.setDate(2, ov_chipkaart.getGeldig_tot());
        pst.setInt(3, ov_chipkaart.getKlasse());
        pst.setDouble(4, ov_chipkaart.getSaldo());
        pst.setInt(5, ov_chipkaart.getReiziger_id());

        pst.executeQuery();
        return true;
    }

    public boolean update(Ov_chipkaart ov_chipkaart) throws SQLException {
        String q = "UPDATE ov_chipkaart SET kaart_nummer = ?, geldig_tot = ?, klasse = ?, saldo = ?, reiziger_id = ? WHERE kaart_nummer = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, ov_chipkaart.getKaart_nummer());
        pst.setDate(2, ov_chipkaart.getGeldig_tot());
        pst.setInt(3, ov_chipkaart.getKlasse());
        pst.setDouble(4, ov_chipkaart.getSaldo());
        pst.setInt(5, ov_chipkaart.getReiziger_id());
        pst.setInt(6, ov_chipkaart.getKaart_nummer());

        pst.executeQuery();
        return true;
    }

    public boolean delete(Ov_chipkaart ov_chipkaart) throws SQLException {
        String q = "DELETE FROM ov_chipkaart WHERE kaart_nummer = ? AND geldig_tot = ? AND klasse = ? AND saldo = ? AND reiziger_id = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, ov_chipkaart.getKaart_nummer());
        pst.setDate(2, ov_chipkaart.getGeldig_tot());
        pst.setInt(3, ov_chipkaart.getKlasse());
        pst.setDouble(4, ov_chipkaart.getSaldo());
        pst.setInt(5, ov_chipkaart.getReiziger_id());

        pst.executeQuery();
        return true;
    }

    public Ov_chipkaart findByKaart_nummer(int Kaart_nummer) throws SQLException {
        String q = "SELECT * FROM ov_chipkaart WHERE kaart_nummer = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, Kaart_nummer);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            int kaart_nummer = myRs.getInt("kaart_nummer");
            Date geldig_tot = myRs.getDate("geldig_tot");
            int klasse = myRs.getInt("klasse");
            int saldo = myRs.getInt("saldo");
            int reiziger_id = myRs.getInt("reiziger_id");
            return new Ov_chipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id, rdao.findByid(reiziger_id));
        }
        return null;
    }
    
    public List<Ov_chipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
        List<Ov_chipkaart> OvList = new ArrayList<Ov_chipkaart>();
        String q = "SELECT * FROM ov_chipkaart WHERE reiziger_id = ?";
        PreparedStatement pst = conn.prepareStatement(q);
        pst.setInt(1, reiziger.getReiziger_id());
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            int kaart_nummer = myRs.getInt("kaart_nummer");
            Date geldig_tot = myRs.getDate("geldig_tot");
            int klasse = myRs.getInt("klasse");
            int saldo = myRs.getInt("saldo");
            int reiziger_id = myRs.getInt("reiziger_id");
            OvList.add(new Ov_chipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id, rdao.findByid(reiziger_id)));
        }
        return OvList;
    }

    public List<Ov_chipkaart> findAll() throws SQLException {
        List<Ov_chipkaart> OvList = new ArrayList<>();
        String q = "SELECT * FROM ov_chipkaart";
        PreparedStatement pst = conn.prepareStatement(q);
        ResultSet myRs = pst.executeQuery();

        while (myRs.next()) {
            int kaart_nummer = myRs.getInt("kaart_nummer");
            Date geldig_tot = myRs.getDate("geldig_tot");
            int klasse = myRs.getInt("klasse");
            int saldo = myRs.getInt("saldo");
            int reiziger_id = myRs.getInt("reiziger_id");
            OvList.add(new Ov_chipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id, rdao.findByid(reiziger_id)));
        }
        return OvList;
    }
}
