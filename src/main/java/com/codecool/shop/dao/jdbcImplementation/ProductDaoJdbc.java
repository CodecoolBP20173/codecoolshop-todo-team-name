package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {


    public void add(Product product) {
        Connection connection = null;
        PreparedStatement stmt = null;

        String query =
                "INSERT INTO product " +
                        "(name, description, price, currency, productcategoryid, supplierid) " +
                        "VALUES (?, ?, ? ,?, ?, ?)";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setString(3, product.getPrice());
            stmt.setString(4, product.getDefaultCurrency());
            stmt.setInt(5, product.getProductCategory().getId());
            stmt.setInt(6, product.getSupplier().getId());
            stmt.executeUpdate();

        } catch (Exception e) {

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

    public Product find(int id) {
        return null;
    }

    public void remove(int id) {
    }

    public List<Product> getAll() {
        return null;
    }

    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
