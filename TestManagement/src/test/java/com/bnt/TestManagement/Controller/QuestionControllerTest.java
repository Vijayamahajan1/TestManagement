package com.bnt.TestManagement.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doNothing;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Model.Question;
import com.bnt.TestManagement.Model.SubCategory;
import com.bnt.TestManagement.Service.QuestionServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class QuestionControllerTest {
    
    @Mock
    QuestionServiceImpl questionService;

    @InjectMocks
    QuestionController questionController;

    static Category category = new Category(1L, "java", "Core Java category");
    static SubCategory subCategory = new SubCategory(1L, category, "springboot", "Spring Boot category");

    @Test
    void createMcqQuestionTest(){
    Question expQuestion = new Question(1l,subCategory,"In Spring Boot @RestController annotation is equivalent to","@Controller and @PostMapping","@Controller and @Component","@Controller and @ResponseBody","@Controller and @ResponseStatus","@Controller and @ResponseBody",3,-1);
    when(questionService.createMcqQuestion(expQuestion)).thenReturn(expQuestion);
    ResponseEntity<Question> ActualResponseEntity = questionController.createMcqQuestion(expQuestion);
    assertEquals(HttpStatus.OK, ActualResponseEntity.getStatusCode());
    assertEquals(expQuestion, ActualResponseEntity.getBody());
    }

    @Test
    void getAllMcqQuestionTest(){
    List<Question> expQuestions = new ArrayList<>();
    expQuestions.add(new Question(1l,subCategory,"In Spring Boot @RestController annotation is equivalent to","@Controller and @PostMapping","@Controller and @Component","@Controller and @ResponseBody","@Controller and @ResponseStatus","@Controller and @ResponseBody",3,-1));
    expQuestions.add(new Question(2l,subCategory,"In Spring Boot @RestController annotation is equivalent to","@Controller and @PostMapping","@Controller and @Component","@Controller and @ResponseBody","@Controller and @ResponseStatus","@Controller and @ResponseBody",3,-1));
    when(questionService.getAllMcqQuestions()).thenReturn(expQuestions);
    ResponseEntity<List<Question>> ActualResponseEntity = questionController.getAllMcqQuestions();
    assertEquals(HttpStatus.OK, ActualResponseEntity.getStatusCode());
    assertEquals(expQuestions, ActualResponseEntity.getBody());
    }

    @Test
    void getMcqQuestionByIdTest(){
        Long id=1l;
       Optional<Question> expQuestion = Optional.empty();
       when(questionService.getMcqQuestionById(id)).thenReturn(expQuestion);
       ResponseEntity<Optional<Question>> ActualResult = questionController.getMcqQuestionById(1l);
       assertSame(HttpStatus.OK, ActualResult.getStatusCode());
       assertSame(expQuestion, ActualResult.getBody());  
    }
    @Test
    void updateMcqQuestionTest(){
        Question expQuestion = new Question(1l,subCategory,"In Spring Boot @RestController annotation is equivalent to","@Controller and @PostMapping","@Controller and @Component","@Controller and @ResponseBody","@Controller and @ResponseStatus","@Controller and @ResponseBody",3,-1);
        when(questionService.updateMcqQuestion(expQuestion)).thenReturn(expQuestion);
        ResponseEntity<Question> ActualResult = questionController.updateMcqQuestion(expQuestion);
        assertSame(HttpStatus.OK, ActualResult.getStatusCode());
        assertSame(expQuestion, ActualResult.getBody()); 
    }
    @Test
    void deleteMcqQuestionTest(){
        Long id=1l;
        doNothing().when(questionService).deleteMCqQuestion(id);
        ResponseEntity<Object> ActualResponseEntity = questionController.deleteMCqQuestion(id);
        assertEquals(HttpStatus.OK, ActualResponseEntity.getStatusCode());
        assertEquals("Question deleted Successfully", ActualResponseEntity.getBody());

    }

}
