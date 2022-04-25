package com.project.ticketseller.service.impl;

import com.project.ticketseller.entity.Category;
import com.project.ticketseller.repository.CategoryRepository;
import com.project.ticketseller.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

  CategoryRepository categoryRepository;

  @Autowired
  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Category getById(Long id) {
    return categoryRepository.getById(id);
  }

  @Override
  public List<Category> getAll() {
    return categoryRepository.findAll();
  }

  @Override
  public void save(Category category) {
    categoryRepository.save(category);
  }

  @Override
  public void deleteById(Long id) {
    categoryRepository.deleteById(id);
  }
}
