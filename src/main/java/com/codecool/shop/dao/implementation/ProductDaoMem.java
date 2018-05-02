package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoMem implements ProductDao {

    private List<Product> data = new ArrayList<>();
    private static ProductDaoMem instance = null;

    private ProductDaoMem() {
    }

    public static ProductDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        product.setId(getData().size() + 1);
        getData().add(product);
    }

    @Override
    public Product find(int id) {
        return getData().stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        getData().remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        return getData();
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return getData().stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return getData().stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }

    public List<Product> getData() {
        return data;
    }
}
