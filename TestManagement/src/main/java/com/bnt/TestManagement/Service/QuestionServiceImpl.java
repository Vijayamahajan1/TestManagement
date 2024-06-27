package com.bnt.TestManagement.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.TestManagement.Model.Question;
import com.bnt.TestManagement.Repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    QuestionRepository questionRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);
    @Override
    public Question createMcqQuestion(Question question) {
        return questionRepository.save(question);  
    }

    @Override
    public List<Question> getAllMcqQuestions() {
        logger.info("The getAllMcqQuestions method is called");
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> getMcqQuestionById(Long id) {
        logger.info("The getMcqQuestionById method is called");
      return questionRepository.findById(id);
    }

    @Override
    public Question updateMcqQuestion(Question newquestion) { 
        logger.info("The updateMcqQuestion method is called");
            return questionRepository.save(newquestion);
    }

    @Override
    public void deleteMCqQuestion(Long id) {
        logger.info("The deleteMCqQuestion method is called");
       questionRepository.deleteById(id);
    }

    
}
