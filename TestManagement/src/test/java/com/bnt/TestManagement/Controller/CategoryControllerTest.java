package com.bnt.TestManagement.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
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
}
