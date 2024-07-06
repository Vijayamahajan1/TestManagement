package com.bnt.TestManagement.Service.ServiceImplementation;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bnt.TestManagement.Exception.DataIsNotPresent;
import com.bnt.TestManagement.Exception.IdNotFoundException;
import com.bnt.TestManagement.Exception.QuestionModelEmpty;
import com.bnt.TestManagement.Model.Question;
import com.bnt.TestManagement.Model.SubCategory;
import com.bnt.TestManagement.Repository.QuestionRepository;
import com.bnt.TestManagement.Repository.SubCategoryRepository;
import com.bnt.TestManagement.Service.ServiceInterface.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
   private QuestionRepository questionRepository;

    @Autowired
   private SubCategoryRepository subCategoryRepository;

    
    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

    @Override
    public Question createMcqQuestion(Question question) {
        logger.info("The createMcqQuestion method is called");
        if (question==null ||  question.getQuestion()==null) {
            logger.error("Exception occured");
            throw new  QuestionModelEmpty("Question model is empty");
        }
        Question storedQuestion = questionRepository.save(question);
        return storedQuestion;  
    }

    @Override
    public List<Question> getAllMcqQuestions() {
        logger.info("The getAllMcqQuestions method is called");
       List<Question> list = questionRepository.findAll();
       if(list.isEmpty()){
        logger.error("Exception occured");
        throw new DataIsNotPresent("Data is not present");
       }
        return list;
    }

    @Override
    public Optional<Question> getMcqQuestionById(Long id) {
        logger.info("The getMcqQuestionById method is called");
        Optional<Question> question = questionRepository.findById(id);
       if(!question.isPresent()){    
            logger.error("Exception occured");       
            throw new IdNotFoundException("Id not found with id");
        }
       return question;   
    }

    @Override
    public Question updateMcqQuestion(Question newquestion) { 
        logger.info("The updateMcqQuestion method is called");
        Optional<Question> question = questionRepository.findById(newquestion.getQuestion_id());
        if (question.isPresent()) {
            Question questions =question.get();
            questions.setSubCategory(newquestion.getSubCategory());
            questions.setQuestion(newquestion.getQuestion());
            questions.setOptionOne(newquestion.getOptionOne());
            questions.setOptionTwo(newquestion.getOptionTwo());
            questions.setOptionThree(newquestion.getOptionThree());
            questions.setOptionFour(newquestion.getOptionFour());
            questions.setCorrectOption(newquestion.getCorrectOption());
            questions.setPositiveMark(newquestion.getPositiveMark());
            questions.setNegativeMark(newquestion.getNegativeMark());
            Question updatedData = questionRepository.save(questions);
            logger.info("return updated data");
            return updatedData;
        }else{
            logger.error("Exception occured");
            throw new IdNotFoundException("Id is Not Present");
        }
    }

    @Override
    public void deleteMCqQuestion(Long id) {
        logger.info("The deleteMCqQuestion method is called");
        Optional<Question> question = questionRepository.findById(id);
        if(question.isEmpty()){   
        logger.error("Exception occured");
        throw new IdNotFoundException("Id is Not Found");        
        }
        questionRepository.deleteById(id);
        logger.info("The data is deleted");
    }

    @Override
    public void uploadExcelFile(MultipartFile file)  throws Exception{
      logger.info("The bulk upload question mehod is called");
      List<Question>  questions = new ArrayList<>();
      try(InputStream is = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(is))  {
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet){
             if(row.getRowNum()==0){
               continue;
             }
              try{
                // String categoryname = getCellValue(row.getCell(1));
                String subcategoryname = getCellValue(row.getCell(2));
                String questionText = getCellValue(row.getCell(3));
                String optionOne = getCellValue(row.getCell(4));
                String optionTwo = getCellValue(row.getCell(5));
                String optionThree = getCellValue(row.getCell(6));
                String optionFour = getCellValue(row.getCell(7));
                String correctOption = getCellValue(row.getCell(8));
                int positiveMark;
                if (row.getCell(9).getCellType() == CellType.NUMERIC) {
                    positiveMark = (int) row.getCell(9).getNumericCellValue();
                } else {
                    positiveMark = Integer.parseInt(row.getCell(9).getStringCellValue());
                 
                }
   
                int negativeMark;
                if (row.getCell(10).getCellType() == CellType.NUMERIC) {
                    negativeMark = (int) row.getCell(10).getNumericCellValue();
                } else {
                    negativeMark = Integer.parseInt(row.getCell(10).getStringCellValue());
                }


               SubCategory subCategory = subCategoryRepository.findBySubcategoryName(subcategoryname);
               if(subCategory==null){
                logger.error("Subcategory not found: {}", subcategoryname);
                throw new DataIsNotPresent("Subcategory is not present");
               }
           Question question = new Question();
           question.setSubCategory(subCategory);
           question.setQuestion(questionText);
           question.setOptionOne(optionOne);
           question.setOptionTwo(optionTwo);
           question.setOptionThree(optionThree);
           question.setOptionFour(optionFour);
           question.setCorrectOption(correctOption);
           question.setPositiveMark(positiveMark);
           question.setNegativeMark(negativeMark);
           questions.add(question);
            } catch(DataIsNotPresent e) {
                logger.error("Data not present", e.getMessage());
            }catch(Exception e){
                e.printStackTrace();
            }
        } 
        workbook.close();
        questionRepository.saveAll(questions);
        }  
         
    }

    private String getCellValue(Cell cell) {
       return cell.getStringCellValue();
    }

    }

    

