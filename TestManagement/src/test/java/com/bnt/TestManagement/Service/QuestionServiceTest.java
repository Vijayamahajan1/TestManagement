package com.bnt.TestManagement.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

import com.bnt.TestManagement.Model.Question;
import com.bnt.TestManagement.Repository.QuestionRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {
    
    @Mock
    QuestionRepository questionRepository;

    @InjectMocks
    QuestionServiceImpl questionService;

    @Test
    void createMcqQuestionTest(){
    Question expQuestion = new Question(1l,"springboot","In Spring Boot @RestController annotation is equivalent to","@Controller and @PostMapping","@Controller and @Component","@Controller and @ResponseBody","@Controller and @ResponseStatus","@Controller and @ResponseBody",3,-1);
    when(questionRepository.save(expQuestion)).thenReturn(expQuestion);
    Question Actualquestion = questionService.createMcqQuestion(expQuestion);
    assertEquals(expQuestion, Actualquestion);
    }

      @Test
    void getAllMcqQuestionTest(){
        List<Question> expQuestions = new ArrayList<>();
        expQuestions.add(new Question(1l,"cpp","In Spring Boot @RestController annotation is equivalent to","@Controller and @PostMapping","@Controller and @Component","@Controller and @ResponseBody","@Controller and @ResponseStatus","@Controller and @ResponseBody",3,-1));
        expQuestions.add(new Question(2l,"springboot","In Spring Boot @RestController annotation is equivalent to","@Controller and @PostMapping","@Controller and @Component","@Controller and @ResponseBody","@Controller and @ResponseStatus","@Controller and @ResponseBody",3,-1));
        when(questionRepository.findAll()).thenReturn(expQuestions);
        List<Question> ActualResult = questionService.getAllMcqQuestions();
        assertEquals(expQuestions, ActualResult);       
    }

     @Test
    void getMcqQuestionByIdTest(){
        Long id=1l;
       Optional<Question> expQuestion = Optional.empty();
       when(questionRepository.findById(id)).thenReturn(expQuestion);
       Optional<Question> ActualResult= questionService.getMcqQuestionById(1l);
       assertFalse(ActualResult.isPresent());
    }

    @Test
    void updateMcqQuestionTest(){
        Question expQuestion = new Question(1l,"springboot","In Spring Boot @RestController annotation is equivalent to","@Controller and @PostMapping","@Controller and @Component","@Controller and @ResponseBody","@Controller and @ResponseStatus","@Controller and @ResponseBody",3,-1);
        when(questionRepository.save(expQuestion)).thenReturn(expQuestion);
        Question Actualquestion = questionService.updateMcqQuestion(expQuestion);
        assertEquals(expQuestion, Actualquestion);
    }

    @Test
    void deleteMcqQuestionTest(){
            Long id=1l;
            questionService.deleteMCqQuestion(id);
            verify(questionRepository,times(1)).deleteById(id);

    }
}
