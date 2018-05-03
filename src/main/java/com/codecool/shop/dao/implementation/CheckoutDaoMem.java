package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CheckoutDao;
import com.codecool.shop.model.Checkout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckoutDaoMem implements CheckoutDao {

    private List<Checkout> checkoutList;
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
    public void add(Checkout checkout) {
        checkoutList.add(checkout);
    }

    @Override
    public List<Checkout> getAll() {
        return checkoutList;
    }
}
