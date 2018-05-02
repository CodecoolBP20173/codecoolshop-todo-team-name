package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {

    ProductDao productDao = ProductDaoMem.getInstance();
    Supplier testSupplier = new Supplier("test", "description");
    ProductCategory testProductCat = new ProductCategory("name", "department", "description");
    Product testProduct = new Product("test", 1, "USD", "description", testProductCat, testSupplier);

    @BeforeEach
    public void init() {
        productDao.add(testProduct);
    }

    @AfterEach
    public void after() {
        productDao.remove(1);
    }


    @Test
    public void testIsProductDaoNotNull() {
        assertNotNull(productDao);
    }

    @Test
    public void testIsProductNotNull() {
        List<Product> products = productDao.getAll();
        assertNotNull(products.get(0));
    }

    @Test
    public void testIsProductAdded() {
        productDao.add(new Product("test", 2, "USD", "description", new ProductCategory("name", "department", "description"), new Supplier("test", "description")));
        List<Product> products = productDao.getAll();
        assertEquals(2, products.get(products.size() - 1).getId());
        productDao.remove(products.get(products.size() - 1).getId());
    }

    @Test
    public void testIsProductFound() {
        List<Product> products = productDao.getAll();
        assertEquals(products.get(0), productDao.find(products.get(0).getId()));
    }

    @Test
    public void testIsProductRemoved() {
        Product p = new Product("test", 4, "USD", "description", new ProductCategory("name", "department", "description"), new Supplier("test", "description"));
        productDao.add(p);
        int id = p.getId();
        productDao.remove(id);
        assertNull(productDao.find(id));
    }

    @Test
    public void testAreAllProductsGot() {
        assertEquals(1, productDao.getAll().size());
    }

    @Test
    public void testIsProductGotBySupplier() {
        assertEquals(testProduct, productDao.getBy(testSupplier).get(0));
    }

    @Test
    public void testIsProductGotByCategory() {
        assertEquals(testProduct, productDao.getBy(testProductCat).get(0));
    }
}