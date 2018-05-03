package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.CheckoutDaoMem;
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
        /*String data = "Test information";
        String result = "Test information";
        checkoutDataStore.add(data);
        assertEquals(result, checkoutDataStore.getAll().get(checkoutDataStore.getAll().size()-1));*/
    }

    @Test
    public void testIsGotAll() {
        /*String data = "Test information 2";
        List<String> resultList = new ArrayList<>(Arrays.asList("Test information", "Test information 2"));
        checkoutDataStore.add(data);
        assertEquals(resultList, checkoutDataStore.getAll());*/
    }

}