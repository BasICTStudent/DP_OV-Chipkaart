package dao;

import domain.OVChipkaart;
import domain.Reiziger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OVChipkaartDAO {
    boolean save(OVChipkaart ovChipkaart) throws SQLException;

    boolean update(OVChipkaart ovChipkaart) throws SQLException;

    boolean delete(OVChipkaart ovChipkaart) throws SQLException;

    OVChipkaart findByOVChipkaart(int kaartNummer, boolean vanPr) throws SQLException;

    List<OVChipkaart> findByReiziger(Reiziger reiziger, boolean vanPr) throws SQLException;

    ArrayList<OVChipkaart> findByProductNummer(int productNumme, boolean vanPr) throws SQLException;

    List<OVChipkaart> findAll(boolean vanPr) throws SQLException;

    Connection getConn();
}
