package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/cart"})
public class ShoppingCart extends HttpServlet {

    private static List<Product> cartProducts = new ArrayList<>();

    private static Product getById(Integer id) {
        return cartProducts.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public static void add(Product product) {
        //product.setId(cartProducts.size() + 1);
        cartProducts.add(product);
    }

    public static List<Product> getAll() {
        return cartProducts;
    }

    public static int getSize() {
        return cartProducts.size();
    }

    public static double sumOfPrices() {
        double sum = 0;
        for (int i = 0; i < cartProducts.size(); i++) {
            sum+= cartProducts.get(i).getDefaultPrice();
        }
        return sum;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

//        Map params = new HashMap<>();
//        params.put("category", productCategoryDataStore.find(1));
//        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariables(params);
        context.setVariable("recipient", "World");
        context.setVariable("products", this.getAll());
        context.setVariable("size", this.getSize()+ " item(s) in cart");
        context.setVariable("sum", this.sumOfPrices());
        engine.process("product/cart.html", context, resp.getWriter());
    }
}


