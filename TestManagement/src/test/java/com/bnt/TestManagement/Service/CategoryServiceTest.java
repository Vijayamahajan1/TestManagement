package com.bnt.TestManagement.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.bnt.TestManagement.Exception.DataIsNotPresent;
import com.bnt.TestManagement.Exception.IdNotFoundException;
import com.bnt.TestManagement.Exception.InvalidDataException;
import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Repository.CategoryRepository;
import com.bnt.TestManagement.Service.ServiceImplementation.CategoryServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryServiceImpl categoryService;

    @Test
    void createCategoryTest(){
        Category expcategory = new Category(1l, "java", "Core Java category");
        when(categoryRepository.save(expcategory)).thenReturn(expcategory);
        Category Actualcategory = categoryService.createCategory(expcategory);
         assertEquals(expcategory, Actualcategory);
    }
     @Test
    void getAllCategoryTest(){
        List<Category> expcategory = new ArrayList<>();
        expcategory.add(new Category(1l, "java", "Core Java category"));
        expcategory.add(new Category(2l, "springboot", "Core Java category"));
        when(categoryRepository.findAll()).thenReturn(expcategory);
        List<Category> Actualcategory = categoryService.getAllCategory();
         assertEquals(expcategory, Actualcategory);

    }

    @Test
    public void GetCategoryById(){
    Long id = 1L;
    when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());
    assertThrows(IdNotFoundException.class, () -> categoryService.getCategoryById(id));
    verify(categoryRepository, times(1)).findById(id);
    }

    @Test
    void updateCategoryTest(){
    Long categoryId = 1L;
    Category existingCategory = new Category(categoryId, "Existing Category", "Existing Description");
    Category updatedCategory = new Category(categoryId, "Updated Category", "Updated Description");
    when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));
    when(categoryRepository.save(any(Category.class))).thenAnswer(invocation -> invocation.getArgument(0));
    Category result = categoryService.updateCategory(updatedCategory);
    assertNotNull(result);
    assertEquals("Updated Category", result.getCategoryName());
    assertEquals("Updated Description", result.getCategoryDescription());
    verify(categoryRepository, times(1)).findById(categoryId);
    verify(categoryRepository, times(1)).save(existingCategory);
}
    
    
     @Test
    void deleteCategoryTest(){
        Long id = 1L;
        Category existingCategory = new Category(id, "Existing Category", "Existing Description");
        when(categoryRepository.findById(id)).thenReturn(Optional.of(existingCategory));
        categoryService.deleteCategory(id);
        verify(categoryRepository, times(1)).findById(id);
        verify(categoryRepository, times(1)).deleteById(id);
    }

    // -----------------------------------------Negative test cases--------------------------------------------------//

    @Test
    void createCategoryTest_InvalidData() {
    Category invalidCategory = new Category(1L, null, "core java directory");
    InvalidDataException thrown = assertThrows(
        InvalidDataException.class,
        () -> categoryService.createCategory(invalidCategory)    
    );
    assertEquals("Data Is Invalid", thrown.getMessage());
   }

    @Test
    void getAllCategoryTest_DataIsNotPresent() {
    List<Category> emptyCategoryList = new ArrayList<>();
    when(categoryRepository.findAll()).thenReturn(emptyCategoryList);  
    DataIsNotPresent thrown = assertThrows(
            DataIsNotPresent.class,
            () -> categoryService.getAllCategory()
        );
        assertEquals("Data is not present", thrown.getMessage());
    }

    @Test
    void getCategoryByIdTest_IdNotFound() {
    Long invalidId = -1L;
    when(categoryRepository.findById(invalidId)).thenReturn(Optional.empty());
    
    IdNotFoundException thrown = assertThrows(
        IdNotFoundException.class,
        () -> categoryService.getCategoryById(invalidId)
    );
    assertEquals("Id Not Found:" + invalidId, thrown.getMessage());
   }


   @Test
   void updateCategoryIdTest_IdNotFound() {
       Category categoryWithInvalidId = new Category(-1L, "Java", "core java directory");
       when(categoryRepository.findById(categoryWithInvalidId.getCategoryId())).thenReturn(Optional.empty());
   
       IdNotFoundException thrown = assertThrows(
           IdNotFoundException.class,
           () -> categoryService.updateCategory(categoryWithInvalidId)
       );
       assertEquals("Id Is Not Present", thrown.getMessage());
   }
   
   @Test
   void deleteCategoryTest_IdNotFound() {
       Long invalidId = -1L;
       when(categoryRepository.findById(invalidId)).thenReturn(Optional.empty());
       
       IdNotFoundException thrown = assertThrows(
           IdNotFoundException.class,
           () -> categoryService.deleteCategory(invalidId)
       );
       assertEquals("Id is Not Found", thrown.getMessage());
   }

   
   


}

