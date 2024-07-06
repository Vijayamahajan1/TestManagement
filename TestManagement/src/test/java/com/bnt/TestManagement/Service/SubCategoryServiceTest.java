package com.bnt.TestManagement.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Model.SubCategory;
import com.bnt.TestManagement.Repository.SubCategoryRepository;
import com.bnt.TestManagement.Service.ServiceImplementation.SubCategoryServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SubCategoryServiceTest {
    @Mock
    SubCategoryRepository subCategoryRepository;

    @InjectMocks
    SubCategoryServiceImpl subCategoryService;

    static SubCategory createSampleSubCategory() {
        Category category = new Category(1l, "Java", "Core Java category");
        return new SubCategory(1l, category, "Collections", "Collections from Java");
    }

    static SubCategory createupdatedSubCategory() {
        Category category = new Category(1l, "Java", "Core Java category");
        return new SubCategory(1l, category, "Exception", "Collections from Java");   
    }

  @Test
    void testcreateSubCategory() {
        SubCategory expectedSubCategory = createSampleSubCategory();
        when(subCategoryRepository.save(any(SubCategory.class))).thenReturn(expectedSubCategory);
        SubCategory ActualSubcategory = subCategoryService.createSubCategory(expectedSubCategory);
        assertEquals(expectedSubCategory,ActualSubcategory);
    }

   @Test
    void testgetAllCategory() {
    List<SubCategory> expCategories = new ArrayList<>();
    expCategories.add(createSampleSubCategory());
    when(subCategoryRepository.findAll()).thenReturn(expCategories);
    List<SubCategory> ActualSubcategory =subCategoryService.getAllSubCategory();
    assertEquals(expCategories, ActualSubcategory);
}

   @Test
    void testgetSubCategoryById_found() {
        Long id = 1L;
        SubCategory expectedSubCategory = createSampleSubCategory();
        when(subCategoryRepository.findById(id)).thenReturn(Optional.of(expectedSubCategory));
        Optional<SubCategory> actualSubCategory = subCategoryService.getSubCategoryById(id);
        assertTrue(actualSubCategory.isPresent());
        assertEquals(expectedSubCategory, actualSubCategory.get());
        verify(subCategoryRepository, times(1)).findById(id);
    }

 @Test
void testUpdateSubCategory_found() {
    Long id = 1L;
    SubCategory existingSubCategory = createSampleSubCategory();
    SubCategory updatedSubCategory = createupdatedSubCategory();
    when(subCategoryRepository.findById(id)).thenReturn(Optional.of(existingSubCategory));
    when(subCategoryRepository.save(existingSubCategory)).thenReturn(updatedSubCategory);
    SubCategory result = subCategoryService.updateSubCategory(updatedSubCategory);
    assertNotNull(result);
    assertEquals(updatedSubCategory.getSubcategoryName(), result.getSubcategoryName());
    assertEquals(updatedSubCategory.getSubcategoryDescription(), result.getSubcategoryDescription());
    verify(subCategoryRepository, times(1)).findById(id);
    verify(subCategoryRepository, times(1)).save(existingSubCategory);
}
 
     @Test
    void testdeleteCategory(){
        Long id = 1L;
        SubCategory existingSubCategory = createSampleSubCategory();
        when(subCategoryRepository.findById(id)).thenReturn(Optional.of(existingSubCategory));
        subCategoryService.deleteSubCategory(id);
        verify(subCategoryRepository, times(1)).findById(id);
        verify(subCategoryRepository, times(1)).deleteById(id);
    }
}
