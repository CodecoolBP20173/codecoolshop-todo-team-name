package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.model.Checkout;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutDaoJdbcTest {

    @Test
    public void testIsElementAddedToDatabase() {
        Checkout testCheckout = new Checkout(1, "Test Name", "test@eamil.com", "30601234567", "Testcountry", "Testcity", "1000", "Test Address 420", "Testcountry", "Testcity", "1000", "Test Address 420");
        CheckoutDaoJdbc database = CheckoutDaoJdbc.getInstance();
        database.add(testCheckout);
    }

    @Test
    public void testGetAllElementFromDatabase() {
        CheckoutDaoJdbc database = CheckoutDaoJdbc.getInstance();
        System.out.println(database.getAll());
    }

}