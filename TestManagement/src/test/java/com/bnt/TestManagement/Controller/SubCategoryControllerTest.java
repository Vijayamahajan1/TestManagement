package com.bnt.TestManagement.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Model.SubCategory;
import com.bnt.TestManagement.Service.ServiceImplementation.SubCategoryServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SubCategoryControllerTest {
    @Mock
    SubCategoryServiceImpl subCategoryService;

    @InjectMocks
    SubCategoryController subCategoryController;
    
    static SubCategory createSampleSubCategory() {
        Category category = new Category(1l, "Java", "Core Java category");
        return new SubCategory(1l, category, "Collections", "Collections from Java");
    }

  @Test
    void CreateSubCategorytest() {
        SubCategory expectedSubCategory = createSampleSubCategory();
        when(subCategoryService.createSubCategory(any(SubCategory.class))).thenReturn(expectedSubCategory);
        ResponseEntity<SubCategory> responseEntity = subCategoryController.createSubCategory(expectedSubCategory);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedSubCategory, responseEntity.getBody());
    }

    @Test
    void getAllCategoryTest() {
    List<SubCategory> expCategories = new ArrayList<>();
    expCategories.add(createSampleSubCategory());
    when(subCategoryService.getAllSubCategory()).thenReturn(expCategories);
    ResponseEntity<List<SubCategory>> responseEntity =subCategoryController.getAllSubCategory();
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(expCategories, responseEntity.getBody());
}

    @Test
    void getSubCategoryByIdTest(){
        Long subCategoryId = 1L;
        SubCategory expectedSubCategory = new SubCategory();
        expectedSubCategory.setSubcategory_id(subCategoryId);
        Optional<SubCategory> optionalSubCategory = Optional.of(expectedSubCategory);
        when(subCategoryService.getSubCategoryById(anyLong())).thenReturn(optionalSubCategory);
        ResponseEntity<Optional<SubCategory>> responseEntity = subCategoryController.getSubCategoryById(subCategoryId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(optionalSubCategory, responseEntity.getBody());
    }

    @Test
    void updateSubCategorytest() {
        SubCategory expectedSubCategory = createSampleSubCategory();
        when(subCategoryService.updateSubCategory(any(SubCategory.class))).thenReturn(expectedSubCategory);
        ResponseEntity<SubCategory> responseEntity = subCategoryController.updateSubcategory(expectedSubCategory);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedSubCategory, responseEntity.getBody());
    }
 
      @Test
    void deleteSubCategoryTest(){
         Long id=1l;
        doNothing().when(subCategoryService).deleteSubCategory(id);
        ResponseEntity<Object> ActualResponseEntity = subCategoryController.deleteSubCategory(id);
        assertEquals(HttpStatus.OK, ActualResponseEntity.getStatusCode());
        assertEquals("Subcategory delted Successfully", ActualResponseEntity.getBody());
    }

    //>>-----------------------------------------Negative Test Cases--------------------------------------->>

    @Test
    void createSubCategoryTest_IllegalArgumentException() {
        SubCategory invalidSubCategory = new SubCategory();
        invalidSubCategory.setSubcategoryName(null);;
        when(subCategoryService.createSubCategory(any(SubCategory.class))).thenThrow(new IllegalArgumentException("Invalid subcategory data"));
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        subCategoryController.createSubCategory(invalidSubCategory));
        assertEquals("Invalid subcategory data", exception.getMessage());
    }

    @Test
    void getSubCategoryAllTest_ServerError() {
        doThrow(new RuntimeException("Server Error")).when(subCategoryService).getAllSubCategory();
        Exception exception = assertThrows(RuntimeException.class, () -> 
        subCategoryController.getAllSubCategory());
        assertEquals("Server Error", exception.getMessage());
    }

    @Test
    void getSubCategoryIdTest_SubCategoryNotFound() {
        Long id = 1L;
        doThrow(new RuntimeException("SubCategory not found")).when(subCategoryService).getSubCategoryById(id);
        Exception exception = assertThrows(RuntimeException.class, () -> 
        subCategoryController.getSubCategoryById(id));
        assertEquals("SubCategory not found", exception.getMessage());
    }

    @Test
    void updateSubCategoryTest_InvalidSubCategory() {
        SubCategory subCategory = new SubCategory(); 
        subCategory.setSubcategoryName(null);
        subCategory.setSubcategoryDescription("Collections from java");
        when(subCategoryService.updateSubCategory(subCategory)).thenThrow(new IllegalArgumentException("Invalid subcategory data"));
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        subCategoryController.updateSubcategory(subCategory));
        assertEquals("Invalid subcategory data", exception.getMessage());
    }

    @Test
    void deleteSubCategoryTest_SubCategoryNotFound() {
        Long id = 1L;
        doThrow(new RuntimeException("SubCategory not found")).when(subCategoryService).deleteSubCategory(id);
        Exception exception = assertThrows(RuntimeException.class, () -> 
        subCategoryController.deleteSubCategory(id));
        assertEquals("SubCategory not found", exception.getMessage());
    }
}    



