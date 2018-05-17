package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CheckoutDao;
import com.codecool.shop.dao.implementation.CheckoutDaoMem;
import com.codecool.shop.dao.jdbcImplementation.CheckoutDaoJdbc;
import com.codecool.shop.model.Checkout;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session = request.getSession();
        session.setAttribute("customerCheckout", CheckoutDaoJdbc.getInstance());
        CheckoutDao checkoutList = (CheckoutDao) session.getAttribute("customerCheckout");

        Integer userId = (Integer) session.getAttribute("userId");
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
        Checkout checkout = new Checkout(userId, name, email, phoneNumber, billCountry, billCity, billZipcode, billAddress, shipCountry, shipCity, shipZipcode, shipAddress);

        checkoutList.add(checkout);

        response.sendRedirect("payment");

    }
}
