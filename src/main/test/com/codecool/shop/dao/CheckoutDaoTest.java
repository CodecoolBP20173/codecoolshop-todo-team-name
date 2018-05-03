package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.CheckoutDaoMem;
import com.codecool.shop.model.Checkout;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutDaoTest {

    CheckoutDao checkoutDataStore;

    @BeforeEach
    public void init() {
         checkoutDataStore = CheckoutDaoMem.getInstance("1");
    }


    @Test
    public void testIsInformationAdded() {
        int sizeBefore = checkoutDataStore.getAll().size();
        Checkout testCheckout = new Checkout(1, "Test Name", "e@mail.com", "30601234567", "Testcountry", "TestCity", "1000", "Testaddress", "Testcountry", "Testcity", "1000", "Testaddress");
        checkoutDataStore.add(testCheckout);
        int sizeAfter = checkoutDataStore.getAll().size();
        assertEquals(sizeAfter, sizeBefore+1);
    }

    @Test
    public void testIsGotAll() {
        Checkout testCheckout2 = new Checkout(2, "Test Name2", "e@mail.com", "30601234567", "Testcountry2", "TestCity2", "1000", "Testaddress2", "Testcountry2", "Testcity2", "1000", "Testaddress2");
        checkoutDataStore.add(testCheckout2);
        int size = checkoutDataStore.getAll().size();
        assertEquals(size, 2);
    }

}