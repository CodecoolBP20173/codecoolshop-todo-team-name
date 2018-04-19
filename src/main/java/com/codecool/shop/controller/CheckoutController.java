package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CheckoutDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.CheckoutDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    CheckoutDao checkoutList = CheckoutDaoMem.getInstance();
    List<Object> orderInfo = new ArrayList<>();

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

        checkoutList.add(name);
        checkoutList.add(email);
        checkoutList.add(phoneNumber);
        checkoutList.add(billCountry);
        checkoutList.add(billCity);
        checkoutList.add(billZipcode);
        checkoutList.add(billAddress);
        checkoutList.add(shipCountry);
        checkoutList.add(shipCity);
        checkoutList.add(shipZipcode);
        checkoutList.add(shipAddress);

        orderInfo.add(checkoutList);
        
        response.sendRedirect("payment");

    }
}
