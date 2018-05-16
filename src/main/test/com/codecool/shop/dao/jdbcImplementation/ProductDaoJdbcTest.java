package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.jdbcImplementation.ProductDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoJdbcTest {

    @Test
    public void testIsAbleToAddNewProductToDatabase() {
        Supplier bme = new Supplier("KKK", "University");
        ProductCategory progbasic = new ProductCategory("iii", "Programming Basics", "First module of Codecool.");
        Product product = new Product("LOFASZ", 10, "CC", "I can help you with these topics: Scratch, Python, OOP, SQL, Web (HTML+CSS+JS), PHP, (Android).", progbasic, bme);

        ProductDao productDao = ProductDaoJdbc.getInstance();
        productDao.add(product);

    }
}