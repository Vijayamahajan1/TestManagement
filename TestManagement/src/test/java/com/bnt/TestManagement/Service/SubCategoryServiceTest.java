package com.bnt.TestManagement.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void testgetSubCategoryById(){
       Long id=1l;
       Optional<SubCategory> expcategory = Optional.empty();
       when(subCategoryRepository.findById(id)).thenReturn(expcategory);
       Optional<SubCategory> ActualSubcategory = subCategoryService.getSubCategoryById(id);
       assertEquals(expcategory, ActualSubcategory);
      
    }

    @Test
    void tsetupdateSubCategory() {
        SubCategory expectedSubCategory = createSampleSubCategory();
        when(subCategoryRepository.save(any(SubCategory.class))).thenReturn(expectedSubCategory);
        SubCategory ActualSubcategory = subCategoryService.updateSubCategory(expectedSubCategory);
        assertEquals(expectedSubCategory,ActualSubcategory);
    }
 
     @Test
    void testdeleteCategory(){
         Long id=1l;
            subCategoryService.deleteSubCategory(id);
            verify(subCategoryRepository,times(1)).deleteById(id);
    }
}
