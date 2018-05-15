package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.jdbcImplementation.OrderDaoJdbc;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/cart"})
public class OrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setAttribute("customerOrder", OrderDaoJdbc.getInstance());
        OrderDao cart = (OrderDao) session.getAttribute("customerOrder");

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("recipient", "World");
        context.setVariable("products", cart.getAll(1));
        context.setVariable("itemNum", cart.getProductNum(1));
        context.setVariable("sum", cart.getSumOfPrices(1));
        engine.process("product/cart.html", context, resp.getWriter());
    }
}
