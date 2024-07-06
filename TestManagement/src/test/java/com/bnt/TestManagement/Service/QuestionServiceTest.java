package com.bnt.TestManagement.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

import com.bnt.TestManagement.Exception.IdNotFoundException;
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
    void getMcqQuestionByIdTest() {
    Long id = 1L;
    Optional<Question> expQuestion = Optional.empty();
    when(questionRepository.findById(id)).thenReturn(expQuestion);
    IdNotFoundException thrown = assertThrows(
        IdNotFoundException.class,
        () -> questionService.getMcqQuestionById(id),
        "Expected getMcqQuestionById to throw, but it didn't"
    );
    assertFalse(thrown.getMessage().contains("Id not found with id: " + id));
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

}
