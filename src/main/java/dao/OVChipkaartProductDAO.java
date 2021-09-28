package dao;

import domain.OVChipkaart;
import domain.OvChipkaartProduct;
import domain.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartProductDAO {
    boolean save(OvChipkaartProduct ovChipkaartProduct) throws SQLException;

    boolean update(OvChipkaartProduct ovChipkaartProduct) throws SQLException;

    boolean delete(OvChipkaartProduct ovChipkaartProduct) throws SQLException;

    List<OvChipkaartProduct> findByKaartNummer(int kaartNummer) throws SQLException;

    List<OvChipkaartProduct> findByProductNummer(int productNummer) throws SQLException;

    List<OvChipkaartProduct> findAll() throws SQLException;
}
