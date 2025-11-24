package com.example.Quiz_App.service;

import com.example.Quiz_App.dto.responsedto.QuizResultResponse;
import com.example.Quiz_App.model.Answer;
import com.example.Quiz_App.model.QuizResult;
import com.example.Quiz_App.model.QuizSession;
import com.example.Quiz_App.repository.AnswerRepository;
import com.example.Quiz_App.repository.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    @Autowired
    AnswerService answerService;

    @Autowired
    QuizResultRepository quizResultRepository;

    @Autowired
    AnswerRepository answerRepository;


    public QuizResultResponse calculateAndSaveResult(Long quizSessionId){

        // check user enter valid session or not
        QuizSession quizSession = answerService.getValidSession(quizSessionId);

        if(quizSession == null){
            return failureResponse();
        }

        List<Answer> byQuizSession = getTotalRecordsBySession(quizSession);

        int correctAnswers = 0;
        int totalQuestions = byQuizSession.size();


        for (Answer answer : byQuizSession){
            if(answer.isCorrect()){
                correctAnswers++;
            }
        }

        // Count Total Incorrect question
        int totalIncorrect = totalQuestions - correctAnswers;

        // Calculate percentage
        double score = ((double) correctAnswers / totalQuestions) * 100;

        // check if the user is passed or failed
        String result = score >= 50 ? "pass" : "fail";

        saveQuizResult(quizSession, correctAnswers, totalIncorrect, totalQuestions, score, result);

        return successResponse(correctAnswers, totalIncorrect, totalQuestions, score, result);
    }


    private List<Answer> getTotalRecordsBySession(QuizSession quizSession){
        return answerRepository.findByQuizSession(quizSession);
    }

    private QuizResultResponse failureResponse(){
        QuizResultResponse response = new QuizResultResponse();
        response.setMessage("No QuizSession Found");
        return response;
    }

    private QuizResultResponse successResponse(int correctAnswers, int incorrectAnswers, int totalQuestions, double score, String result){
        QuizResultResponse response = new QuizResultResponse();
        response.setMessage("Quiz Result Calculated");
        response.setCorrectAnswers(correctAnswers);
        response.setIncorrectAnswers(incorrectAnswers);
        response.setTotalQuestions(totalQuestions);
        response.setScore(score);
        response.setResult(result);

        return  response;
    }

    private void saveQuizResult(QuizSession quizSession, int correct, int incorrect, int totalQuestion, double score, String result){
        QuizResult quizResult = new QuizResult();

        quizResult.setTotalQuestions(totalQuestion);
        quizResult.setQuizSession(quizSession);
        quizResult.setCorrectAnswers(correct);
        quizResult.setScore(score);
        quizResult.setResult(result);
        quizResult.setIncorrectAnswers(incorrect);

        quizResultRepository.save(quizResult);
    }
}
