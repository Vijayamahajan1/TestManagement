package com.bnt.TestManagement.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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
import com.bnt.TestManagement.Service.ServiceImplementation.QuestionServiceImpl;

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
      Long questionId = 1L;
        Question expQuestion = new Question();
        expQuestion.setQuestion_id(questionId);
        Optional<Question> optionalQuestion = Optional.of(expQuestion);
        when(questionService.getMcqQuestionById(anyLong())).thenReturn(optionalQuestion);
        ResponseEntity<Optional<Question>> responseEntity = questionController.getMcqQuestionById(questionId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(optionalQuestion, responseEntity.getBody()); 
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
//>>------------------------------------------Negative test cases------------------------------------------------------->>

@Test
void createMcqQuestionTest_ServiceError() {
    Question question = new Question(1L, subCategory, "Question?", "Option A", "Option B", "Option C", "Option D", "Option C", 1, -1);
    when(questionService.createMcqQuestion(question)).thenThrow(new RuntimeException("Service error"));
    RuntimeException exception = assertThrows(RuntimeException.class, () -> 
    questionController.createMcqQuestion(question));   
    assertEquals("Service error", exception.getMessage());
}

    @Test
    void getAllMcqQuestionTest_ServerError() {
    when(questionService.getAllMcqQuestions()).thenThrow(new RuntimeException("Server error"));
    Exception exception = assertThrows(RuntimeException.class, () -> 
    questionController.getAllMcqQuestions());
    assertEquals("Server error", exception.getMessage());
    }

    @Test
    void getMcqQuestionByIdTest_NotFound() {
    Long questionId = 1L;
    when(questionService.getMcqQuestionById(questionId)).thenThrow(new RuntimeException("Question not found"));
    Exception exception = assertThrows(RuntimeException.class, () -> 
    questionController.getMcqQuestionById(questionId));
    assertEquals("Question not found", exception.getMessage());
   }

    @Test
    void updateMcqQuestionTest_InvalidQuestion() {
    Question invalidQuestion = new Question(1L, subCategory, null, "Option A", "Option B", "Option C", "Option D", "Option A", 1, -1); // Assuming question text is required
    when(questionService.updateMcqQuestion(invalidQuestion)).thenThrow(new IllegalArgumentException("Invalid question data"));
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
    questionController.updateMcqQuestion(invalidQuestion));   
    assertEquals("Invalid question data", exception.getMessage());
   }

    @Test
    void deleteMcqQuestionTest_NotFound() {
    Long id = 1L;
    doThrow(new RuntimeException("Question not found")).when(questionService).deleteMCqQuestion(id);
    Exception exception = assertThrows(RuntimeException.class, () -> 
    questionController.deleteMCqQuestion(id));   
    assertEquals("Question not found", exception.getMessage());
}



}



