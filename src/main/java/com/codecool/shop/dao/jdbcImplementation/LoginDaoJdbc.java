package com.codecool.shop.dao.jdbcImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDaoJdbc {

    private static LoginDaoJdbc instance = null;

    private LoginDaoJdbc() {
    }

    public static LoginDaoJdbc getInstance() {
        if (instance == null) {
            instance = new LoginDaoJdbc();
        }
        return instance;
    }

    public String getHashPasswordForEmail(String email) {
        Connection connection = null;
        PreparedStatement stmt = null;
        String hashedPassword = "";

        String query = "SELECT password, FROM users WHERE email=?";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                hashedPassword = rs.getString("password");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeStatementAndConnection(connection, stmt);
        }
        return hashedPassword;
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
