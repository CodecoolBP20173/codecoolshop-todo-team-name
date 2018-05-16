package com.codecool.shop.dao.jdbcImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegistrationDaoJdbc {

    private static RegistrationDaoJdbc instance = null;

    private RegistrationDaoJdbc() {
    }

    public static RegistrationDaoJdbc getInstance() {
        if (instance == null) {
            instance = new RegistrationDaoJdbc();
        }
        return instance;
    }

    public void add(String email, String password) {
        Connection connection = null;
        PreparedStatement stmt = null;

        String query = "INSERT INTO users (email, password) VALUES (?, ?)";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);

            stmt.setString(1, email);
            stmt.setString(2, password);

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeStatementAndConnection(connection, stmt);
        }
    }

    private void closeStatementAndConnection(Connection connection, PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }
}
