package com.codecool.shop.dao;

import com.codecool.shop.dao.jdbcImplementation.OrderDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoTest {

    OrderDao orderDao = OrderDaoJdbc.getInstance();
    Supplier testSupplier = new Supplier("test", "description");
    ProductCategory testProductCat = new ProductCategory("name", "department", "description");
    Product testProduct = new Product("test", 1, "USD", "description", testProductCat, testSupplier);

    @BeforeEach
    public void init() {
        orderDao.add(testProduct,1);
    }

    @AfterEach
    public void after() {
        orderDao.delete(testProduct,1);
    }

    @Test
    public void testIsProductAdded() {
        assertEquals(testProduct, orderDao.getAll(1).get(0));
    }


    @Test
    public void testIsProductNumGot() {
        assertEquals(3, orderDao.getProductNum(1));
    }


    @Test
    public void testIsProductRemoved() {
        orderDao.add(testProduct,1);
        assertEquals(2,orderDao.getProductNum(1));
        orderDao.remove(testProduct,1);
        assertEquals(1, orderDao.getProductNum(1));
    }

    @Test
    public void testIsProductDeleted() {
        orderDao.add(testProduct,1);
        orderDao.delete(testProduct,1);
        assertEquals(0, orderDao.getProductNum(1));
    }

    @Test
    public void testAreAllProductGot() {
        assertEquals(1, orderDao.getAll(1).size());
        orderDao.delete(testProduct,1);
        assertEquals(0, orderDao.getAll(1).size());

    }

    @Test
    public void testIsGetSumOfPricesValueCorrect() {
        orderDao.add(testProduct,1);
        orderDao.add(testProduct,1);
        assertEquals("3.0 CC", orderDao.getSumOfPrices(1));
    }

    @Test
    public void testIsSumOfPricesValueCorrect() {
        orderDao.add(testProduct,1);
        orderDao.add(testProduct,1);
        assertEquals(3, orderDao.sumOfPrices(1));
    }

}