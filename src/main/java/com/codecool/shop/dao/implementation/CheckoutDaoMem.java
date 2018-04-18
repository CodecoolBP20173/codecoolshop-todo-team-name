package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CheckoutDao;

import java.util.ArrayList;
import java.util.List;

public class CheckoutDaoMem implements CheckoutDao {

    private List<String> checkoutList = new ArrayList<>();
    private static CheckoutDaoMem instance = null;

    private CheckoutDaoMem() {
    }

    public static CheckoutDaoMem getInstance() {
        if (instance == null) {
            instance = new CheckoutDaoMem();
        }
        return instance;
    }

    @Override
    public void add(String string) {
        checkoutList.add(string);
    }

    @Override
    public List<String> getAll() {
        return checkoutList;
    }
}
