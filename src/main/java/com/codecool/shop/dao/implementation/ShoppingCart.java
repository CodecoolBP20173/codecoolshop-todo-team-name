package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private static List<Product> cartProducts = new ArrayList<>();
    public static List<String> checkoutList = new ArrayList<>();

    public static List<Product> getAll(){
        return cartProducts;
    }

    private static Product getById(Integer id){
        return cartProducts.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public static void add(Product product){
        //product.setId(cartProducts.size() + 1);
        cartProducts.add(product);
    }

//    public static void remove(Integer id){
//        cartProducts.remove(getById(id));
//    }
}
