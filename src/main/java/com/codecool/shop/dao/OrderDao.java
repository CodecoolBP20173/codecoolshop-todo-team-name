package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.List;

public interface OrderDao {
    void add(Product product); //Adds 1 product to cart or increases its number

    void remove(Product product); //Removes 1 product from cart or decreases its number

    void delete(Product product); //Removes every instance of the product from cart

    List<Product> getAll(); //Returns all the products in the cart

    int getProductNum(); //Returns the total quantity of products in cart

    String getSumOfPrices(); //Returns the Total price with the currency

    double sumOfPrices(); //Returns the Total price without currency
}
