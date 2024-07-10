package com.bnt.TestManagement.Service.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.TestManagement.Exception.DataAllreadyPresentException;
import com.bnt.TestManagement.Exception.DataIsNotPresent;
import com.bnt.TestManagement.Exception.IdNotFoundException;
import com.bnt.TestManagement.Exception.InvalidDataException;
import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Repository.CategoryRepository;
import com.bnt.TestManagement.Service.ServiceInterface.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        logger.info("createCategory method called");
        if(category ==null || category.getCategoryName()==null || category.getCategoryDescription().isEmpty()){
            logger.error("Exception Occured");
            throw new InvalidDataException("Data Is Invalid");
        }
        Optional<Category> checkCategoryName= categoryRepository.findByCategoryName(category.getCategoryName());
        if(checkCategoryName.isPresent()){
           throw new DataAllreadyPresentException("Category is allready present");
        }
        Category storedcategory = categoryRepository.save(category);
        return storedcategory;
    }

    @Override
    public List<Category> getAllCategory() {
        logger.info("getAllCategory method called");
        List<Category> list = categoryRepository.findAll();
        if(list.isEmpty()){
            logger.error("Exception occured");
            throw new DataIsNotPresent("Data is not present");
        }
        return list;
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        logger.info("getCategoryById method called");
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new IdNotFoundException("Id Not Found:"+ id);
        }
        return category;
    }

    @Override
    public Category updateCategory(Category newcategory) {
        Optional<Category> category = categoryRepository.findById(newcategory.getCategoryId());
        if (category.isPresent()) {
            Category categorys = category.get();
            categorys.setCategoryName(newcategory.getCategoryName());
            categorys.setCategoryDescription(newcategory.getCategoryDescription());
            Category updatedData = categoryRepository.save(categorys);
            logger.info("return updated data");
            return updatedData;
        } else {
            logger.error("Exception occured");
            throw new IdNotFoundException("Id Is Not Present");
        }
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            logger.error("Exception occured");
            throw new IdNotFoundException("Id is Not Found");

        }
        categoryRepository.deleteById(id);
        logger.info("The data is deleted");

    }

}
