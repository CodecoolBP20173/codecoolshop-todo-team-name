package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.jdbcImplementation.OrderDaoJdbc;
import com.codecool.shop.dao.jdbcImplementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.jdbcImplementation.ProductDaoJdbc;
import com.codecool.shop.dao.jdbcImplementation.SupplierDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    ProductDao productDataStore = ProductDaoJdbc.getInstance();
    ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();
    SupplierDao supplierDataStore = SupplierDaoJdbc.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        HttpSession session = req.getSession();
        session.setAttribute("customerOrder", OrderDaoJdbc.getInstance());
        OrderDao cart = (OrderDao) session.getAttribute("customerOrder");

        String type = req.getParameter("type");

        setContent(req, context, type);
        context.setVariable("recipient", "World");
        context.setVariable("itemNum", cart.getProductNum(1));
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());

        engine.process("product/index.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        session.setAttribute("customerOrder", OrderDaoJdbc.getInstance());
        OrderDao cart = (OrderDao) session.getAttribute("customerOrder");

        String action = request.getParameter("action");
        Integer id = Integer.valueOf(request.getParameter("id"));

        if ("add".equals(action)) {
            cart.add(productDataStore.find(id), 1);
        } else if ("remove".equals(action)) {
            cart.remove(productDataStore.find(id),1);
        } else if ("delete".equals(action)) {
            cart.delete(productDataStore.find(id),1);
        } else {
            super.doPost(request, response);
        }
    }

    private void setContent(HttpServletRequest req, WebContext context, String type) {

        if ("category".equals(type)) {
            int categoryId = Integer.parseInt(req.getParameter("id"));

            List<ProductCategory> categories = new ArrayList<>();
            ProductCategory category = productCategoryDataStore.find(categoryId);
            categories.add(category);

            context.setVariable("category", categories);
            context.setVariable("products", productDataStore.getBy(category));

        } else if ("supplier".equals(type)) {
            int supplierID = Integer.parseInt(req.getParameter("id"));

            Supplier supplier = supplierDataStore.find(supplierID);

            List<Product> products = productDataStore.getBy(supplier);
            List<ProductCategory> categories = new ArrayList<>();

            for (Product product : products) {
                ProductCategory category = product.getProductCategory();
                if (!categories.contains(category)) {
                    categories.add(category);
                }
            }
            context.setVariable("category", categories);
            context.setVariable("products", products);

        } else {
            context.setVariable("category", productCategoryDataStore.getAll());
            context.setVariable("products", productDataStore.getAll());
        }
    }
}