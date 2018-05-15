package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoJdbcTest {

    ProductCategoryDao productCategoryDao = ProductCategoryDaoJdbc.getInstance();

    @BeforeEach
    private void init() {}

    @Test
    public void testIsProductAddedToDatabase() {
        int sizeBefore = productCategoryDao.getAll().size();
        ProductCategory test = new ProductCategory("Test", "Soft skill", "Soft skills.");
        productCategoryDao.add(test);
        int sizeAfter = productCategoryDao.getAll().size();
        assertEquals(1, sizeAfter-sizeBefore);
    }

    @Test
    public void testIsProductCategoryFound() {
        ProductCategory test = new ProductCategory("Test","Soft skill", "");
        test.setId(6);
        ProductCategory dbProductCatgory = productCategoryDao.find(6);
        assertEquals(test.getId(), dbProductCatgory.getId());
        assertEquals(test.getDepartment(), dbProductCatgory.getDepartment());
        assertEquals(test.getName(), dbProductCatgory.getName());
        assertEquals(test.getDescription(), dbProductCatgory.getDescription());
    }

    @Test
    public void testIsProductRemovedFromDatabase() {
        int sizeBefore = productCategoryDao.getAll().size();
        productCategoryDao.remove(9);
        int sizeAfter = productCategoryDao.getAll().size();
        assertEquals(1, sizeBefore-sizeAfter);
    }


}