package com.bnt.TestManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import com.bnt.TestManagement.Model.SubCategory;
import com.bnt.TestManagement.Service.ServiceImplementation.SubCategoryServiceImpl;

@RestController
@RequestMapping("/api/subCategory")
public class SubCategoryController {
    @Autowired
    SubCategoryServiceImpl subCategoryService;

    @PostMapping
    public ResponseEntity<SubCategory> createSubCategory(@RequestBody SubCategory subCategory){
        SubCategory createdSubCategory = subCategoryService.createSubCategory(subCategory);
        return new ResponseEntity<SubCategory>(createdSubCategory,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<SubCategory>> getAllSubCategory(){
        List<SubCategory> getedsubcategories = subCategoryService.getAllSubCategory();
        return new ResponseEntity<List<SubCategory>>(getedsubcategories,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<SubCategory>> getSubCategoryById(@PathVariable ("id") Long id){
        Optional<SubCategory> getSubCategoryById = subCategoryService.getSubCategoryById(id);
        return new ResponseEntity<Optional<SubCategory>>(getSubCategoryById,HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<SubCategory> updateSubcategory(@RequestBody SubCategory newSubCategory){
        SubCategory updatedSubCategory = subCategoryService.updateSubCategory(newSubCategory);
        return new ResponseEntity<SubCategory>(updatedSubCategory,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSubCategory(@PathVariable ("id") Long id){
        subCategoryService.deleteSubCategory(id);
        return new ResponseEntity<Object>("Subcategory delted Successfully", HttpStatus.OK);
    }

}
