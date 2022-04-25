package com.project.ticketseller.entity;

import com.project.ticketseller.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CategoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testCreateCategory(){
        Category category = new Category();
        category.setName("Test");
        Category savedCategory = categoryRepository.save(category);

        Assertions.assertNotNull(savedCategory);
        Assertions.assertEquals(category.getName(), savedCategory.getName());
    }

    @Test
    public void testFindById(){
        Category category = new Category();
        category.setName("Test");
        Category savedCategory = categoryRepository.save(category);

        Assertions.assertNotNull(categoryRepository.findById(savedCategory.getId()));
    }

    @Test
    public void testUpdateCategory(){
        Category category = new Category();
        category.setName("Test");
        Category savedCategory = categoryRepository.save(category);

        savedCategory.setName("TestUpdate");
        Category updatedCategory = categoryRepository.save(savedCategory);

        Assertions.assertEquals(savedCategory.getId(), updatedCategory.getId());
        Assertions.assertEquals("TestUpdate", updatedCategory.getName());
    }

    @Test
    public void testDeleteCategory(){
        Category category = new Category();
        category.setName("Test");
        Category savedCategory = categoryRepository.save(category);

        boolean existedBeforeDelete = categoryRepository.findById(savedCategory.getId()).isPresent();

        categoryRepository.deleteById(savedCategory.getId());

        boolean notExistsAfterDelete = categoryRepository.findById(savedCategory.getId()).isPresent();

        Assertions.assertTrue(existedBeforeDelete);
        Assertions.assertFalse(notExistsAfterDelete);
    }

}