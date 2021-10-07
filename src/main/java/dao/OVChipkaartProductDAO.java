package dao;

import domain.OvChipkaartProduct;
import java.sql.SQLException;

public interface OVChipkaartProductDAO {
    boolean save(OvChipkaartProduct ovChipkaartProduct) throws SQLException;

    boolean update(OvChipkaartProduct ovChipkaartProduct) throws SQLException;

    boolean delete(OvChipkaartProduct ovChipkaartProduct) throws SQLException;

    OvChipkaartProduct findByKaartAndProduct(int kaartNummer, int productNummer) throws SQLException;
}
