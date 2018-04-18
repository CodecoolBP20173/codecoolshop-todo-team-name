package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem implements OrderDao {

    private List<Product> cartProducts = new ArrayList<>();
    private static OrderDaoMem instance = null;

    OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        boolean addProduct = true;
        for (Product cartProduct : cartProducts) {
            if (cartProduct.getId() == product.getId()) {
                cartProduct.setQuantity(cartProduct.getQuantity() + 1);
                addProduct = false;
                break;
            }
        }
        if (addProduct) {
            cartProducts.add(product);
        }
    }

    @Override
    public void remove(Product product) {
        for (Product cartProduct : cartProducts) {
            if (cartProduct.getId() == product.getId() && cartProduct.getQuantity() > 1) {
                cartProduct.setQuantity(cartProduct.getQuantity() - 1);
                break;
            }
            if (cartProduct.getId() == product.getId() && cartProduct.getQuantity() == 1) {
                cartProducts.remove(cartProduct);
            }
        }
    }

    @Override
    public void delete(Product product) {
        product.setQuantity(1);
        cartProducts.remove(product);
    }

    @Override
    public List<Product> getAll() {
        return cartProducts;
    }

    @Override
    public int getProductNum() {
        int sum = 0;
        for (Product product:cartProducts) {
            sum += product.getQuantity();
        }
        return sum;
    }

    @Override
    public String getSumOfPrices() {
        return sumOfPrices() + " USD";
    }

    @Override
    public double sumOfPrices() {
        double sum = 0;
        for (int i = 0; i < cartProducts.size(); i++) {
            sum += cartProducts.get(i).getDefaultPrice() * cartProducts.get(i).getQuantity();
        }

        BigDecimal bd = new BigDecimal(Double.toString(sum));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
