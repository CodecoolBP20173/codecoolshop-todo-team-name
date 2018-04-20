package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CheckoutDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckoutDaoMem implements CheckoutDao {

    private List<String> checkoutList;
    private static HashMap<String, CheckoutDaoMem> instance;

    private CheckoutDaoMem() {
        checkoutList = new ArrayList<>();
    }

    public static CheckoutDaoMem getInstance(String id) {
        if (instance == null) {
            instance = new HashMap<String, CheckoutDaoMem>();
        }
        if (!instance.containsKey(id)) {
            instance.put(id, new CheckoutDaoMem());
        }
        return instance.get(id);
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
