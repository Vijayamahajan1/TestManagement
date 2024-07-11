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
import com.bnt.TestManagement.Model.SubCategory;
import com.bnt.TestManagement.Repository.SubCategoryRepository;
import com.bnt.TestManagement.Service.ServiceInterface.SubCategoryService;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

     private static final Logger logger = LoggerFactory.getLogger(SubCategoryService.class);

    @Autowired
    SubCategoryRepository subCategoryRepository;
    @Override
    public SubCategory createSubCategory(SubCategory subCategory) {
         logger.info("createSubCategory method called");
        if(subCategory ==null || subCategory.getSubcategoryName()==null || subCategory.getSubcategoryDescription().isEmpty()){
            logger.error("Exception Occured");
            throw new InvalidDataException("Data Is Invalid");
        }
        SubCategory checkSubCategoryName= subCategoryRepository.findBySubcategoryName(subCategory.getSubcategoryName());
        if(checkSubCategoryName != null){
            logger.error("Exception occured");
           throw new DataAllreadyPresentException("Subategory is allready present");
        }
        SubCategory storedsubcategory =  subCategoryRepository.save(subCategory);                                 
        return storedsubcategory;
    }

    @Override
    public List<SubCategory> getAllSubCategory() {
        logger.info("getAllSubCategory method is called");
        List<SubCategory> list = subCategoryRepository.findAll();
        if(list.isEmpty()){
            logger.error("Execption Occured");
            throw new DataIsNotPresent("Data is not present");
        }
        return list;
    }

    @Override
    public Optional<SubCategory> getSubCategoryById(Long id) {
        logger.info("getSubCategoryById method is called");
        Optional<SubCategory> subcategory =  subCategoryRepository.findById(id);
        if(!subcategory.isPresent()){
        logger.error("Execption Occured");
         throw new IdNotFoundException("Id Not Found:"+id);
      }
      return subcategory;
    }

    @Override
    public SubCategory updateSubCategory(SubCategory newSubCategory) {
        Optional<SubCategory> subcategory = subCategoryRepository.findById(newSubCategory.getSubcategory_id());
        if (subcategory.isPresent()) {
            SubCategory subCategory = subcategory.get();
            subCategory.setSubcategoryName(subCategory.getSubcategoryName());
            subCategory.setSubcategoryDescription(subCategory.getSubcategoryDescription());
            SubCategory updatedData = subCategoryRepository.save(subCategory);
            logger.info("return updated data");
            return updatedData;
        } else {
            logger.error("Exception occured");
            throw new IdNotFoundException("Id Is Not Present");
        }
    }
   
    @Override
    public void deleteSubCategory(Long id) {
        logger.info("The deleteSubCategory method is called");
        Optional<SubCategory> subcategory = subCategoryRepository.findById(id);
        if(subcategory.isEmpty()){    
            logger.error("Exception occured");
            throw new IdNotFoundException("Id is Not Found");         
           }
           subCategoryRepository.deleteById(id);
            logger.info("The data is deleted");
    }  
    
}
