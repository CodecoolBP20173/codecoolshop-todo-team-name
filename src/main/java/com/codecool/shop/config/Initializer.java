package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        /*Supplier bme = new Supplier("BME", "University");
        supplierDataStore.add(bme);
        Supplier elte = new Supplier("ELTE", "University");
        supplierDataStore.add(elte);
        Supplier corvinus = new Supplier("BCE", "University");
        supplierDataStore.add(corvinus);
        Supplier pazmany = new Supplier("PPKE", "University");
        supplierDataStore.add(pazmany);
        Supplier miskolcu = new Supplier("ME", "University");
        supplierDataStore.add(miskolcu);
        Supplier bolyai = new Supplier("BBTE", "University");
        supplierDataStore.add(bolyai);
        Supplier unknown = new Supplier("Unkown", "University unknown");
        supplierDataStore.add(unknown);*/

        //setting up a new product category
        /*ProductCategory progbasic = new ProductCategory("Prog Basic Module", "Programming Basics", "First module of Codecool.");
        productCategoryDataStore.add(progbasic);
        ProductCategory web = new ProductCategory("Web Module", "Web", "Second module of Codecool.");
        productCategoryDataStore.add(web);
        ProductCategory oop = new ProductCategory("OOP Module", "Java/OOP", "Third module of Codecool. Object Oriented programming.");
        productCategoryDataStore.add(oop);
        ProductCategory advanced = new ProductCategory("Advanced Module", "Advanced", "Fourth module of Codecool.");
        productCategoryDataStore.add(advanced);
        ProductCategory softskill = new ProductCategory("Soft Skills", "Soft skill", "Soft skills.");
        productCategoryDataStore.add(softskill);*/

        //setting up products and printing it
        //Prog basics
       /* productDataStore.add(new Product("Rudi Szabo", 10, "CC", "I can help you with these topics: Scratch, Python, OOP, SQL, Web (HTML+CSS+JS), PHP, (Android).", progbasic, bme));
        productDataStore.add(new Product("Immanuel Fodor", 10, "CC", "I can help you with these topics:, Scratch, Python, OOP, Web (HTML+CSS+JS), Java SE, Advanced PostgreSQL, PHP, Related frameworks, tools (ORM, Git, Scrum, etc), Project management, Productivity, Bitcoin.", progbasic, elte));
        productDataStore.add(new Product("Reka Tobak", 10, "CC", "I'm familiar with these topics: Python, OOP, Java SE, Clean code, C++.", progbasic, bolyai));
*/
        //Web
        /*productDataStore.add(new Product("Daniel Molnar", 15, "CC", "I can mainly help you with Python and Web related stuff, but I'm open for any topics. Except Microsoft stuff, I have closed-mindset about that. Before Codecool, I worked as PHP dev, Front-End dev and Project Manager. I worked with different frameworks, like Yii, Angular, Electron.", web, unknown));
        productDataStore.add(new Product("Laszlo Terray", 15, "CC", "I'm familiar with these topics: (Hard skills)Python, OOP, Web (HTML+CSS+JS), Java SE, SQL, PHP, Clean code (Soft skills)Presetation, Team work, Feedback giving, Time management, Growth mindset, Anything which is important for you.", web, elte));
        productDataStore.add(new Product("Arpad Torzsok", 15, "CC", "I can help you with these topics: Scratch, Python, SQL, anything data related, data career questions, personal issues.", web, corvinus));
*/
        //OOP
       /* productDataStore.add(new Product("Bence Fabian", 100, "CC", "I can help you with these topics: Python, Java, C/C++, SW testing, Programming in general (design / clean code), Git, Programming languages, Systems programming, Networks, Unix (Linux and OS X are both Unix-based), Containers (namespaces, cgroups, docker, firejail), Graphics / audio programming.", oop, elte));
        productDataStore.add(new Product("Zoltan Sallay", 100, "CC", "I can help you with these topics: Java SE / EE, Algorithms, SQL, Python, OOP, Web (HTML+CSS+JS).", oop, elte));
        productDataStore.add(new Product("Balazs Farago", 100, "CC", "I'm Balazs Farago but you can call me Intergalactic Spacelord of the Known and Unknown Universe, or just Balazs.", oop, pazmany));
*/
        //Advanced
       /* productDataStore.add(new Product("Miki Beothy", 20, "CC", "Hard (tech skills): Scratch, Python, SQL, JavaScript / HTML (no CSS), Java SE, OOP programming, Clean Code, design patterns, Ruby, Ruby on Rails, Networks, protocols, Git, MacOS, Linux Soft skills: learning/motivational issues, presentational skills (prepare for a meetup/conference for example), communication skills, time management, teamwork issues, career advices", advanced, bme));
        productDataStore.add(new Product("Matyas Forian Szabo", 20, "CC", "I can help you with these topics:, Java SE, Android programming, mobile programming, C#, Xamarin, Python, OOP, Web (HTML+CSS+JS), OpenGL, graphics programming, Frameworks, tools that we learn at Codecool (ORM, Git, Scrum, etc).", advanced, pazmany));
        productDataStore.add(new Product("Attila Molnar", 20, "CC", "I can help you with the following topics: Scratch, Python (+flask), Web (HTML, JS, minimal CSS), OOP, PostgreSQL, MS SQL, C#, ASP.NET, Entity Framework, WinForms - Related frameworks, tools (ORM, Git, Scrum, etc), Project management, Productivity tools", advanced, miskolcu));
*/
        //Soft skills
/*
        productDataStore.add(new Product("David Nadas", 12.5f, "CC", "Softskills, no szamitozas", softskill, elte));
*/
    }
}
