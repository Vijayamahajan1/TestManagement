package com.bnt.TestManagement.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Optional;

import com.bnt.TestManagement.Model.Question;
import com.bnt.TestManagement.Service.ServiceImplementation.QuestionServiceImpl;

@RestController
@RequestMapping("/api/testManagement")
public class QuestionController {
    @Autowired
    QuestionServiceImpl questionService;

     private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
    
    @PostMapping
    public ResponseEntity<Question> createMcqQuestion(@RequestBody Question question){
        logger.info("Save the Question");
        Question createdQuestion = questionService.createMcqQuestion(question);
        return new ResponseEntity<Question>(createdQuestion, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllMcqQuestions(){
        logger.info("Get all the Questions");
        List<Question> getedAllQuestion = questionService.getAllMcqQuestions();
        return new ResponseEntity<List<Question>>(getedAllQuestion,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Question>> getMcqQuestionById(@PathVariable ("id") Long id ){
        logger.info("Get the question with id"+id);
        Optional<Question> getedQuestion = questionService.getMcqQuestionById(id);
        return new ResponseEntity<>(getedQuestion,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Question> updateMcqQuestion(@RequestBody Question question){
        logger.info("Update the question");
        Question updatedQuestion = questionService.updateMcqQuestion(question);
        return new ResponseEntity<Question>(updatedQuestion,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMCqQuestion(@PathVariable ("id") Long id) {
     questionService.deleteMCqQuestion(id);
     logger.info("deleted the question with id"+id);
      return new ResponseEntity<>("Question deleted Successfully" ,HttpStatus.OK);
    }
     
   @PostMapping("/bulk-upload")
   public ResponseEntity<String> bulkUploadQuestions(@RequestParam("file") MultipartFile file) throws Exception {
       questionService.uploadExcelFile(file);
       return ResponseEntity.ok("Questions uploaded successfully");
   }
}
