package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
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

        OrderController.checkoutList.add(name);
        OrderController.checkoutList.add(email);
        OrderController.checkoutList.add(phoneNumber);
        OrderController.checkoutList.add(billCountry);
        OrderController.checkoutList.add(billCity);
        OrderController.checkoutList.add(billZipcode);
        OrderController.checkoutList.add(billAddress);
        OrderController.checkoutList.add(shipCountry);
        OrderController.checkoutList.add(shipCity);
        OrderController.checkoutList.add(shipZipcode);
        OrderController.checkoutList.add(shipAddress);

    }
}
