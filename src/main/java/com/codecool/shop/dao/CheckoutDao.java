package com.codecool.shop.dao;

import com.codecool.shop.model.Checkout;

import java.util.List;

public interface CheckoutDao {

    void add(Checkout checkout);

    List<Checkout> getAll();
}
