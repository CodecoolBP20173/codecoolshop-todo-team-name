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
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier ikea = new Supplier("Ikea", "Furnitures");
        supplierDataStore.add(ikea);
        Supplier fromsoftware = new Supplier("FromSoftware", "Videogame developer");
        supplierDataStore.add(fromsoftware);
        Supplier blizzard = new Supplier("Blizzard Entertainment", "Videogame developer");
        supplierDataStore.add(blizzard);
        Supplier whirlpool = new Supplier("Whirlpool", "Washing machines");
        supplierDataStore.add(whirlpool);
        Supplier samsung = new Supplier("Samsung", "Electrical devices");
        supplierDataStore.add(samsung);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory chair = new ProductCategory("Chair", "Comfort", "The thing you sit on.");
        productCategoryDataStore.add(chair);
        ProductCategory videogame = new ProductCategory("Video Game", "Entertainment", "Game softwares, very good freetime activity.");
        productCategoryDataStore.add(videogame);
        ProductCategory washingmachine = new ProductCategory("Washing Machine", "Household", "Makes work around the house easier, helps washing clothes.");
        productCategoryDataStore.add(washingmachine);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));

        productDataStore.add(new Product("Chair", 50, "USD", "A simple chair.", chair, ikea));
        productDataStore.add(new Product("Chair with Pillow", 100, "USD", "A simple chair with a pillow to increase the comfort.", chair, ikea));
        productDataStore.add(new Product("Very Chair", 150, "USD", "The most comfortable chair in the world. Worth every penny you spend on it.", chair, ikea));

        productDataStore.add(new Product("World of Warcraft", 20, "USD", "One of the most successful MMOs.", videogame, blizzard));
        productDataStore.add(new Product("Dark Souls 3", 60, "USD", "If you are up for a challenge this game is made for you.", videogame, fromsoftware));
        productDataStore.add(new Product("Starcraft 2", 55, "USD", "Very popular RTS game withbig e-sport tournaments.", videogame, blizzard));

        productDataStore.add(new Product("Whirlpool WTW7500GC", 599.99f, "USD", "4.8 Cu. Ft. 27-Cycle Top-Loading Washer - Chrome Shadow.", washingmachine, whirlpool));
        productDataStore.add(new Product("Samsung WF42H5000AW", 600, "USD", "4.2 Cu. Ft. 8-Cycle High-Efficiency Front-Loading Washer - White.", washingmachine, samsung));
        productDataStore.add(new Product("Samsung WF45K6500AV", 849.99f, "USD", "4.5 Cu. Ft. 14-Cycle Addwash™ High-Efficiency Front-Loading Washer with Steam - Black stainless steel.", washingmachine, samsung));
    }
}
