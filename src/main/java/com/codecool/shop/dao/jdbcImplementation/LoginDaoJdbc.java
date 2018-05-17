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

    public String getHashPasswordWithEmail(String email) {
        Connection connection = null;
        PreparedStatement stmt = null;
        String hashedPassword = "";

        String query = "SELECT password FROM users WHERE email=?";

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


    public int getUserIdWithEmail(String email) {
        Connection connection = null;
        PreparedStatement stmt = null;
        int userId = 0;

        String query = "SELECT id FROM users WHERE email=?";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                userId = rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeStatementAndConnection(connection, stmt);
        }
        return userId;
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
