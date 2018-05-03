package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoJdbcTest {

    @Test
    public void testIsElementAddedToDatabase() {
        Supplier testSupplier = new Supplier("Test", "Test description");
        SupplierDaoJdbc database = SupplierDaoJdbc.getInstance();
        database.add(testSupplier);
    }

    @Test
    public void testFindElementByID() {
        SupplierDaoJdbc database = SupplierDaoJdbc.getInstance();
        Supplier testSupplier = database.find(2);
        System.out.println(testSupplier);
        assertEquals("ELTE", testSupplier.getName());
    }

    @Test
    public void testRemoveElementByID() {
        SupplierDaoJdbc database = SupplierDaoJdbc.getInstance();
        database.remove(11);
    }

    @Test
    public void testGetAllElementFromDatabase() {
        SupplierDaoJdbc database = SupplierDaoJdbc.getInstance();
        System.out.println(database.getAll());
    }

}