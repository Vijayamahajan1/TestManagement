package com.bnt.TestManagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
       return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
     return categoryRepository.findById(id);
    }

    @Override
    public Category updateCategory(Category newcategory) {
       return categoryRepository.save(newcategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
    
}
