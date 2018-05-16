package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    private static ProductCategoryDaoJdbc instance = null;

    private ProductCategoryDaoJdbc() {
    }

    public static ProductCategoryDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        Connection connection = null;
        PreparedStatement stmt = null;

        String query =
                "INSERT INTO productcategory " +
                        "(name, department, description) " +
                        "VALUES (?, ?, ?)";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDepartment());
            stmt.setString(3, category.getDescription());
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

    @Override
    public ProductCategory find(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;
        String name = null;
        String department = null;
        String description = null;

        String query =
                "SELECT name, department, description FROM productcategory " +
                        "WHERE id=?";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                name = rs.getString("name");
                department = rs.getString("department");
                description = rs.getString("description");
            }
            ProductCategory p = new ProductCategory(name, department, description);
            p.setId(id);
            return p;

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

    @Override
    public void remove(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;

        String query =
                "DELETE FROM productcategory " +
                        "WHERE id=?";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
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

    @Override
    public List<ProductCategory> getAll() {
        Connection connection = null;
        PreparedStatement stmt = null;
        String name = null;
        String department = null;
        String description = null;
        int id;
        List<ProductCategory> productCategory = new ArrayList<>();

        String query =
                "SELECT * FROM productcategory ";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ProductCategory newProductCategory;
            while (rs.next()) {
                name = rs.getString("name");
                department = rs.getString("department");
                description = rs.getString("description");
                id = rs.getInt("id");
                newProductCategory = new ProductCategory(name, department, description);
                newProductCategory.setId(id);
                productCategory.add(newProductCategory);
            }
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

}
