package com.bnt.TestManagement.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
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

import com.bnt.TestManagement.Exception.DataIsNotPresent;
import com.bnt.TestManagement.Exception.IdNotFoundException;
import com.bnt.TestManagement.Exception.QuestionModelEmpty;
import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Model.Question;
import com.bnt.TestManagement.Model.SubCategory;
import com.bnt.TestManagement.Repository.QuestionRepository;
import com.bnt.TestManagement.Service.ServiceImplementation.QuestionServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {
    
    @Mock
    QuestionRepository questionRepository;

    @InjectMocks
    QuestionServiceImpl questionService;

    static  Category category = new Category(1L, "java", "Core Java category");
    static   SubCategory subCategory = new SubCategory(1L, category, "springboot", "Spring Boot category");

   
    @Test
    void createMcqQuestionTest(){
    Question expQuestion = new Question(1l,subCategory,"In Spring Boot @RestController annotation is equivalent to","@Controller and @PostMapping","@Controller and @Component","@Controller and @ResponseBody","@Controller and @ResponseStatus","@Controller and @ResponseBody",3,-1);
    when(questionRepository.save(expQuestion)).thenReturn(expQuestion);
    Question Actualquestion = questionService.createMcqQuestion(expQuestion);
    assertEquals(expQuestion, Actualquestion);
    }

      @Test
    void getAllMcqQuestionTest(){
        List<Question> expQuestions = new ArrayList<>();
        expQuestions.add(new Question(1l,subCategory,"In Spring Boot @RestController annotation is equivalent to","@Controller and @PostMapping","@Controller and @Component","@Controller and @ResponseBody","@Controller and @ResponseStatus","@Controller and @ResponseBody",3,-1));
        expQuestions.add(new Question(2l,subCategory,"In Spring Boot @RestController annotation is equivalent to","@Controller and @PostMapping","@Controller and @Component","@Controller and @ResponseBody","@Controller and @ResponseStatus","@Controller and @ResponseBody",3,-1));
        when(questionRepository.findAll()).thenReturn(expQuestions);
        List<Question> ActualResult = questionService.getAllMcqQuestions();
        assertEquals(expQuestions, ActualResult);       
    }

    @Test
    public void GetMcqQuestionById(){
    Long id = 1L;
    when(questionRepository.findById(anyLong())).thenReturn(Optional.empty());
    assertThrows(IdNotFoundException.class, () -> questionService.getMcqQuestionById(id));
    verify(questionRepository, times(1)).findById(id);
    }


@Test
    void updateMcqQuestionTest_questionNotFound() {
        Question newQuestion = new Question(1L, subCategory, "In Spring Boot @RestController annotation is equivalent to",
        "@Controller and @PostMapping","@Controller and @Component","@Controller and @ResponseBody","@Controller and @ResponseStatus","@Controller and @ResponseBody",3,-1);
        when(questionRepository.findById(newQuestion.getQuestion_id())).thenReturn(Optional.empty());
        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> {
            questionService.updateMcqQuestion(newQuestion);
        });
        assertEquals("Id is Not Present", exception.getMessage());
        verify(questionRepository, times(1)).findById(newQuestion.getQuestion_id());
        verify(questionRepository, never()).save(any(Question.class));
    }


@Test
void deleteMcqQuestionTest() {
    Long id = 1L;
    Question existingQuestion = new Question(1L, subCategory, "In Spring Boot @RestController annotation is equivalent to",
    "@Controller and @PostMapping","@Controller and @Component","@Controller and @ResponseBody","@Controller and @ResponseStatus","@Controller and @ResponseBody",3,-1);
    when(questionRepository.findById(id)).thenReturn(Optional.of(existingQuestion));
    questionService.deleteMCqQuestion(id);
    verify(questionRepository, times(1)).findById(id);
    verify(questionRepository, times(1)).deleteById(id);
}

// <<---------------------------------------------------Negative Test Case------------------------------------------------->>

    @Test
    void createMcqQuestionTest_QuestionModelIsEmpty() {
    Question questionWithNullField = new Question();
    questionWithNullField.setQuestion(null);   
    QuestionModelEmpty thrown = assertThrows(
        QuestionModelEmpty.class,
        () -> questionService.createMcqQuestion(questionWithNullField));
    assertEquals("Question model is empty", thrown.getMessage());
}

    @Test
    void getByMcqQuestionIdTest_IdNotFound() {
    Long invalidId = -1L;
    when(questionRepository.findById(invalidId)).thenReturn(Optional.empty());
    
    IdNotFoundException thrown = assertThrows(
        IdNotFoundException.class,
        () -> questionService.getMcqQuestionById(invalidId));
    assertEquals("Id Not Found:" + invalidId, thrown.getMessage());
   }

     @Test
    void getAllMcqQuestionTest_DataIsNotPresent() {
    List<Question> emptyQuestionList = new ArrayList<>();
    when(questionRepository.findAll()).thenReturn(emptyQuestionList);
    DataIsNotPresent thrown = assertThrows(
            DataIsNotPresent.class,
            () -> questionService.getAllMcqQuestions());
        assertEquals("Data is not present", thrown.getMessage());
    }

    @Test
    void testUpdateMcqQuestion_IdNotFound() {
        Question newQuestion = new Question();
        newQuestion.setQuestion_id(999L); 
        when(questionRepository.findById(newQuestion.getQuestion_id())).thenReturn(Optional.empty());
        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> 
            questionService.updateMcqQuestion(newQuestion));
        assertEquals("Id is Not Present", exception.getMessage());
    }

    @Test
    void testDeleteMcqQuestion_IdNotFound() {
        Long questionId = 999L;
        when(questionRepository.findById(questionId)).thenReturn(Optional.empty());
        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () ->
            questionService.deleteMCqQuestion(questionId));
        assertEquals("Id is Not Found", exception.getMessage());
    }
    
}
    







