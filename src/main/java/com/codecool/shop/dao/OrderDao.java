package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    void add(Product product, int userId); //Adds 1 product to cart or increases its number

    void remove(Product product, int userId); //Removes 1 product from cart or decreases its number

    void delete(Product product, int userId); //Removes every instance of the product from cart

    Map<Product, Integer> getAll(int userId); //Returns all the products in the cart and their number

    int getProductNum(int userId); //Returns the total quantity of products in cart

    String getSumOfPrices(int userId); //Returns the Total price with the currency

    double sumOfPrices(int userId); //Returns the Total price without currency
}
