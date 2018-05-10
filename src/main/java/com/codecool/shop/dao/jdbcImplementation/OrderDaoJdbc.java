package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class OrderDaoJdbc implements OrderDao {

    private static OrderDaoJdbc instance;

    public static OrderDaoJdbc getInstance() {
        if (instance == null) {
            instance = new OrderDaoJdbc();
        }
        return instance;
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
                System.out.print(e.getMessage());
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.print(e.getMessage());
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
                System.out.print(e.getMessage());
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
    }

    public void delete(Product product, int userId) {
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
                System.out.print(e.getMessage());
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
    }

    public Map<Product, Integer> getAll(int userId) {
        Connection connection = null;
        PreparedStatement stmt = null;
        int count;
        int productId;
        Map<Product, Integer> allProducts = new HashMap<>();

        String query =
                "SELECT productid, COUNT(productid) FROM userorder " +
                        "WHERE userid=? " +
                        "GROUP BY productid;";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                productId = rs.getInt("productid");
                count = rs.getInt("count");
                allProducts.put(getProduct(productId), count);
            }

            return allProducts;

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

    public int getProductNum(int userId) {
        int sum = 0;
        Map<Product, Integer> allProducts = getAll(userId);
        for (Integer num : allProducts.values()) {
            sum += num;
        }
        return sum;
    }

    public String getSumOfPrices(int userId) {

        return sumOfPrices(userId) + " CC";
    }

    public double sumOfPrices(int userId) {
        double sum = 0;
        Map<Product, Integer> allProducts = getAll(userId);
        for (Map.Entry<Product, Integer> entry : allProducts.entrySet()) {
            Product product = entry.getKey();
            int num = entry.getValue();
            sum += product.getDefaultPrice() * num;
        }
        return sum;
    }

    private ProductCategory getProductCategory(int productId) {
        Connection connection = null;
        PreparedStatement stmt = null;

        int id = 0;
        String name = null;
        String department = null;
        String description = null;

        String query =
                "SELECT p.id, p.name, p.department, p.description FROM product " +
                        "INNER JOIN productcategory p on product.productcategoryid = p.id " +
                        "WHERE product.id=?;";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("name");
                department = rs.getString("department");
                description = rs.getString("description");
            }

            ProductCategory productCategory = new ProductCategory(name, department, description);
            productCategory.setId(id);
            return productCategory;

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

    private Supplier getSupplier(int productId) {

        Connection connection = null;
        PreparedStatement stmt = null;

        int id = 0;
        String name = null;
        String description = null;

        String query =
                "SELECT s.id, s.name, s.description FROM product " +
                        "INNER JOIN supplier s on product.productcategoryid = s.id " +
                        "WHERE product.id=?;";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("name");
                description = rs.getString("description");
            }

            Supplier supplier = new Supplier(name, description);
            supplier.setId(id);
            return supplier;

        } catch (Exception e) {
            System.out.print(e.getMessage());

        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
        return null;

    }

    private Product getProduct(int productId) {

        Connection connection = null;
        PreparedStatement stmt = null;

        String name = null;
        String description = null;
        double price = 0;
        String currency = null;

        String query =
                "SELECT * FROM product " +
                        "WHERE product.id=?;";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                name = rs.getString("name");
                description = rs.getString("description");
                price = rs.getDouble("price");
                currency = rs.getString("currency");
            }

            Product product = new Product(name, (float) price, currency, description, getProductCategory(productId), getSupplier(productId));
            product.setId(productId);
            return product;

        } catch (Exception e) {
            System.out.print(e.getMessage());

        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
        return null;

    }

}
