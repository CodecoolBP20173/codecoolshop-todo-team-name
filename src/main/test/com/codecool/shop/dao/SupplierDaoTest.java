package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoTest {

    SupplierDao supplierDataStore;
    Supplier test = new Supplier("test", "This is a test supplier");
    Supplier test2 = new Supplier("another test", "This is a test supplier");
    int lastIndex;

    @BeforeEach
    public void init() {
        supplierDataStore = SupplierDaoMem.getInstance();
        supplierDataStore.add(test);
        supplierDataStore.add(test2);
        List<Supplier> data;
        data = supplierDataStore.getAll();
        lastIndex = data.size()-1;
    }

    @AfterEach
    public void after() {
        supplierDataStore.remove(1);
        supplierDataStore.remove(2);
    }


    @Test
    public void testIsSupplierAdded() {
        String result = "another test";
        assertEquals(result, supplierDataStore.getAll().get(lastIndex).getName());
    }

    @Test
    public void testIsRightIdFound() {
        String result = "test";
        assertEquals(result, supplierDataStore.find(1).getName());
    }

    @Test
    public void testIsElementRemoved() {
        supplierDataStore.remove(1);
        assertNull(supplierDataStore.find(1));
    }

    @Test
    public void testIsGotAll() {
        int result = 2;
        assertEquals(result, supplierDataStore.getAll().size());
    }

}