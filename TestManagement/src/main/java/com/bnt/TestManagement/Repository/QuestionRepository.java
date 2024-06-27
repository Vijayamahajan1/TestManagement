package com.bnt.TestManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bnt.TestManagement.Model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
