package com.bnt.TestManagement.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.TestManagement.Model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{
    // @Query("SELECT c FROM Category c WHERE c.category_name = :categoryName")
    // List<Category> findByName(@Param("category_name") String category_name);

    Category findByCategoryName(String categoryName);

}
