package com.bnt.TestManagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.TestManagement.Model.SubCategory;
import com.bnt.TestManagement.Repository.SubCategoryRepository;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    SubCategoryRepository subCategoryRepository;
    @Override
    public SubCategory createSubCategory(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public List<SubCategory> getAllSubCategory() {
        return subCategoryRepository.findAll();
    }

    @Override
    public Optional<SubCategory> getSubCategoryById(Long id) {
        return subCategoryRepository.findById(id);
    }
    @Override
    public SubCategory updateSubCategory(SubCategory newSubCategory) {
        return subCategoryRepository.save(newSubCategory);
    }
   
    @Override
    public void deleteSubCategory(Long id) {
       subCategoryRepository.deleteById(id);
    }  
    
}
