package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderDaoJdbc implements OrderDao {

    private static HashMap<Integer, OrderDaoJdbc> instances;

    public static OrderDaoJdbc getInstance(int userId) {
        if (instances == null) {
            instances = new HashMap<Integer, OrderDaoJdbc>();
        }
        if (!instances.containsKey(userId)) {
            instances.put(userId, new OrderDaoJdbc());
        }
        return instances.get(userId);
    }

    public List<Integer> getQuantities(int userId) {
        List<Product> products = getAll(userId);
        Connection connection = null;
        PreparedStatement stmt = null;
        int quantity = 0;
        List<Integer> q = new ArrayList<>();

        String query =
                "SELECT COUNT(*) c FROM userorder " +
                        "WHERE productid=? AND userid=?";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            for (Product product : products) {
                stmt.setInt(1, product.getId());
                stmt.setInt(2, userId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    quantity = rs.getInt("count");
                }
                q.add(quantity);
            }

            return q;

        } catch (Exception e) {
            System.out.print(e.getMessage());

        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {

            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {

            }
        }
        return null;
    }

    public void add(Product product, int userId) {
        Connection connection = null;
        PreparedStatement stmt = null;

        String query =
                "INSERT INTO userorder " +
                        "(userid, productid) " +
                        "VALUES (?, ?)";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, userId);
            stmt.setInt(2, product.getId());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.print(e.getMessage());

        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {

            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {

            }
        }

    }

    public void remove(Product product, int userId) {
        Connection connection = null;
        PreparedStatement stmt = null;

        String query =
                "DELETE FROM userorder " +
                        "WHERE userid=? AND productid IN (SELECT id FROM product LIMIT 1)";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, userId);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.print(e.getMessage());

        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {

            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {

            }
        }
    }

    public void delete(Product product) {
        Connection connection = null;
        PreparedStatement stmt = null;

        String query =
                "DELETE FROM userorder " +
                        "WHERE userid=? AND productid=?";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, userId);
            stmt.setInt(2, product.getId());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.print(e.getMessage());

        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {

            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {

            }
        }
    }

    public List<Product> getAll(int userId) {
        Connection connection = null;
        PreparedStatement stmt = null;
        String name = null;
        String currency = null;
        String description = null;
        double price = 0;
        int id;
        List<Product> products = new ArrayList<>();

        String query =
                "SELECT p.id, p.name, p.description, p.price, p.currency FROM product p " +
                        "INNER JOIN userorder u on p.id = u.productid INNER JOIN users u2 on u.userid = u2.id " +
                        "WHERE u2.id=?";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                name = rs.getString("name");
                currency = rs.getString("currency");
                description = rs.getString("description");
                id = rs.getInt("id");
                price = rs.getDouble("price");
                Product p = new Product(name, (float) price, currency, description);
                p.setId(id);
                products.add(p);
            }

            return products;

        } catch (Exception e) {
            System.out.print(e.getMessage());

        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {

            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {

            }
        }
        return null;
    }

    public int getProductNum() {
        return 0;
    }

    public String getSumOfPrices() {
        return null;
    }

    public double sumOfPrices() {
        return 0;
    }
}
