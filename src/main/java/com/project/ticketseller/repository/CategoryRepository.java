package com.project.ticketseller.repository;

import com.project.ticketseller.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
