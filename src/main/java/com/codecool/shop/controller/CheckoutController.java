package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.ShoppingCart;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("product/checkout.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String phoneNumber = request.getParameter("telephone");
    String billCountry = request.getParameter("billcountry");
    String billCity = request.getParameter("billcity");
    String billZipcode = request.getParameter("billzipcode");
    String billAddress = request.getParameter("billaddress");
    String shipCountry = request.getParameter("shipcountry");
    String shipCity = request.getParameter("shipcity");
    String shipZipcode = request.getParameter("shipzipcode");
    String shipAddress = request.getParameter("shipaddress");

    ShoppingCart.checkoutList.add(name);
    ShoppingCart.checkoutList.add(email);
    ShoppingCart.checkoutList.add(phoneNumber);
    ShoppingCart.checkoutList.add(billCountry);
    ShoppingCart.checkoutList.add(billCity);
    ShoppingCart.checkoutList.add(billZipcode);
    ShoppingCart.checkoutList.add(billAddress);
    ShoppingCart.checkoutList.add(shipCountry);
    ShoppingCart.checkoutList.add(shipCity);
    ShoppingCart.checkoutList.add(shipZipcode);
    ShoppingCart.checkoutList.add(shipAddress);

    }
}
