package com.bnt.TestManagement.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.TestManagement.Model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{
    
    Optional<Category> findByCategoryName(String categoryName);

}
