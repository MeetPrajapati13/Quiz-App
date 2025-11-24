package com.example.Quiz_App.service;

import com.example.Quiz_App.dto.requestdto.QuestionDTO;
import com.example.Quiz_App.dto.requestdto.QuizSessionRequest;
import com.example.Quiz_App.model.QuizQuestions;
import com.example.Quiz_App.model.QuizSession;
import com.example.Quiz_App.repository.AnswerRepository;
import com.example.Quiz_App.repository.QuestionsRepository;
import com.example.Quiz_App.repository.QuizSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuestionsRepository questionsRepository;

    @Autowired
    QuizSessionRepository quizSessionRepository;

    @Autowired
    AnswerRepository answerRepository;


    private List<QuizQuestions> questions;
    private int currentIndex = 0;

/*
    public void startQuiz(String userName, String mobileNo,int count) {
        QuizSession quizSession = new QuizSession();
        try {
            quizSession.setUserName(userName);
            quizSession.setMobileNo(mobileNo);
        }catch (DataIntegrityViolationException e){
            System.out.println("mobile number already exist");
        }


        quizSessionRepository.save(quizSession);

        questions = questionsRepository.findRandomQuestions(count);
        currentIndex = 0;

        if (questions.isEmpty()) {
            throw new IllegalStateException("No questions found in the database.");
        }
    }*/

    public boolean startQuiz(QuizSessionRequest quizSessionRequest){
        QuizSession quizSession = quizSessionRepository.findById(quizSessionRequest.getSessionId()).orElse(null);

        if(quizSession == null) return false;

        // check if user already submit answer of this session

        if(answerRepository.existsByQuizSession_SessionId(quizSessionRequest.getSessionId())){
            throw new IllegalStateException("User already attempted this quiz");
        }

        questions = questionsRepository.findRandomQuestions(quizSessionRequest.getCount());

        if(questions.isEmpty()){
            throw new IllegalStateException("No question found In the database");
        }

        return true;
    }

    public QuestionDTO getNextQuestion() {
        if (questions == null || questions.isEmpty()) {
            throw new IllegalStateException("Quiz not started! Call /quiz/start first.");
        }

        if (currentIndex < questions.size()) {
            return new QuestionDTO(questions.get(currentIndex++));
        } else {
            return null; // Quiz completed
        }
    }
}