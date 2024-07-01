package com.bnt.TestManagement.Service;

import java.util.List;
import java.util.Optional;


import com.bnt.TestManagement.Model.SubCategory;

public interface SubCategoryService {

public SubCategory createSubCategory(SubCategory subCategory);

 public List<SubCategory> getAllSubCategory();

 public Optional<SubCategory> getSubCategoryById(Long id);

  public SubCategory updateSubCategory(SubCategory newSubCategory);

  public void deleteSubCategory(Long id);
}
