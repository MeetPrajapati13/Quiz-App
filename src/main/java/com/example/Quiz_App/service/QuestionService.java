package com.example.Quiz_App.service;

import com.example.Quiz_App.dto.responsedto.QuestionResponse;
import com.example.Quiz_App.model.QuizQuestions;
import com.example.Quiz_App.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionsRepository questionsRepository;

    public QuestionResponse saveAllQuestions(List<QuizQuestions> quizQuestions) {
        QuestionResponse questionResponseDto = new QuestionResponse();

        for(QuizQuestions questions : quizQuestions){
            try{
                if(questionsRepository.existsByQuestion(questions.getQuestion())){
                    // duplicate question found
                    questionResponseDto.getSkippedQuestions().add(questions.getQuestion());
                }
                else{
                    // save new question
                    questionsRepository.save(questions);
                    questionResponseDto.getSavedQuestions().add(questions.getQuestion());
                }
            }catch (Exception e){
                // failed question (like null value, DataIntegrityViolation)
                questionResponseDto.getFailedQuestions().add(questions.getQuestion() + " - " + e.getMessage());
            }
        }

        String summary = String.format("Saved Question : %d, Skipped Question : %d, Failed Question : %d",
                questionResponseDto.getSavedQuestions().size(), questionResponseDto.getSkippedQuestions().size(), questionResponseDto.getFailedQuestions().size());

         questionResponseDto.setMessage(summary);

         return questionResponseDto;
    }


}
