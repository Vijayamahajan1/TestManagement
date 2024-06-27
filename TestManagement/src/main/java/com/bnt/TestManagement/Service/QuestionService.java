package com.bnt.TestManagement.Service;

import java.util.List;
import java.util.Optional;

import com.bnt.TestManagement.Model.Question;

public interface QuestionService {

    public Question createMcqQuestion(Question question);

    public List<Question> getAllMcqQuestions();

    public Optional<Question> getMcqQuestionById(Long id);

    public Question updateMcqQuestion(Question newquestion);

    public void deleteMCqQuestion(Long id);
}
