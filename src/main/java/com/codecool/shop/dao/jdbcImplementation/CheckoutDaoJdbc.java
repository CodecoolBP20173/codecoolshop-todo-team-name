package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.dao.CheckoutDao;
import com.codecool.shop.model.Checkout;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheckoutDaoJdbc implements CheckoutDao {

    private static CheckoutDaoJdbc instance = null;

    private CheckoutDaoJdbc() {
    }

    public static CheckoutDaoJdbc getInstance() {
        if (instance == null) {
            instance = new CheckoutDaoJdbc();
        }
        return instance;
    }

    public void add(Checkout checkout) {
        Connection connection = null;
        PreparedStatement stmt = null;

        String query = "INSERT INTO checkout (userid, name, email, telephone, billcountry, billcity, billzipcode, billaddress, shipcountry, shipcity, shipzipcode, shipaddress) VALUES (?, ?, ?, ?, ? ,? ,?, ? ,?, ?, ?, ?)";
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, checkout.getUserId());
            stmt.setString(2, checkout.getName());
            stmt.setString(3, checkout.getEmail());
            stmt.setString(4, checkout.getTelephone());
            stmt.setString(5, checkout.getBillCountry());
            stmt.setString(6, checkout.getBillCity());
            stmt.setString(7, checkout.getBillZipcode());
            stmt.setString(8, checkout.getBillAddress());
            stmt.setString(9, checkout.getShipCountry());
            stmt.setString(10, checkout.getShipCity());
            stmt.setString(11, checkout.getShipZipcode());
            stmt.setString(12, checkout.getShipAddress());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Checkout> getAll() {
        String query = "SELECT * FROM checkout;";
        List<Checkout> resultList = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                Checkout checkout = new Checkout(resultSet.getInt("userid"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("telephone"),
                        resultSet.getString("billcountry"),
                        resultSet.getString("billcity"),
                        resultSet.getString("billzipcode"),
                        resultSet.getString("billaddress"),
                        resultSet.getString("shipcountry"),
                        resultSet.getString("shipcity"),
                        resultSet.getString("shipzipcode"),
                        resultSet.getString("shipaddress"));
                resultList.add(checkout);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

}
