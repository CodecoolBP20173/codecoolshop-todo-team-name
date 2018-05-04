package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {

    private static ProductDaoJdbc instance = null;

    private ProductDaoJdbc() {
    }

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }

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
            stmt.setFloat(3, product.getDefaultPrice());
            stmt.setString(4, product.getDefaultCurrency());
            stmt.setInt(5, product.getProductCategory().getId());
            stmt.setInt(6, product.getSupplier().getId());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.print(e.getMessage());

        } finally {
            closeStatementAndConnection(connection, stmt);
        }
    }

    public Product find(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;

        Product product = new Product(
                "",
                0,
                "",
                "",
                new ProductCategory("", "", ""),
                new Supplier("", "")
        );

        String query =
                "SELECT * FROM product WHERE id = ?";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer productId = rs.getInt("id");
                String productName = rs.getString("name");
                String productDescription = rs.getString("description");
                Integer productPrice = rs.getInt("price");

                Integer productCategoryId = rs.getInt("productcategoryid");
                ProductCategoryDao productCategoryDaoJdbc = ProductCategoryDaoJdbc.getInstance();
                ProductCategory productCategory = productCategoryDaoJdbc.find(productCategoryId);

                Integer productSupplierId = rs.getInt("supplierid");
                SupplierDao supplierDaoJdbc = SupplierDaoJdbc.getInstance();
                Supplier supplier = supplierDaoJdbc.find(productSupplierId);

                product.setId(productId);
                product.setName(productName);
                ((Product) product).setPrice(productPrice, "CC");
                product.setDescription(productDescription);
                ((Product) product).setProductCategory(productCategory);
                ((Product) product).setSupplier(supplier);
            }

        } catch (Exception e) {
            System.out.print(e.getMessage());

        } finally {
            closeStatementAndConnection(connection, stmt);
        }

        return product;
    }

    public void remove(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;

        String query =
                "DELETE FROM product WHERE id = ?";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeStatementAndConnection(connection, stmt);
        }
    }

    public List<Product> getAll() {
        Connection connection = null;
        PreparedStatement stmt = null;

        List<Product> allProducts = new ArrayList<>();

        String query =
                "SELECT * FROM product";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                Product product = new Product(
                        "",
                        0,
                        "",
                        "",
                        new ProductCategory("", "", ""),
                        new Supplier("", "")
                );

                Integer productCategoryId = resultSet.getInt("productcategoryid");
                ProductCategoryDao productCategoryDaoJdbc = ProductCategoryDaoJdbc.getInstance();
                ProductCategory productCategory = productCategoryDaoJdbc.find(productCategoryId);

                Integer productSupplierId = resultSet.getInt("supplierid");
                SupplierDao supplierDaoJdbc = SupplierDaoJdbc.getInstance();
                Supplier supplier = supplierDaoJdbc.find(productSupplierId);

                Integer productId = resultSet.getInt("id");
                String productName = resultSet.getString("name");
                String productDescription = resultSet.getString("description");
                Integer productPrice = resultSet.getInt("price");

                product.setId(productId);
                product.setName(productName);
                ((Product) product).setPrice(productPrice, "CC");
                product.setDescription(productDescription);
                ((Product) product).setProductCategory(productCategory);
                ((Product) product).setSupplier(supplier);

                allProducts.add(product);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeStatementAndConnection(connection, stmt);
        }

        return allProducts;
    }

    public List<Product> getBy(Supplier supplier) {
        Connection connection = null;
        PreparedStatement stmt = null;

        List<Product> filteredProducts = new ArrayList<>();

        int id = supplier.getId();

        String query =
                "SELECT * FROM product WHERE supplierid = ?";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                Product product = new Product(
                        "",
                        0,
                        "",
                        "",
                        new ProductCategory("", "", ""),
                        new Supplier("", "")
                );

                Integer productCategoryId = resultSet.getInt("productcategoryid");
                ProductCategoryDao productCategoryDaoJdbc = ProductCategoryDaoJdbc.getInstance();
                ProductCategory productCategory = productCategoryDaoJdbc.find(productCategoryId);

                Integer productId = resultSet.getInt("id");
                String productName = resultSet.getString("name");
                String productDescription = resultSet.getString("description");
                Integer productPrice = resultSet.getInt("price");

                product.setId(productId);
                product.setName(productName);
                ((Product) product).setPrice(productPrice, "CC");
                product.setDescription(productDescription);
                ((Product) product).setProductCategory(productCategory);
                ((Product) product).setSupplier(supplier);

                filteredProducts.add(product);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeStatementAndConnection(connection, stmt);
        }

        return filteredProducts;
    }

    public List<Product> getBy(ProductCategory productCategory) {
        Connection connection = null;
        PreparedStatement stmt = null;

        List<Product> filteredProducts = new ArrayList<>();

        int id = productCategory.getId();

        String query =
                "SELECT * FROM product WHERE productcategoryid = ?";

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                Product product = new Product(
                        "",
                        0,
                        "",
                        "",
                        new ProductCategory("", "", ""),
                        new Supplier("", "")
                );

                Integer productSupplierId = resultSet.getInt("supplierid");
                SupplierDao supplierDaoJdbc = SupplierDaoJdbc.getInstance();
                Supplier supplier = supplierDaoJdbc.find(productSupplierId);

                Integer productId = resultSet.getInt("id");
                String productName = resultSet.getString("name");
                String productDescription = resultSet.getString("description");
                Integer productPrice = resultSet.getInt("price");

                product.setId(productId);
                product.setName(productName);
                ((Product) product).setPrice(productPrice, "CC");
                product.setDescription(productDescription);
                ((Product) product).setProductCategory(productCategory);
                ((Product) product).setSupplier(supplier);

                filteredProducts.add(product);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeStatementAndConnection(connection, stmt);
        }

        return filteredProducts;
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
