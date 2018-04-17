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
    public static List<String> checkoutList = new ArrayList<>();

    private static Product getById(Integer id) {
        return cartProducts.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public static void add(Product product) {
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

    public static void remove (Product product) {
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

    public static void delete (Product product) {
        cartProducts.remove(product);
    }

    public static List<Product> getAll() {
        return cartProducts;
    }

    public static int getProductNum() {
        int num = 0;
        for (Product cartProduct: cartProducts) {
            num += cartProduct.getQuantity();

        }

        return num;
    }

    public static String sumOfPrices() {
        double sum = 0;
        for (int i = 0; i < cartProducts.size(); i++) {
            sum += cartProducts.get(i).getDefaultPrice() * cartProducts.get(i).getQuantity();
        }
        return sum + " USD";
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
        context.setVariable("itemNum", this.getProductNum());
        context.setVariable("sum", this.sumOfPrices());
        engine.process("product/cart.html", context, resp.getWriter());
    }
}


