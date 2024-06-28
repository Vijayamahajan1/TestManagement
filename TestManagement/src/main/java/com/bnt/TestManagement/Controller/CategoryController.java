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
import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Service.CategoryServiceImpl;

@RestController
@RequestMapping("/Category")
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    @PostMapping
     public ResponseEntity<Category> createCategory(@RequestBody Category category){
       Category createdCategory= categoryService.createCategory(category);
       return new ResponseEntity<Category>(createdCategory,HttpStatus.OK);
     }

     @GetMapping
     public ResponseEntity<List<Category>> getAllCategory(){
      List<Category> getedCategory= categoryService.getAllCategory();
      return new ResponseEntity<List<Category>>(getedCategory,HttpStatus.OK);
     }
      
     @GetMapping("/{id}")
     public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable ("id")Long id){
      Optional<Category> getedCategory= categoryService.getCategoryById(id);
      return new ResponseEntity<Optional<Category>>(getedCategory,HttpStatus.OK);
     }
     @PutMapping
     public ResponseEntity<Category> updateCategory(@RequestBody Category newCategory){
      Category newcategory = categoryService.updateCategory(newCategory);
      return new ResponseEntity<Category>(newcategory , HttpStatus.OK);
     }
     @DeleteMapping("/{id}")
     public ResponseEntity<Object> deleteCategory(@PathVariable ("id") Long id){
       categoryService.deleteCategory(id);
       return new ResponseEntity<Object>("Category deleted Successuly", HttpStatus.OK);
     }
    
}
