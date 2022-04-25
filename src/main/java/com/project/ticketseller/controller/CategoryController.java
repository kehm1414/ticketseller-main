package com.project.ticketseller.controller;

import com.project.ticketseller.entity.Category;
import com.project.ticketseller.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

  private CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("/event-categories")
  public String listCategories(Model model) {
    model.addAttribute("categories", categoryService.getAll());
    return "event-categories";
  }

  @GetMapping("event-categories/new")
  public String newCategoryForm(Model model) {
    model.addAttribute("category", new Category());
    return "add-category";
  }

  @PostMapping("/event-categories/new")
  public String saveCategory(@ModelAttribute("category") Category category) {
    categoryService.save(category);
    return "redirect:/event-categories";
  }

  @GetMapping("event-categories/edit/{id}")
  public String editCategoryForm(@PathVariable Long id, Model model) {
    model.addAttribute("category", categoryService.getById(id));
    return "edit-category";
  }

  @PostMapping("event-categories/{id}")
  public String updateCategory(
      @PathVariable Long id, @ModelAttribute("category") Category category) {
    Category existingCategory = categoryService.getById(id);
    existingCategory.setName(category.getName());
    categoryService.save(existingCategory);
    return "redirect:/event-categories";
  }

  @GetMapping("event-categories/{id}")
  public String deleteCategory(@PathVariable Long id) {
    categoryService.deleteById(id);
    return "redirect:/event-categories";
  }
}
