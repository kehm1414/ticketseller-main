package com.project.ticketseller.service;

import com.project.ticketseller.entity.Category;

import java.util.List;

public interface CategoryService {
  Category getById(Long id);

  List<Category> getAll();

  void save(Category category);

  void deleteById(Long id);
}
