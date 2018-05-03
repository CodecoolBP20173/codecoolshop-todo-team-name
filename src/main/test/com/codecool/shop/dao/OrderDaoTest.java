package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoTest {

    OrderDao orderDao = OrderDaoMem.getInstance("id");
    Supplier testSupplier = new Supplier("test", "description");
    ProductCategory testProductCat = new ProductCategory("name", "department", "description");
    Product testProduct = new Product("test", 1, "USD", "description", testProductCat, testSupplier);

    @BeforeEach
    public void init() {
        orderDao.add(testProduct);
    }

    @AfterEach
    public void after() {
        orderDao.delete(testProduct);
    }

    @Test
    public void testIsProductAdded() {
        assertEquals(testProduct, orderDao.getAll().get(0));
    }


    @Test
    public void testIsProductNumGot() {
        assertEquals(1, orderDao.getProductNum());
    }

    @Test
    public void testIsQuantityGot() {
        orderDao.add(testProduct);
        assertEquals(new Integer(2), orderDao.getQuantities().get(0));
    }

    @Test
    public void testIsProductRemoved() {
        orderDao.add(testProduct);
        assertEquals(2,orderDao.getProductNum());
        orderDao.remove(testProduct);
        assertEquals(1, orderDao.getProductNum());
    }

    @Test
    public void testIsProductDeleted() {
        orderDao.add(testProduct);
        orderDao.delete(testProduct);
        assertEquals(0, orderDao.getProductNum());
    }

    @Test
    public void testAreAllProductGot() {
        assertEquals(1, orderDao.getAll().size());
        orderDao.delete(testProduct);
        assertEquals(0, orderDao.getAll().size());

    }

    @Test
    public void testIsGetSumOfPricesValueCorrect() {
        orderDao.add(testProduct);
        orderDao.add(testProduct);
        assertEquals("3.0 CC", orderDao.getSumOfPrices());
    }

    @Test
    public void testIsSumOfPricesValueCorrect() {
        orderDao.add(testProduct);
        orderDao.add(testProduct);
        assertEquals(3, orderDao.sumOfPrices());
    }

}