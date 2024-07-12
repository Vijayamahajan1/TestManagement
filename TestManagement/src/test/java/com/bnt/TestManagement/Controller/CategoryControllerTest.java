package com.bnt.TestManagement.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Service.ServiceImplementation.CategoryServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {
    @Mock
    CategoryServiceImpl categoryService;

    @InjectMocks
    CategoryController categoryController;

    @Test
    void createCategoryTest(){
        Category expcategory = new Category(1l, "java", "Core Java category");
        when(categoryService.createCategory(expcategory)).thenReturn(expcategory);
        ResponseEntity<Category> Actualcategory = categoryController.createCategory(expcategory);
         assertEquals(HttpStatus.OK, Actualcategory.getStatusCode());
         assertEquals(expcategory, Actualcategory.getBody());
    }

    @Test
    void getAllCategoryTest(){
        List<Category> expcategory = new ArrayList<>();
        expcategory.add(new Category(1l, "java", "Core Java category"));
        expcategory.add(new Category(2l, "springboot", "Core Java category"));
        when(categoryService.getAllCategory()).thenReturn(expcategory);
        ResponseEntity<List<Category>> Actualcategory = categoryController.getAllCategory();
         assertEquals(HttpStatus.OK, Actualcategory.getStatusCode());
         assertEquals(expcategory, Actualcategory.getBody());
    }

    @Test
    void getCategoryByIdTest(){
        Long categoryId = 1L;
        Category expcategory = new Category();
        expcategory.setCategoryId(categoryId);
        Optional<Category> optionalCategory = Optional.of(expcategory);
        when(categoryService.getCategoryById(anyLong())).thenReturn(optionalCategory);
        ResponseEntity<Optional<Category>> responseEntity = categoryController.getCategoryById(categoryId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(optionalCategory, responseEntity.getBody());
    }

    @Test
    void updateCategoryTest(){
        Category expcategory = new Category(1l, "java", "Core Java category");
        when(categoryService.updateCategory(expcategory)).thenReturn(expcategory);
        ResponseEntity<Category> Actualcategory = categoryController.updateCategory(expcategory);
         assertEquals(HttpStatus.OK, Actualcategory.getStatusCode());
         assertEquals(expcategory, Actualcategory.getBody());  
    }

    @Test
    void deleteCategoryTest(){
         Long id=1l;
        doNothing().when(categoryService).deleteCategory(id);
        ResponseEntity<Object> ActualResponseEntity = categoryController.deleteCategory(id);
        assertEquals(HttpStatus.OK, ActualResponseEntity.getStatusCode());
        assertEquals("Category deleted Successuly", ActualResponseEntity.getBody());
    }
//>>-----------------------------------------Negative Test Cases--------------------------------------->>

    @Test
    void createCategoryTest_IllegalArgumentException() {
        Category invalidCategory = new Category();
        invalidCategory.setCategoryName(null);;
        when(categoryService.createCategory(any(Category.class))).thenThrow(new IllegalArgumentException("Invalid category data"));
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        categoryController.createCategory(invalidCategory));
        assertEquals("Invalid category data", exception.getMessage());
    }

    @Test
    void getCategoryAllTest_ServerError() {
        doThrow(new RuntimeException("Server Error")).when(categoryService).getAllCategory();
        Exception exception = assertThrows(RuntimeException.class, () -> 
        categoryController.getAllCategory());
        assertEquals("Server Error", exception.getMessage());
    }

    @Test
    void getCategoryIdTest_CategoryNotFound() {
        Long id = 1L;
        doThrow(new RuntimeException("Category not found")).when(categoryService).getCategoryById(id);
        Exception exception = assertThrows(RuntimeException.class, () -> 
        categoryController.getCategoryById(id));
        assertEquals("Category not found", exception.getMessage());
    }

    @Test
    void updateCategoryTest_InvalidCategory() {
        Category invalidCategory = new Category(1L, null, "No name"); // Assuming name is required
        when(categoryService.updateCategory(invalidCategory)).thenThrow(new IllegalArgumentException("Invalid category data"));
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        categoryController.updateCategory(invalidCategory));
        assertEquals("Invalid category data", exception.getMessage());
    }

    @Test
    void deleteCategoryTest_CategoryNotFound() {
        Long id = 1L;
        doThrow(new RuntimeException("Category not found")).when(categoryService).deleteCategory(id);
        Exception exception = assertThrows(RuntimeException.class, () -> 
        categoryController.deleteCategory(id));
        assertEquals("Category not found", exception.getMessage());
    }
}    

