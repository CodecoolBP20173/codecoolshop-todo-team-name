package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderDaoMem implements OrderDao {

    private List<Product> cartProducts;
    private List<Integer> productNum;
    private List<String> subtotals;
    private static HashMap<String, OrderDaoMem> orders;

    OrderDaoMem() {
        cartProducts = new ArrayList<>();
        productNum = new ArrayList<>();
        subtotals = new ArrayList<>();
    }

    public static OrderDaoMem getInstance(String id) {
        if (orders == null) {
            orders = new HashMap<String, OrderDaoMem>();
        }
        if (!orders.containsKey(id)) {
            orders.put(id, new OrderDaoMem());
        }
        return orders.get(id);
    }

    @Override
    public List<Integer> getQuantities() {
        return productNum;
    }

    @Override
    public void add(Product product) {
        boolean addProduct = true;
        for (int i = 0; i < cartProducts.size(); i++) {
            if (cartProducts.get(i).getId() == product.getId()) {
                addProduct = false;
                productNum.set(i, productNum.get(i) + 1);
                break;
            }

        }
        if (addProduct) {
            cartProducts.add(product);
            productNum.add(1);
        }
    }

    @Override
    public void remove(Product product) {
        for (int i = 0; i < cartProducts.size(); i++) {
            if (cartProducts.get(i).getId() == product.getId()) {
                productNum.set(i, productNum.get(i) - 1);
            }
            if (cartProducts.get(i).getId() == product.getId() && productNum.get(i) == 0) {
                cartProducts.remove(cartProducts.get(i));
                productNum.remove(productNum.get(i));
            }
        }
    }

    @Override
    public void delete(Product product) {

        for (int i = 0; i < cartProducts.size(); i++) {
            if (cartProducts.get(i).getId() == product.getId()) {
                productNum.remove(productNum.get(i));
                cartProducts.remove(cartProducts.get(i));
            }
        }
    }

    @Override
    public List<Product> getAll() {
        return cartProducts;
    }

    @Override
    public int getProductNum() {
        int sum = 0;
        for (int i : productNum) {
            sum += i;
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
            sum += cartProducts.get(i).getDefaultPrice() * productNum.get(i);
        }

        BigDecimal bd = new BigDecimal(Double.toString(sum));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
