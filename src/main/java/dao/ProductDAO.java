package dao;

import domain.OVChipkaart;
import domain.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    void save(Product product) throws SQLException;

    void update(Product product) throws SQLException;

    void delete(Product product) throws SQLException;

    Product findByProduct(int productNummer, boolean vanOVC) throws SQLException;

    List<Product> findByOVChipkaart(OVChipkaart ovChipkaart, boolean vanOVC) throws SQLException;

    List<Product> findAll(boolean vanOVC) throws SQLException;

    Connection getConn();
}
