package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.codecool.shop.dao.jdbcImplementation.ConnectionManager.closeStatementAndConnection;

public class SupplierDaoJdbc implements SupplierDao {

    private static SupplierDaoJdbc instance = null;

    private SupplierDaoJdbc() {
    }

    public static SupplierDaoJdbc getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        Connection connection = null;
        PreparedStatement stmt = null;

        String query = "INSERT INTO supplier (name, description) VALUES (?, ?)";
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getDescription());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, stmt);
        }

    }

    @Override
    public Supplier find(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;
        int supplierId = 0;
        String supplierName = "";
        String supplierDescription = "";

        String query = "SELECT * FROM supplier WHERE id = ?";
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                supplierId = rs.getInt("id");
                supplierName = rs.getString("name");
                supplierDescription = rs.getString("description");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, stmt);
        }
        Supplier supplier = new Supplier(supplierName, supplierDescription);
        supplier.setId(supplierId);
        return supplier;
    }

    @Override
    public void remove(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;

        String query = "DELETE FROM supplier WHERE id = ?";
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, stmt);
        }
    }

    @Override
    public List<Supplier> getAll() {
        String query = "SELECT * FROM supplier;";
        List<Supplier> resultList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;

        try {connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
         
            while (resultSet.next()) {
                Supplier supplier = new Supplier(resultSet.getString("name"),
                        resultSet.getString("description"));
                supplier.setId(resultSet.getInt("id"));
                resultList.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(connection, stmt);
        }

        return resultList;
    }
}
