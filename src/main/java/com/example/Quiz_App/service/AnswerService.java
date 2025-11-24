package com.example.Quiz_App.service;

import com.example.Quiz_App.dto.requestdto.AnswerRequest;
import com.example.Quiz_App.dto.responsedto.AnswerResponse;
import com.example.Quiz_App.dto.responsedto.QuizResultResponse;
import com.example.Quiz_App.model.Answer;
import com.example.Quiz_App.model.QuizQuestions;
import com.example.Quiz_App.model.QuizResult;
import com.example.Quiz_App.model.QuizSession;
import com.example.Quiz_App.repository.AnswerRepository;
import com.example.Quiz_App.repository.QuestionsRepository;
import com.example.Quiz_App.repository.QuizResultRepository;
import com.example.Quiz_App.repository.QuizSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    @Autowired
    QuizSessionRepository quizSessionRepository;

    @Autowired
    QuestionsRepository questionsRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuizResultRepository quizResultRepository;


    public AnswerResponse saveAnswerDetail(AnswerRequest answerRequest){

        //  Fetch Quiz Session
        QuizSession quizSession = getValidSession(answerRequest.getSessionId());

        // Fetch Question Detail
        QuizQuestions questions = getValidQuestion(answerRequest.getQuestionId());

        if(quizSession == null || questions == null){
            return quizErrorResponse();
        }

        boolean isCorrect = evaluateAnswer(answerRequest.getSelectedAnswer(), questions.getCorrectAnswer());

        saveAnswer(quizSession, questions, answerRequest.getSelectedAnswer(), isCorrect);

        return quizSuccessResponse(isCorrect, questions.getCorrectAnswer());
    }

    protected QuizSession getValidSession(Long sessionId){
        return quizSessionRepository.findById(sessionId).orElse(null);
    }

    private QuizQuestions getValidQuestion(Long questionId){
        return questionsRepository.findById(questionId).orElse(null);
    }

    private boolean evaluateAnswer(String selected, String correct){
        return correct.equalsIgnoreCase(selected);
    }

    private void saveAnswer(QuizSession quizSession, QuizQuestions question, String selectedOption, boolean isCorrect){
        Answer answer = new Answer();
        answer.setQuizSession(quizSession);
        answer.setQuizQuestion(question);
        answer.setCorrect(isCorrect);
        answer.setSelectedOption(selectedOption);

        answerRepository.save(answer);
    }

    private AnswerResponse quizSuccessResponse(boolean isCorrect, String correctAnswer){
        AnswerResponse response = new AnswerResponse();

        response.setCorrect(isCorrect);
        response.setCorrectAnswer(correctAnswer);
        response.setMessage(isCorrect ? "Correct" : "Wrong answer");
        return response;
    }

    private AnswerResponse quizErrorResponse(){
        AnswerResponse response = new AnswerResponse();

        response.setCorrect(false);
        response.setMessage("Invalid Quiz Session or Question");

        return response;
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
