package com.bnt.TestManagement.Service;

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

import com.bnt.TestManagement.Model.Question;
import com.bnt.TestManagement.Model.SubCategory;
import com.bnt.TestManagement.Repository.CategoryRepository;
import com.bnt.TestManagement.Repository.QuestionRepository;
import com.bnt.TestManagement.Repository.SubCategoryRepository;

@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
   private QuestionRepository questionRepository;

    @Autowired
   private SubCategoryRepository subCategoryRepository;

    @Autowired 
   private CategoryRepository categoryRepository;
    
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

                String categoryname = getCellValue(row.getCell(1));
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

            //    Category category = categoryRepository.findByName(category_name);
            //    if(category==null){
            //     category = new Category();
            //     category.setCategory_name(category_name);
            //     categoryRepository.save(category);
            //    }

               SubCategory subCategory = subCategoryRepository.findBySubcategoryName(subcategoryname);
               if(subCategory==null){
                subCategory = new SubCategory();
                subCategory.setSubcategoryName(subcategoryname);
                subCategoryRepository.save(subCategory);
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
           
        } 
        workbook.close();
        questionRepository.saveAll(questions);
        }       
         
    }

    private String getCellValue(Cell cell) {
       return cell.getStringCellValue();
    }

    }

    

