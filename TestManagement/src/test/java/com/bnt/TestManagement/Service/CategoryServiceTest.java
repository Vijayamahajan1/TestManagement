package com.bnt.TestManagement.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Repository.CategoryRepository;

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
    void getCategoryByIdTest(){
        Long id=1l;
       Optional<Category> expcategory = Optional.empty();
       when(categoryRepository.findById(id)).thenReturn(expcategory);
       Optional<Category> Actualcategory = categoryService.getCategoryById(id);
        assertEquals(expcategory, Actualcategory);
    }
    @Test
    void updateCategoryTest(){
        Category expcategory = new Category(1l, "java", "Core Java category");
        when(categoryRepository.save(expcategory)).thenReturn(expcategory);
        Category Actualcategory = categoryService.updateCategory(expcategory);
        assertEquals(expcategory, Actualcategory);
    }
     @Test
    void deleteCategoryTest(){
         Long id=1l;
            categoryService.deleteCategory(id);
            verify(categoryRepository,times(1)).deleteById(id);
    }
}
