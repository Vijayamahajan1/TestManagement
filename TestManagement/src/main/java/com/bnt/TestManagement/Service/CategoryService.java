package com.bnt.TestManagement.Service;

import java.util.List;
import java.util.Optional;

import com.bnt.TestManagement.Model.Category;

public interface CategoryService {
 public Category createCategory(Category category);

 public List<Category> getAllCategory();

 public Optional<Category> getCategoryById(Long id);

  public Category updateCategory(Category newcategory);

  public void deleteCategory(Long id);
}
