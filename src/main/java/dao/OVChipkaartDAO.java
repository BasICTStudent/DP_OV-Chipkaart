package dao;

import domain.Ov_chipkaart;
import domain.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDAO {
    boolean save(Ov_chipkaart ov_chipkaart) throws SQLException;

    boolean update(Ov_chipkaart ov_chipkaart) throws SQLException;

    boolean delete(Ov_chipkaart ov_chipkaart) throws SQLException;

    Ov_chipkaart findByKaart_nummer(int Kaart_nummer) throws SQLException;

    List<Ov_chipkaart> findByReiziger(Reiziger reiziger) throws SQLException;

    List<Ov_chipkaart> findAll() throws SQLException;
}
